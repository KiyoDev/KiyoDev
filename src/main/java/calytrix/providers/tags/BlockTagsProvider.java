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
        final var blocks = CalytrixBlocks.getResourceStorageBlocks();
        final var resourceForgeTagsByType = ModTags.Blocks.resourceForgeTagsByType();
        
        blocks.forEach((type, block) -> tag(resourceForgeTagsByType.get(type)).add(block.getBlockObj().get()));
    }
}
