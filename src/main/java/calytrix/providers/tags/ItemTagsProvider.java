package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import calytrix.item.resources.ResourceType;
import calytrix.util.IItemRegistryObject;
import calytrix.util.IResource;
import calytrix.util.ModTags;
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
    }
    
    private void resourceIngotsTags() {
        addTags(CalytrixItems.getResourceIngots(), ModTags.Items.resourceIngotsTagsByType(),
                (data, itemObj, item) -> tag(Tags.Items.INGOTS).add(item));
    }
    
    private void resourceStorageBlockItemTags() {
        addTags(CalytrixBlocks.getResourceStorageBlocks(), ModTags.Items.resourceStorageBlocksTagsByType(),
                (data, itemObj, blockItem) -> tag(Tags.Items.STORAGE_BLOCKS).add(blockItem));
    }
    
    private void oreBlockItemTags() {
        addTags(CalytrixBlocks.getOreBlocks(), ModTags.Items.oreBlocksTagsByType(), (data, itemObj, blockItem) -> {
            final var blockObj = itemObj.getBlockObj();
            final var ore = blockObj.get();
            final var oreType = ore.getOreType();
            
            tag(Tags.Items.ORES).add(blockItem);
            tag(oreType.getInGroundTag()).add(blockItem);
        });
    }
    
    private <DATA extends IResource, ITEM extends Item, OBJ extends IItemRegistryObject<ITEM>> void addTags(
        Map<DATA, OBJ> items,
        Map<ResourceType, TagKey<Item>> tagsByType,
        TriConsumer<DATA, OBJ, ITEM> consumer
    ) {
        items.forEach((data, itemObj) -> {
            final var item = itemObj.getItem();
            final var type = data.getResourceType();
            final var tag = tagsByType.get(type);
            
            tag(tag).add(item);
            
            consumer.accept(data, itemObj, item);
        });
    }
}
