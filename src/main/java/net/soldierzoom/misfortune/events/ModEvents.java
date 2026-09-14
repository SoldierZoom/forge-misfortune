package net.soldierzoom.misfortune.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;

@Mod.EventBusSubscriber(modid = Misfortune.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    private static final MobEffectInstance PERMA_BLINDNESS = new MobEffectInstance(
            MobEffects.BLINDNESS,
            MobEffectInstance.INFINITE_DURATION,
            0,
            false,
            false,
            false
    );

    //blindness curse
    //applies blindness upon curse change
    @SubscribeEvent
    public static void onCurseChange(CurseChangedEvent event) {
        if (!(event.getPlayer() instanceof ServerPlayer player)) return;

        if(event.getCurse()==CurseType.BLINDNESS) {
            player.addEffect(PERMA_BLINDNESS);
        } else {
            player.removeEffect(MobEffects.BLINDNESS);
        }
    }
    //reapply on respawn
    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        if(PlayerCurse.get(player).get()==CurseType.BLINDNESS) {
            player.addEffect(PERMA_BLINDNESS);
        }
    }
    //re-add blindness when any effects removed
    @SubscribeEvent
    public static void onEffectsClear(MobEffectEvent.Remove event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if(PlayerCurse.get(player).get()==CurseType.BLINDNESS&&event.getEffect()==MobEffects.BLINDNESS) {
            event.setCanceled(true);
        }
    }
}
