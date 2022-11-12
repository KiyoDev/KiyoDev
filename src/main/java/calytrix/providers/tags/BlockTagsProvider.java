package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.util.ModTags;
import org.jetbrains.annotations.Nullable;

public class BlockTagsProvider extends net.minecraft.data.tags.BlockTagsProvider {
    
    public BlockTagsProvider(
        DataGenerator dataGenerator,
        String modId,
        @Nullable ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
        resourceBlockTags();
    }
    
    private void resourceBlockTags() {
        final var blocks = CalytrixBlocks.getResourceBlocks();
        final var tagsByResource = ModTags.Blocks.tagsByResource();
        
        blocks.forEach((type, block) -> {
            tag(tagsByResource.get(type)).add(block.getBlock().get());
        });
    }
    
    private void forgeTags() {
        // tag(ModTags.Blocks.BLOCK_OF_ADAMANTINE)
        //     .add(CalytrixBlocks.BLOCK_OF_ADAMANTINE.get());
        // tag(ModTags.Blocks.BLOCK_OF_MITHRIL)
        //     .add(CalytrixBlocks.BLOCK_OF_MITHRIL.get());
    }
}
