package net.soldierzoom.misfortune.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Misfortune.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ModItems.HAUNTED_CRESCENT);
        simpleBlockItem(ModBlocks.MURKWOOD_DOOR,"item");
        simpleBlockItem(ModBlocks.MURKWOOD_SAPLING);
        fenceItem(ModBlocks.MURKWOOD_FENCE,ModBlocks.MURKWOOD_PLANKS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID,"item/"+item.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> block,String path) {
        return withExistingParent(block.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID,path+"/"+block.getId().getPath()));
    }
    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> block) {
        return simpleBlockItem(block,"block");
    }
    public void fenceItem(RegistryObject<Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID, "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

}
