package net.soldierzoom.misfortune.util;

import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.soldierzoom.misfortune.Misfortune;

public class ModWoodTypes {
    private static final BlockSetType MURKWOOD_BLOCK_SET =  new BlockSetType("murkwood");
    public static final WoodType MURKWOOD = WoodType.register(new WoodType(Misfortune.MOD_ID + ":murkwood",MURKWOOD_BLOCK_SET ));
}
