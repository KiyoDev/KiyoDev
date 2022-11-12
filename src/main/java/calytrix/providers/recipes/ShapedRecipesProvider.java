package calytrix.providers.recipes;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.util.ModTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Consumer;

public class ShapedRecipesProvider extends CalytrixRecipeProvider {
    
    public ShapedRecipesProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        pickaxe(consumer, ModTags.Items.MITHRIL_INGOT, CalytrixItems.MITHRIL_PICKAXE);
        axe(consumer, ModTags.Items.MITHRIL_INGOT, CalytrixItems.MITHRIL_AXE);
        // ShapedRecipeBuilder.shaped(ItemRegistry.ADAMANTINE_AXE.get())
        //                    .pattern("ii ")
        //                    .pattern("is ")
        //                    .pattern(" s ")
        //                    .define('i', ModTags.Items.ADAMANTINE_INGOT)
        //                    .define('s', ModTags.Items.WOOD_STICK)
        //                    .unlockedBy("has_adamantine_ingot", has(ItemRegistry.ADAMANTINE_INGOT.get()))
        //                    .save(consumer, Calytrix.resourceLocation("tools/adamantine_axe"));
    }
    
    private static void pickaxe(Consumer<FinishedRecipe> consumer, TagKey<Item> ingot, RegistryObject<Item> result) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern("iii")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           // .define('s', ModTags.Items.WOOD_STICK)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
    
    private static void axe(Consumer<FinishedRecipe> consumer, TagKey<Item> ingot, RegistryObject<Item> result) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern("ii ")
                           .pattern("is ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
}
