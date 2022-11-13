package calytrix.providers.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import calytrix.Calytrix;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ResourceType;
import calytrix.util.ModTags;

import java.util.function.Consumer;

public class ShapedRecipesProvider extends CalytrixRecipeProvider {
    
    public ShapedRecipesProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator, existingFileHelper);
    }
    
    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        final var ingotTags = ModTags.Items.resourceIngotsTagsByType();
        var mithrilTag = ingotTags.get(ResourceType.MITHRIL);
        
        addToolSet(consumer, mithrilTag, CalytrixItems.MITHRIL_PICKAXE, CalytrixItems.MITHRIL_AXE, CalytrixItems.MITHRIL_SHOVEL,
                   CalytrixItems.MITHRIL_HOE, CalytrixItems.MITHRIL_SWORD);
    }
    
    private static <ITEM extends ItemLike> void addToolSet(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        RegistryObject<PickaxeItem> pickaxe,
        RegistryObject<AxeItem> axe,
        RegistryObject<ShovelItem> shovel,
        RegistryObject<HoeItem> hoe,
        RegistryObject<SwordItem> sword
    ) {
        pickaxe(consumer, ingot, pickaxe);
        axe(consumer, ingot, axe);
        shovel(consumer, ingot, shovel);
        hoe(consumer, ingot, hoe);
        sword(consumer, ingot, sword);
    }
    
    private static <ITEM extends ItemLike> void pickaxe(
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
    
    private static <ITEM extends ItemLike> void axe(
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
    
    private static <ITEM extends ItemLike> void shovel(
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
    
    private static <ITEM extends ItemLike> void hoe(
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
    
    private static <ITEM extends ItemLike> void sword(
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
