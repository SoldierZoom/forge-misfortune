package net.soldierzoom.misfortune.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.soldierzoom.misfortune.Misfortune;

public class ModTags {
    public static class Blocks {
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.tryBuild(Misfortune.MOD_ID, name));
        }
        public static final TagKey<Block> MURKWOOD_LOGS = tag("murkwood_logs");

    }
    public static class Items {
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.tryBuild(Misfortune.MOD_ID, name));
        }
        public static final TagKey<Item> MURKWOOD_LOGS = tag("murkwood_logs");
    }
}
