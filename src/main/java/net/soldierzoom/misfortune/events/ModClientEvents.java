package net.soldierzoom.misfortune.events;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;
import net.soldierzoom.misfortune.curse.main.ICurse;

@Mod.EventBusSubscriber(modid = Misfortune.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ModClientEvents {
    private static boolean shaderActive = false;

    private static final ResourceLocation BLIND_SHADER =
            ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID, "shaders/post/blind.json");

    //helper func
    private static void toggleBlindShader(Minecraft mc) {
        if (shaderActive) {
            mc.gameRenderer.loadEffect(BLIND_SHADER);
        } else {
            mc.gameRenderer.shutdownEffect();
            shaderActive = false;
        }
    }

    //blindness curse
    @SubscribeEvent
    public static void onCurseChange(CurseChangedEvent event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.player!=event.getPlayer()) return;

        shaderActive = event.getCurse()==CurseType.BLINDNESS;
        toggleBlindShader(mc);
    }
    @SubscribeEvent
    public static void onLoggedIn(ClientPlayerNetworkEvent.LoggingIn event) {
        toggleBlindShader(Minecraft.getInstance());
    }

    //can't feel curse
    @SubscribeEvent
    public static void onRenderOverlay(RenderGuiOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;
        ICurse curse = PlayerCurse.getOrNull(mc.player);
        if (curse == null) return;

        if (curse.get()==CurseType.CANT_FEEL) {
            if (event.getOverlay() == VanillaGuiOverlay.PLAYER_HEALTH.type()
                    || event.getOverlay() == VanillaGuiOverlay.FOOD_LEVEL.type()) {

                event.setCanceled(true);
            }
        }
    }

}
