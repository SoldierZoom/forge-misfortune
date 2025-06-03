package net.soldierzoom.misfortune.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Misfortune.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //murkwood wood
        logBlock(((RotatedPillarBlock) ModBlocks.MURKWOOD_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.MURKWOOD_WOOD.get()),
                blockTexture(ModBlocks.MURKWOOD_LOG.get()),
                blockTexture(ModBlocks.MURKWOOD_LOG.get())
        );
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MURKWOOD_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_MURKWOOD_LOG.get()),
                ResourceLocation.tryBuild(Misfortune.MOD_ID,"block/stripped_murkwood_log_top")
        );
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MURKWOOD_WOOD.get()),
                blockTexture(ModBlocks.STRIPPED_MURKWOOD_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_MURKWOOD_LOG.get())
        );
        blockItem(ModBlocks.MURKWOOD_LOG);
        blockItem(ModBlocks.MURKWOOD_WOOD);
        blockItem(ModBlocks.STRIPPED_MURKWOOD_LOG);
        blockItem(ModBlocks.STRIPPED_MURKWOOD_WOOD);

        doorBlockWithRenderType(((DoorBlock) ModBlocks.MURKWOOD_DOOR.get()),
                ResourceLocation.tryBuild(Misfortune.MOD_ID,"block/murkwood_door_bottom"),
                ResourceLocation.tryBuild(Misfortune.MOD_ID,"block/murkwood_door_top"),
                "cutout"
        );
        stairsBlock(((StairBlock) ModBlocks.MURKWOOD_STAIRS.get()),
                blockTexture(ModBlocks.MURKWOOD_PLANKS.get())
        );
        blockItem(ModBlocks.MURKWOOD_STAIRS);

        blockWithItem(ModBlocks.MURKWOOD_PLANKS);
        leavesBlock(ModBlocks.MURKWOOD_LEAVES);
        saplingBlock(ModBlocks.MURKWOOD_SAPLING);


    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.tryParse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(Misfortune.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
