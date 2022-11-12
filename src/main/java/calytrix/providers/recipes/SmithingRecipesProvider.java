package calytrix.providers.recipes;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.util.ModTags;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import java.util.function.Consumer;

public class SmithingRecipesProvider extends CalytrixRecipeProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public SmithingRecipesProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        buildAdamantineRecipes(consumer);
        LOGGER.info("generating smithing recipes...");
    }
    
    private static void buildAdamantineRecipes(Consumer<FinishedRecipe> consumer) {
        buildRecipe(consumer, Items.NETHERITE_PICKAXE, ModTags.Items.ADAMANTINE_INGOT, CalytrixItems.ADAMANTINE_PICKAXE);
        buildRecipe(consumer, Items.NETHERITE_AXE, CalytrixItems.ADAMANTINE_INGOT, CalytrixItems.ADAMANTINE_AXE);
        buildRecipe(consumer, Items.NETHERITE_SHOVEL, CalytrixItems.ADAMANTINE_INGOT, CalytrixItems.ADAMANTINE_SHOVEL);
        buildRecipe(consumer, Items.NETHERITE_HOE, CalytrixItems.ADAMANTINE_INGOT, CalytrixItems.ADAMANTINE_HOE);
        buildRecipe(consumer, Items.NETHERITE_SWORD, CalytrixItems.ADAMANTINE_INGOT, CalytrixItems.ADAMANTINE_SWORD);
    }
    
    private static void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        RegistryObject<Item> material,
        RegistryObject<Item> result
    ) {
        final String matName = material.getId().getPath();
        buildRecipe(consumer, base, material, result, "has_%s".formatted(matName));
    }
    
    private static void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        RegistryObject<Item> material,
        RegistryObject<Item> result,
        String toUnlock
    ) {
        final Item mat = material.get();
        
        UpgradeRecipeBuilder.smithing(Ingredient.of(base),
                                      Ingredient.of(mat),
                                      result.get())
                            .unlocks(toUnlock, has(mat))
                            .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
    
    private static void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        TagKey<Item> material,
        RegistryObject<Item> result
    ) {
        buildRecipe(consumer, base, material, result, "has_%s".formatted(material.location().getPath()));
    }
    
    private static void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        TagKey<Item> material,
        RegistryObject<Item> result,
        String toUnlock
    ) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(base),
                                      Ingredient.of(material),
                                      result.get())
                            .unlocks(toUnlock, has(material))
                            .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
}
