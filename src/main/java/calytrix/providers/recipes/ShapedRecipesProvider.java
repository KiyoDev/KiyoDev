package calytrix.providers.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ItemResourceIngotData;
import calytrix.util.ModTags;

import java.util.function.Consumer;

public class ShapedRecipesProvider extends CalytrixRecipeProvider {
    
    public ShapedRecipesProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        final var ingotTags = ModTags.Items.resourceForgeTagsByType();
        var mithrilTag = ingotTags.get(ItemResourceIngotData.MITHRIL);
        
        pickaxe(consumer, mithrilTag, CalytrixItems.MITHRIL_PICKAXE);
        axe(consumer, mithrilTag, CalytrixItems.MITHRIL_AXE);
        shovel(consumer, mithrilTag, CalytrixItems.MITHRIL_SHOVEL);
        hoe(consumer, mithrilTag, CalytrixItems.MITHRIL_HOE);
        sword(consumer, mithrilTag, CalytrixItems.MITHRIL_SWORD);
    }
    
    private static <ITEM extends Item> void pickaxe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<ITEM> result
    ) {
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
    
    private static <ITEM extends Item> void axe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<ITEM> result
    ) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern("ii ")
                           .pattern("is ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
    
    private static <ITEM extends Item> void shovel(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<ITEM> result
    ) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern(" i ")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
    
    private static <ITEM extends Item> void hoe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<ITEM> result
    ) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern("ii ")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
    
    private static <ITEM extends Item> void sword(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<ITEM> result
    ) {
        ShapedRecipeBuilder.shaped(result.get())
                           .pattern(" i ")
                           .pattern(" i ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, Calytrix.resourceLocation("tools/%s".formatted(result.getId().getPath())));
    }
}
