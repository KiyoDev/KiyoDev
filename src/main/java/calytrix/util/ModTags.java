package calytrix.util;

import calytrix.Calytrix;
import calytrix.block.resources.BlockResourceData;
import calytrix.item.resources.ItemResourceIngotData;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ModTags {
    
    public static class Blocks {
        
        public static final TagKey<Block> NEEDS_ADAMANTINE_TOOL = tag("needs_adamantine_tool");
        public static final TagKey<Block> NEEDS_MITHRIL_TOOL = tag("needs_mithril_tool");
        
        // public static final TagKey<Block> BLOCK_OF_ADAMANTINE = forgeTag("storage_block/adamantine");
        // public static final TagKey<Block> BLOCK_OF_MITHRIL = forgeTag("storage_block/mithril");
        
        // private static final Map<BlockResourceData, TagKey<Block>> RESOURCE_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<BlockResourceData, TagKey<Block>> RESOURCE_FORGE_TAGS_BY_TYPE = new LinkedHashMap<>();
        
        static {
            // final var resourceBlocks = CalytrixBlocks.getResourceBlocks();
            // for(var block : resourceBlocks.keySet()) {
            for(var block : BlockResourceData.values()) {
                RESOURCE_FORGE_TAGS_BY_TYPE.put(block, forgeTag("storage_blocks/%s".formatted(block.getResource().getResourceName())));
            }
        }
        
        public static Map<BlockResourceData, TagKey<Block>> resourceForgeTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_FORGE_TAGS_BY_TYPE);
        }
        
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Calytrix.resourceLocation(name));
        }
        
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(forgeResourceLocation(name));
        }
    }
    
    public static class Items {
    
        private static final Map<ItemResourceIngotData, TagKey<Item>> RESOURCE_FORGE_TAGS_BY_TYPE = new LinkedHashMap<>();
        
        static {
            for(var item : ItemResourceIngotData.values()) {
                RESOURCE_FORGE_TAGS_BY_TYPE.put(item, forgeTag("ingots/%s".formatted(item.resourceName())));
            }
        }
    
        public static Map<ItemResourceIngotData, TagKey<Item>> resourceForgeTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_FORGE_TAGS_BY_TYPE);
        }
        
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(Calytrix.resourceLocation(name));
        }
    
        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(forgeResourceLocation(name));
        }
    }

    private static ResourceLocation forgeResourceLocation(String name) {
        return new ResourceLocation("forge", name);
    }
    
    // public static class
}
