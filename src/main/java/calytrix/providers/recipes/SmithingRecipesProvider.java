package calytrix.providers.recipes;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ItemResourceMaterialData;
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
    }
    
    private static void buildAdamantineRecipes(Consumer<FinishedRecipe> consumer) {
        final var ingotTags = ModTags.Items.resourceIngotsTagsByType();
        var adamantineTag = ingotTags.get(ItemResourceMaterialData.ADAMANTINE.getResourceType());
        
        buildRecipe(consumer, Items.NETHERITE_PICKAXE, adamantineTag, CalytrixItems.ADAMANTINE_PICKAXE.getItemObj());
        buildRecipe(consumer, Items.NETHERITE_AXE, adamantineTag, CalytrixItems.ADAMANTINE_AXE.getItemObj());
        buildRecipe(consumer, Items.NETHERITE_SHOVEL, adamantineTag, CalytrixItems.ADAMANTINE_SHOVEL.getItemObj());
        buildRecipe(consumer, Items.NETHERITE_HOE, adamantineTag, CalytrixItems.ADAMANTINE_HOE.getItemObj());
        buildRecipe(consumer, Items.NETHERITE_SWORD, adamantineTag, CalytrixItems.ADAMANTINE_SWORD.getItemObj());
    }
    
    private static <ITEM extends ItemLike> void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        TagKey<Item> material,
        RegistryObject<ITEM> result
    ) {
        buildRecipe(consumer, base, material, result, "has_%s".formatted(material.location().getPath()));
    }
    
    private static <ITEM extends ItemLike> void buildRecipe(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        TagKey<Item> material,
        RegistryObject<ITEM> result,
        String toUnlock
    ) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(base),
                                      Ingredient.of(material),
                                      result.get().asItem())
                            .unlocks(toUnlock, has(material))
                            .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
}
