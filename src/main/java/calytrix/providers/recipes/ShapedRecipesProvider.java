package calytrix.providers.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.Calytrix;
import calytrix.block.CalytrixBlocks;
import calytrix.block.ores.BlockOreData;
import calytrix.block.resources.BlockResourceData;
import calytrix.item.CalytrixItems;
import calytrix.item.ItemRegistryObject;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.BaseAxeItem;
import calytrix.item.tools.BaseHoeItem;
import calytrix.item.tools.BasePickaxeItem;
import calytrix.item.tools.BaseShovelItem;
import calytrix.item.tools.BaseSwordItem;
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
        
        for (var entry : ModTags.Items.resourceIngotsTagsByType().entrySet()) {
            final var resourceType = entry.getKey();
            final var tag = entry.getValue();
            final var blockResourceData = BlockResourceData.fromType(resourceType);
            final var storageBlock = CalytrixBlocks.getResourceStorageBlocks().get(blockResourceData);
            
            compactRecipe(consumer, tag, storageBlock.getBlock(),
                          Calytrix.resourceLocation("compact/%s".formatted(blockResourceData.blockOfName())));
        }
        
        for (var entry : ModTags.Items.rawMaterialTagsByType().entrySet()) {
            final var resourceType = entry.getKey();
            final var tag = entry.getValue();
            final var blockResourceData = BlockOreData.fromType(resourceType);
            final var blockObj = CalytrixBlocks.getRawBlocks().get(blockResourceData);
    
            compactRecipe(consumer, tag, blockObj.getBlock(),
                          Calytrix.resourceLocation("compact/block_of_raw_%s".formatted(blockResourceData.resourceName())));
        }
    }
    
    private static <ITEM extends ItemLike> void addToolSet(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ItemRegistryObject<BasePickaxeItem> pickaxe,
        ItemRegistryObject<BaseAxeItem> axe,
        ItemRegistryObject<BaseShovelItem> shovel,
        ItemRegistryObject<BaseHoeItem> hoe,
        ItemRegistryObject<BaseSwordItem> sword
    ) {
        pickaxe(consumer, ingot, pickaxe.getItem(), Calytrix.toolsResourceLocation(pickaxe.getItemObj()));
        axe(consumer, ingot, axe.getItem(), Calytrix.toolsResourceLocation(axe.getItemObj()));
        shovel(consumer, ingot, shovel.getItem(), Calytrix.toolsResourceLocation(shovel.getItemObj()));
        hoe(consumer, ingot, hoe.getItem(), Calytrix.toolsResourceLocation(hoe.getItemObj()));
        sword(consumer, ingot, sword.getItem(), Calytrix.toolsResourceLocation(sword.getItemObj()));
    }
    
    private static <ITEM extends ItemLike> void pickaxe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern("iii")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, resourceLocation);
    }
    
    private static <ITEM extends ItemLike> void axe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern("ii ")
                           .pattern("is ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, resourceLocation);
    }
    
    private static <ITEM extends ItemLike> void shovel(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern(" i ")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, resourceLocation);
    }
    
    private static <ITEM extends ItemLike> void hoe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern("ii ")
                           .pattern(" s ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, resourceLocation);
    }
    
    private static <ITEM extends ItemLike> void sword(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> ingot,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern(" i ")
                           .pattern(" i ")
                           .pattern(" s ")
                           .define('i', ingot)
                           .define('s', Tags.Items.RODS_WOODEN)
                           .unlockedBy("has_%s".formatted(ingot.location().getPath()), has(ingot))
                           .save(consumer, resourceLocation);
    }
    
    private static <ITEM extends ItemLike> void compactRecipe(
        Consumer<FinishedRecipe> consumer,
        TagKey<Item> itemTag,
        ITEM result,
        ResourceLocation resourceLocation
    ) {
        ShapedRecipeBuilder.shaped(result)
                           .pattern("###")
                           .pattern("###")
                           .pattern("###")
                           .define('#', itemTag)
                           .unlockedBy("has_%s".formatted(itemTag.location().getPath()), has(itemTag))
                           .save(consumer, resourceLocation);
    }
}
