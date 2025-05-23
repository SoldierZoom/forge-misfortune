package net.soldierzoom.misfortune.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
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
        blockWithItem(ModBlocks.AURUM_PLANKS);

        //caeruleum wood
        logBlock(((RotatedPillarBlock) ModBlocks.CAERULEUM_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.CAERULEUM_WOOD.get()),
                blockTexture(ModBlocks.CAERULEUM_LOG.get()),
                blockTexture(ModBlocks.CAERULEUM_LOG.get())
        );
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CAERULEUM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_CAERULEUM_LOG.get()),
                ResourceLocation.tryBuild(Misfortune.MOD_ID,"block/stripped_caeruleum_log_top")
        );
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_CAERULEUM_WOOD.get()),
                blockTexture(ModBlocks.STRIPPED_CAERULEUM_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_CAERULEUM_LOG.get())
        );
        blockItem(ModBlocks.CAERULEUM_LOG);
        blockItem(ModBlocks.CAERULEUM_WOOD);
        blockItem(ModBlocks.STRIPPED_CAERULEUM_LOG);
        blockItem(ModBlocks.STRIPPED_CAERULEUM_WOOD);

        blockWithItem(ModBlocks.CAERULEUM_PLANKS);

        leavesBlock(ModBlocks.CAERULEUM_LEAVES);

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
