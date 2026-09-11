package net.soldierzoom.misfortune.events;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.network.ModNetwork;

@Mod.EventBusSubscriber(modid = Misfortune.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetupEvents {
    @SubscribeEvent
    public static void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModNetwork.registerPackets();
        });
    }
}
