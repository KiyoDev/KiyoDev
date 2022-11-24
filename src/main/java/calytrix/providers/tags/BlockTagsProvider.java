package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.item.resources.ResourceType;
import calytrix.util.IBlockRegistryObject;
import calytrix.util.IResource;
import calytrix.util.ModTags;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import org.apache.logging.log4j.util.TriConsumer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

import java.util.Map;

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
        oreBlockTags();
        rawBlockTags();
    }
    
    private void resourceBlockTags() {
        addTags(CalytrixBlocks.getResourceStorageBlocks(), ModTags.Blocks.resourceForgeTags(), (data, blockObj, block) -> {
            final var resourceBlock = blockObj.getBlock();
            final var requiredToolTierTag = data.requiredToolTier();
        
            tag(Tags.Blocks.STORAGE_BLOCKS).add(resourceBlock);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(resourceBlock);
            tag(requiredToolTierTag).add(resourceBlock);
        });
    }
    
    private void oreBlockTags() {
        addTags(CalytrixBlocks.getOreBlocks(), ModTags.Blocks.oresForgeTags(), (data, blockObj, block) -> {
            final var ore = blockObj.getBlock();
            final var requiredToolTierTag = data.requiredToolTier();
            final var oreType = ore.getOreType();
    
            tag(Tags.Blocks.ORES).add(ore);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ore);
            tag(requiredToolTierTag).add(ore);
    
            switch (oreType) {
                case STONE -> tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ore);
                case DEEPSLATE -> tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(ore);
                case NETHER -> tag(Tags.Blocks.ORES_IN_GROUND_NETHERRACK).add(ore);
                case END -> tag(ModTags.Blocks.ORES_IN_GROUND_END).add(ore);
            }
        });
    }
    
    private void rawBlockTags() {
        addTags(CalytrixBlocks.getRawBlocks(), ModTags.Blocks.rawStorageBlockForgeTags(), (data, blockObj, block) -> {
            final var rawBlock = blockObj.getBlock();
            final var requiredToolTierTag = data.requiredToolTier();
        
            tag(Tags.Blocks.ORES).add(rawBlock);
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(rawBlock);
            tag(requiredToolTierTag).add(rawBlock);
        });
    }
    
    private <DATA extends IResource, OBJ extends IBlockRegistryObject> void addTags(
        Map<DATA, OBJ> items,
        Map<ResourceType, TagKey<Block>> tagsByType,
        TriConsumer<DATA, OBJ, ItemLike> consumer
    ) {
        items.forEach((data, blockObj) -> {
            final var block = blockObj.getBlock();
            final var type = data.getResourceType();
            final var tag = tagsByType.get(type);
            
            tag(tag).add(block);
            
            consumer.accept(data, blockObj, block);
        });
    }
    
    private <DATA extends IResource, OBJ extends IBlockRegistryObject> void addTags(
        Multimap<DATA, OBJ> blocks,
        Map<ResourceType, TagKey<Block>> tagsByType,
        TriConsumer<DATA, OBJ, ItemLike> consumer
    ) {
        blocks.forEach((data, blockObj) -> {
            final var block = blockObj.getBlock();
            final var type = data.getResourceType();
            final var tag = tagsByType.get(type);
            
            tag(tag).add(block);
            
            consumer.accept(data, blockObj, block);
        });
    }
}
