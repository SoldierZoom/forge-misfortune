package net.soldierzoom.misfortune.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.custom.ModRotatedPillarBlock;
import net.soldierzoom.misfortune.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Misfortune.MOD_ID);

    public static final RegistryObject<Block> AURUM_LOG = registerBlock("aurum_log",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> AURUM_WOOD = registerBlock("aurum_wood",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> AURUM_PLANKS = registerBlock("aurum_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    //Caeruleum Wood
    public static final RegistryObject<Block> CAERULEUM_LOG = registerBlock("caeruleum_log",
            () -> new ModRotatedPillarBlock(TopSideMapColour(BlockBehaviour.Properties.copy(Blocks.WARPED_STEM),
                    MapColor.DIAMOND,MapColor.COLOR_BLACK)
                    .strength(5.0F)
            ));
    public static final RegistryObject<Block> CAERULEUM_WOOD = registerBlock("caeruleum_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.WARPED_HYPHAE)
                    .mapColor(MapColor.COLOR_BLACK)
                    .strength(5.0F)
            ));
    public static final RegistryObject<Block> STRIPPED_CAERULEUM_LOG = registerBlock("stripped_caeruleum_log",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_STEM)
                    .mapColor(MapColor.DIAMOND)
                    .strength(5.0F)
            ));
    public static final RegistryObject<Block> STRIPPED_CAERULEUM_WOOD = registerBlock("stripped_caeruleum_wood",
            () -> new ModRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_WARPED_HYPHAE)
                    .mapColor(MapColor.DIAMOND)
                    .strength(5.0F)
            ));

    public static final RegistryObject<Block> CAERULEUM_PLANKS = registerBlock("caeruleum_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.WARPED_PLANKS)
                    .mapColor(MapColor.DIAMOND)
                    .strength(5.0F,7.5F)
            ));
    public static final RegistryObject<Block> CAERULEUM_LEAVES = registerBlock("caeruleum_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.of().mapColor(MapColor.DIAMOND).strength(0.5F).randomTicks().sound(SoundType.GRASS)
                    .noOcclusion().isValidSpawn((state, getter, pos, entityType) -> false).isSuffocating((state, getter, pos) -> false)
                    .isViewBlocking((state, getter, pos) -> false).pushReaction(PushReaction.DESTROY).isRedstoneConductor((state, getter, pos) -> false)
            ));


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
