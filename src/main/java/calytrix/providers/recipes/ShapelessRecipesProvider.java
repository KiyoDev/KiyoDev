package calytrix.providers.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.Calytrix;
import calytrix.block.CalytrixBlocks;
import calytrix.block.ores.BlockOreData;
import calytrix.block.resources.BlockResourceData;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ItemResourceMaterialData;
import calytrix.util.ModTags;

import java.util.function.Consumer;

public class ShapelessRecipesProvider extends CalytrixRecipeProvider {
    
    public ShapelessRecipesProvider(
        DataGenerator dataGenerator,
        ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        for (var entry : ModTags.Items.resourceStorageBlocksTagsByType().entrySet()) {
            final var resourceType = entry.getKey();
            final var tag = entry.getValue();
            final var blockResourceData = BlockResourceData.fromType(resourceType);
            final var resourceName = blockResourceData.resourceName();
            final var result = CalytrixItems.getResourceIngots()
                                            .get(ItemResourceMaterialData.fromType(resourceType))
                                            .getItem();
            
            decompress(consumer, tag, result, "%s_ingot".formatted(resourceName),
                       Calytrix.resourceLocation("%s_ingot_from_%s_block".formatted(resourceName, resourceName)));
        }
        
        for (var entry : ModTags.Items.rawStorageBlocksTagsByType().entrySet()) {
            final var resourceType = entry.getKey();
            final var tag = entry.getValue();
            final var blockResourceData = BlockResourceData.fromType(resourceType);
            final var resourceName = blockResourceData.resourceName();
            final var result = CalytrixItems.getRawMaterials()
                                            .get(BlockOreData.fromType(resourceType))
                                            .getItem();
            
            decompress(consumer, tag, result, "raw_%s".formatted(resourceName),
                       Calytrix.resourceLocation("raw_%s_from_raw_%s_block".formatted(resourceName, resourceName)));
        }
    }
    
    private static <ITEM extends ItemLike> void decompress(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> itemTag,
        ITEM result,
        String group,
        ResourceLocation resourceLocation
    ) {
        ShapelessRecipeBuilder.shapeless(result, 9)
                              .group(group)
                              .requires(itemTag)
                              .unlockedBy("has_%s".formatted(itemTag.location().getPath()), has(itemTag))
                              .save(consumer, resourceLocation);
    }
}
