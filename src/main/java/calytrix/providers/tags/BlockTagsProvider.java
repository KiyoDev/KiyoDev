package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.util.ModTags;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public class BlockTagsProvider extends net.minecraft.data.tags.BlockTagsProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    
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
        final var resourceForgeTagsByType = ModTags.Blocks.resourceForgeTagsByType();
        
        blocks.forEach((type, block) -> tag(resourceForgeTagsByType.get(type)).add(block.getBlock().get()));
    }
    
    // private void forgeTags() {
        // tag(ModTags.Blocks.BLOCK_OF_ADAMANTINE)
        //     .add(CalytrixBlocks.BLOCK_OF_ADAMANTINE.get());
        // tag(ModTags.Blocks.BLOCK_OF_MITHRIL)
        //     .add(CalytrixBlocks.BLOCK_OF_MITHRIL.get());
    // }
}
