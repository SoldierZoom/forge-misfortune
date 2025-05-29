package net.soldierzoom.misfortune.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.soldierzoom.misfortune.Misfortune;
import net.soldierzoom.misfortune.block.ModBlocks;
import net.soldierzoom.misfortune.util.ModTags;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        planksFromLogs(consumer, ModBlocks.MURKWOOD_PLANKS.get(), ModTags.Items.MURKWOOD_LOGS,4);
        woodFromLogs(consumer, ModBlocks.MURKWOOD_WOOD.get(),ModBlocks.MURKWOOD_LOG.get());
        woodFromLogs(consumer, ModBlocks.STRIPPED_MURKWOOD_WOOD.get(),ModBlocks.STRIPPED_MURKWOOD_LOG.get());
        XFromPlanks(consumer,stairBuilder(ModBlocks.MURKWOOD_STAIRS.get(),Ingredient.of(ModBlocks.MURKWOOD_PLANKS.get())),ModBlocks.MURKWOOD_PLANKS.get());
        XFromPlanks(consumer,doorBuilder(ModBlocks.MURKWOOD_DOOR.get(),Ingredient.of(ModBlocks.MURKWOOD_PLANKS.get())),ModBlocks.MURKWOOD_PLANKS.get());
    }





    //ore recipes
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike),
                    has(itemlike)).save(pFinishedRecipeConsumer, Misfortune.MOD_ID + ":" + (pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
    //wood
    protected static void XFromPlanks(Consumer<FinishedRecipe> consumer, RecipeBuilder recipeBuilder, ItemLike pPlanks) {
        recipeBuilder.unlockedBy("has_planks", has(pPlanks)).save(consumer);
    }
}
