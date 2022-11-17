package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ResourceType;
import calytrix.util.IBlockRegistryObject;
import calytrix.util.IItemRegistryObject;
import calytrix.util.IResource;
import calytrix.util.ModTags;
import com.google.common.collect.Multimap;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.Map;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {
    public ItemTagsProvider(
        DataGenerator dataGenerator,
        BlockTagsProvider blockTagsProvider,
        String modId,
        ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
        resourceIngotsTags();
        resourceStorageBlockItemTags();
        
        oreBlockItemTags();
        rawMaterialTags();
        rawStorageBlockTags();
        
        // TODO - dusts
    }
    
    private void resourceIngotsTags() {
        addTags(CalytrixItems.getResourceIngots(), ModTags.Items.resourceIngotsTagsByType(),
                (data, itemObj, item) -> tag(Tags.Items.INGOTS).add(item.asItem()));
    }
    
    private void resourceStorageBlockItemTags() {
        addBlockItemTags(CalytrixBlocks.getResourceStorageBlocks(), ModTags.Items.resourceStorageBlocksTagsByType(),
                         (data, itemObj, blockItem) -> tag(Tags.Items.STORAGE_BLOCKS).add(blockItem.asItem()));
    }
    
    private void oreBlockItemTags() {
        addTags(CalytrixBlocks.getOreBlocks(), ModTags.Items.oreBlocksTagsByType(), (data, itemObj, blockItem) -> {
            final var blockObj = itemObj.getBlockObj();
            final var ore = blockObj.get();
            final var oreType = ore.getOreType();
            
            tag(Tags.Items.ORES).add(blockItem.asItem());
            switch (oreType) {
                case STONE -> tag(Tags.Items.ORES_IN_GROUND_STONE).add(blockItem.asItem());
                case DEEPSLATE -> tag(Tags.Items.ORES_IN_GROUND_DEEPSLATE).add(blockItem.asItem());
                case NETHER -> tag(Tags.Items.ORES_IN_GROUND_NETHERRACK).add(blockItem.asItem());
                case END -> tag(ModTags.Items.ORES_IN_GROUND_END).add(blockItem.asItem());
            }
        });
    }
    
    private void rawMaterialTags() {
        addTags(CalytrixItems.getRawMaterials(), ModTags.Items.rawMaterialTagsByType(),
                (data, itemObj, rawMaterial) -> {
                    tag(Tags.Items.RAW_MATERIALS).add(rawMaterial.asItem());
                });
    }
    
    private void rawStorageBlockTags() {
        addBlockItemTags(CalytrixBlocks.getRawBlocks(), ModTags.Items.rawStorageBlocksTagsByType(),
                         (data, itemObj, rawMaterial) -> {
                             tag(Tags.Items.STORAGE_BLOCKS).add(CalytrixBlocks.getRawBlocks().get(data).getItem());
                         });
    }
    
    private <DATA extends IResource, OBJ extends IItemRegistryObject> void addTags(
        Map<DATA, OBJ> items,
        Map<ResourceType, TagKey<Item>> tagsByType,
        TriConsumer<DATA, OBJ, ItemLike> consumer
    ) {
        items.forEach((data, itemObj) -> {
            final var item = itemObj.getItem();
            final var type = data.getResourceType();
            final var tag = tagsByType.get(type);
            
            tag(tag).add(item);
            
            consumer.accept(data, itemObj, item);
        });
    }
    
    private <DATA extends IResource, OBJ extends IBlockRegistryObject> void addBlockItemTags(
        Map<DATA, OBJ> items,
        Map<ResourceType, TagKey<Item>> tagsByType,
        TriConsumer<DATA, OBJ, ItemLike> consumer
    ) {
        items.forEach((data, itemObj) -> {
            final var item = itemObj.getItem();
            final var type = data.getResourceType();
            final var tag = tagsByType.get(type);
            
            tag(tag).add(item);
            
            consumer.accept(data, itemObj, item);
        });
    }
    
    private <DATA extends IResource, OBJ extends IBlockRegistryObject> void addTags(
        Multimap<DATA, OBJ> items,
        Multimap<ResourceType, TagKey<Item>> tagsByType,
        TriConsumer<DATA, OBJ, ItemLike> consumer
    ) {
        items.forEach((data, itemObj) -> {
            final var item = itemObj.getItem();
            final var type = data.getResourceType();
            final var tags = tagsByType.get(type);
            
            for (var tag : tags) {
                tag(tag).add(item);
            }
            
            consumer.accept(data, itemObj, item);
        });
    }
}
