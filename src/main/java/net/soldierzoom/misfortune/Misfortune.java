package net.soldierzoom.misfortune;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.cmds.ModCmds;
import net.soldierzoom.misfortune.curse.capability.AttachCurseCapability;
import net.soldierzoom.misfortune.curse.setup.CurseAssignment;
import net.soldierzoom.misfortune.curse.setup.CursePersistsOnDeath;
import net.soldierzoom.misfortune.item.ModCreativeModeTab;
import net.soldierzoom.misfortune.item.ModItems;
import net.soldierzoom.misfortune.util.ModWoodTypes;
import net.soldierzoom.misfortune.worldgen.tree.ModFoliagePlacers;
import net.soldierzoom.misfortune.worldgen.tree.ModTrunkPlacerTypes;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Misfortune.MOD_ID)
public class Misfortune {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "misfortune";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Misfortune(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTab.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModTrunkPlacerTypes.register(modEventBus);
        ModFoliagePlacers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(new AttachCurseCapability());
        MinecraftForge.EVENT_BUS.register(new CurseAssignment());
        MinecraftForge.EVENT_BUS.register(new CursePersistsOnDeath());

        //MinecraftForge.EVENT_BUS.register(new ModCmds());
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }


    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.MURKWOOD);
        }
    }
}
