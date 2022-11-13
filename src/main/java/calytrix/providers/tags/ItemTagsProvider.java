package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.block.ores.BlockOreType;
import calytrix.item.CalytrixItems;
import calytrix.util.ModTags;

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
    }
    
    private void resourceIngotsTags() {
        final var ingots = CalytrixItems.getResourceIngots();
        final var resourceForgeTagsByType = ModTags.Items.resourceIngotsTagsByType();
        
        ingots.forEach((data, ingot) -> {
            final var item = ingot.getItem().get();
            final var type = data.resourceType();
            final var tag = resourceForgeTagsByType.get(type);
            
            tag(tag).add(item);
        });
    }
    
    private void resourceStorageBlockItemTags() {
        final var blocks = CalytrixBlocks.getResourceStorageBlocks();
        final var storageBlocksTagsByType = ModTags.Items.resourceStorageBlocksTagsByType();
        
        blocks.forEach((data, block) -> {
            final var blockItem = block.getItem().get();
            final var type = data.getResourceType();
            final var tag = storageBlocksTagsByType.get(type);
            
            tag(tag).add(blockItem);
            tag(Tags.Items.STORAGE_BLOCKS).add(blockItem);
        });
    }
    
    private void oreBlockItemTags() {
        final var ores = CalytrixBlocks.getOreBlocks();
        final var oreTagsByType = ModTags.Items.oreBlocksTagsByType();
        
        ores.forEach((data, block) -> {
            final var blockItem = block.getItem().get();
            final var type = data.resourceType();
            final var tag = oreTagsByType.get(type);
            
            tag(tag).add(blockItem);
            tag(Tags.Items.ORES).add(blockItem);
    
            for (BlockOreType oreType : data.getTypes()) {
                tag(oreType.getInGroundTag()).add(blockItem);
            }
        });
    }
}
