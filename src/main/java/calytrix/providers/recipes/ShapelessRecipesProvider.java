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
import calytrix.block.ores.BlockOreData;
import calytrix.block.resources.BlockResourceData;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ItemResourceMaterialData;
import calytrix.item.resources.ResourceType;
import calytrix.util.ModTags;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class ShapelessRecipesProvider extends CalytrixRecipeProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public ShapelessRecipesProvider(
        DataGenerator dataGenerator,
        ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        uncraftStorageBlock(consumer, ModTags.Items.resourceStorageBlocksTagsByType(), "%s_ingot", "%s_ingot_from_%s_block",
                            CalytrixItems::getResource);
        
        uncraftStorageBlock(consumer, ModTags.Items.rawStorageBlocksTagsByType(), "raw_%s", "raw_%s_from_raw_%s_block",
                            CalytrixItems::getRawMaterial);
    }
    
    private <ITEM extends ItemLike> void uncraftStorageBlock(
        Consumer<FinishedRecipe> consumer,
        Map<ResourceType, TagKey<Item>> tags,
        String group,
        String recipeNameFormat,
        Function<ResourceType, ITEM> function
    ) {
        for (var entry : tags.entrySet()) {
            final var resourceType = entry.getKey();
            final var tag = entry.getValue();
            final var blockResourceData = BlockResourceData.fromType(resourceType);
            final var resourceName = blockResourceData.getResourceName();
            final var result = function.apply(resourceType);
            
            ShapelessRecipeBuilder.shapeless(result, 9)
                                  .group(group.formatted(resourceName))
                                  .requires(tag)
                                  .unlockedBy("has_%s".formatted(tag.location().getPath()), has(tag))
                                  .save(consumer, Calytrix.resourceLocation(recipeNameFormat.formatted(resourceName,
                                                                                                       resourceName)));
        }
    }
}
