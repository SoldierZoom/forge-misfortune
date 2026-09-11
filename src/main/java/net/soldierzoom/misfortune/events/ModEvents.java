package net.soldierzoom.misfortune.events;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;

@Mod.EventBusSubscriber(modid = Misfortune.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {
    //blindness curse
    //applies blindness every sec if player has blindness curse
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        //only does at end of tick
        if (event.phase != TickEvent.Phase.END) return;

        //makes sure to apply server side
        if (!(event.player instanceof ServerPlayer player)) return;

        //apply ever sec
        if (player.tickCount % 20 == 0) {
            CurseType curse = PlayerCurse.get(player).get();
            //add blindness
            if (curse == CurseType.BLINDNESS) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.BLINDNESS,
                        40, 0,
                        false, false,
                        false
                ));
            }
        }
    }
    //prevents milk bucket clearing effect
    @SubscribeEvent
    public static void onMilkBucketFinish(LivingEntityUseItemEvent.Finish event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (event.getItem().is(Items.MILK_BUCKET)) {
            CurseType curse = PlayerCurse.get(player).get();

            if (curse == CurseType.BLINDNESS) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.BLINDNESS,
                        40,
                        0,
                        false,
                        false,
                        false
                ));
            }
        }
    }
}
