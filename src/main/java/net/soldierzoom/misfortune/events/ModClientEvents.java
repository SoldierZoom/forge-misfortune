package net.soldierzoom.misfortune.events;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
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

    private static final ResourceLocation CURSE_SHADER =
            ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID, "shaders/post/blind.json");

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) return;

        ICurse curse = PlayerCurse.getOrNull(mc.player);
        if (curse == null) return;

        Boolean shouldBeActive = curse.get()==CurseType.BLINDNESS;


        if (shouldBeActive && !shaderActive) {
            mc.gameRenderer.loadEffect(CURSE_SHADER);
            shaderActive = true;
        }

        if (!shouldBeActive && shaderActive) {
            mc.gameRenderer.shutdownEffect();
            shaderActive = false;
        }
    }
    @SubscribeEvent
    public static void onLoggedOut(ClientPlayerNetworkEvent.LoggingOut event) {
        shaderActive = false;
    }
}
