package net.soldierzoom.misfortune.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    private static final DeferredRegister<Block> DROP_SELF = DeferredRegister.create(ForgeRegistries.BLOCKS, Misfortune.MOD_ID);

    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Murkwood wood
        this.dropSelf(ModBlocks.MURKWOOD_LOG.get());
        this.dropSelf(ModBlocks.MURKWOOD_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MURKWOOD_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MURKWOOD_WOOD.get());
        this.dropSelf(ModBlocks.MURKWOOD_PLANKS.get());
        this.dropSelf(ModBlocks.MURKWOOD_STAIRS.get());
        this.add(ModBlocks.MURKWOOD_LEAVES.get(), block ->
                createLeavesDrops(block,ModBlocks.MURKWOOD_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.MURKWOOD_DOOR.get(), block ->
                createDoorTable(ModBlocks.MURKWOOD_DOOR.get()));
        this.dropSelf(ModBlocks.MURKWOOD_SAPLING.get());
        this.add(ModBlocks.MURKWOOD_SLAB.get(), block ->
                createSlabItemTable(ModBlocks.MURKWOOD_SLAB.get()));
        this.dropSelf(ModBlocks.MURKWOOD_FENCE.get());
        this.dropSelf(ModBlocks.MURKWOOD_FENCE_GATE.get());
        this.dropSelf(ModBlocks.MURKWOOD_TRAPDOOR.get());
        this.dropSelf(ModBlocks.MURKWOOD_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.MURKWOOD_BUTTON.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
