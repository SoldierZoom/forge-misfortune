package net.soldierzoom.misfortune.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.custom.ModRotatedPillarBlock;
import net.soldierzoom.misfortune.item.ModItems;
import net.soldierzoom.misfortune.util.ModWoodTypes;
import net.soldierzoom.misfortune.worldgen.tree.MurkwoodTreeGrower;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Misfortune.MOD_ID);

    //Murkwood Wood
    //logs
    public static final RegistryObject<Block> MURKWOOD_LOG = registerBlock("murkwood_log",
            () -> new ModRotatedPillarBlock(TopSideMapColour(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM),
                    MapColor.COLOR_BLACK,MapColor.TERRACOTTA_BROWN)
                    .strength(4F)
                    .sound(SoundType.WOOD)
            ));
    public static final RegistryObject<Block> MURKWOOD_WOOD = registerBlock("murkwood_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE)
                    .mapColor(MapColor.TERRACOTTA_BROWN)
                    .strength(4F)
                    .sound(SoundType.WOOD)
            ));
    public static final RegistryObject<Block> STRIPPED_MURKWOOD_LOG = registerBlock("stripped_murkwood_log",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F)
                    .sound(SoundType.WOOD)
            ));
    public static final RegistryObject<Block> STRIPPED_MURKWOOD_WOOD = registerBlock("stripped_murkwood_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F)
                    .sound(SoundType.WOOD)
            ));
    //planks & stuff
    public static final RegistryObject<Block> MURKWOOD_PLANKS = registerBlock("murkwood_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F,6F)
            ));
    public static final RegistryObject<Block> MURKWOOD_STAIRS = registerBlock("murkwood_stairs",
            () -> new StairBlock(() -> MURKWOOD_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(MURKWOOD_PLANKS.get())
            ));
    public static final RegistryObject<Block> MURKWOOD_SLAB = registerBlock("murkwood_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_SLAB)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F,6F)
            ));
    public static final RegistryObject<Block> MURKWOOD_FENCE = registerBlock("murkwood_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F,6F)
            ));
    public static final RegistryObject<Block> MURKWOOD_FENCE_GATE = registerBlock("murkwood_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_FENCE_GATE)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(4F,6F),
                    ModWoodTypes.MURKWOOD
            ));
    public static final RegistryObject<Block> MURKWOOD_DOOR = registerBlock("murkwood_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_DOOR)
                    .mapColor(MapColor.DIAMOND)
                    .strength(6F)
                    ,ModWoodTypes.MURKWOOD.setType()
            ));
    //leaves & sapling
    public static final RegistryObject<Block> MURKWOOD_LEAVES = registerBlock("murkwood_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.4F).randomTicks().sound(SoundType.GRASS)
                    .noOcclusion().isValidSpawn((state, getter, pos, entityType) -> false).isSuffocating((state, getter, pos) -> false)
                    .isViewBlocking((state, getter, pos) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, getter, pos) -> false)
            ));
    public static final RegistryObject<Block> MURKWOOD_SAPLING = registerBlock("murkwood_sapling",
            () -> new SaplingBlock(new MurkwoodTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)
                    .mapColor(MapColor.PLANT)
            ));
    //signs & boats
    //TBA




    private static BlockBehaviour.Properties TopSideMapColour(BlockBehaviour.Properties pProperties, MapColor topCol, MapColor sideCol) {
        pProperties.mapColor((colour) -> colour.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topCol : sideCol);
        return pProperties;
    }

    //registering block
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    //registers item for block given
    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
