package net.soldierzoom.misfortune.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;

public class ModCreativeModeTab {
    public static final DeferredRegister<net.minecraft.world.item.CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Misfortune.MOD_ID);

    public static final RegistryObject<net.minecraft.world.item.CreativeModeTab> MISFORTUNE_TAB = CREATIVE_MODE_TABS.register("misfortune_tab",
            () -> net.minecraft.world.item.CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.NIGHT_FAIRY_SOUL.get()))
                    .title(Component.translatable("creativetab.misfortune_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
                            pOutput.accept(item.get());
                        }
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
