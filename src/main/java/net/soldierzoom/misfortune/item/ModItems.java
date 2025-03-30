package net.soldierzoom.misfortune.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Misfortune.MOD_ID);

    public static final RegistryObject<Item> NIGHT_FAIRY_SOUL =
            ITEMS.register("night_fairy_soul",() -> new Item(new Item.Properties()));






    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
