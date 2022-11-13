package calytrix.providers.recipes;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ItemResourceIngotData;
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
        final var ingotTags = ModTags.Items.resourceIngotsTagsByType();
        var adamantineTag = ingotTags.get(ItemResourceIngotData.ADAMANTINE.resourceType());
        
        buildRecipe(consumer, Items.NETHERITE_PICKAXE, adamantineTag, CalytrixItems.ADAMANTINE_PICKAXE);
        buildRecipe(consumer, Items.NETHERITE_AXE, adamantineTag, CalytrixItems.ADAMANTINE_AXE);
        buildRecipe(consumer, Items.NETHERITE_SHOVEL, adamantineTag, CalytrixItems.ADAMANTINE_SHOVEL);
        buildRecipe(consumer, Items.NETHERITE_HOE, adamantineTag, CalytrixItems.ADAMANTINE_HOE);
        buildRecipe(consumer, Items.NETHERITE_SWORD, adamantineTag, CalytrixItems.ADAMANTINE_SWORD);
    }
    
    private static void addToolSet(
        Consumer<FinishedRecipe> consumer,
        ItemLike base,
        TagKey<Item> material,
        RegistryObject<ItemLike> pickaxe,
        RegistryObject<ItemLike> axe,
        RegistryObject<ItemLike> shovel,
        RegistryObject<ItemLike> hoe,
        RegistryObject<ItemLike> sword
    ) {
        buildRecipe(consumer, base, material, pickaxe);
        buildRecipe(consumer, base, material, axe);
        buildRecipe(consumer, base, material, shovel);
        buildRecipe(consumer, base, material, hoe);
        buildRecipe(consumer, base, material, sword);
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
