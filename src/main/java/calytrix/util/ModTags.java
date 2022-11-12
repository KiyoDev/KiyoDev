package calytrix.util;

import calytrix.Calytrix;
import calytrix.block.BlockResourceData;

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
        
        public static final TagKey<Block> BLOCK_OF_ADAMANTINE = forgeTag("storage_block/adamantine");
        public static final TagKey<Block> BLOCK_OF_MITHRIL = forgeTag("storage_block/mithril");
        
        private static final Map<BlockResourceData, TagKey<Block>> TAGS_BY_RESOURCE = new LinkedHashMap<>();
        
        static {
            for(var block : BlockResourceData.values()) {
                TAGS_BY_RESOURCE.put(block, forgeTag("storage_block/%s".formatted(block.getSuffix())));
            }
        }
        
        public static Map<BlockResourceData, TagKey<Block>> tagsByResource() {
            return Collections.unmodifiableMap(TAGS_BY_RESOURCE);
        }
        
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Calytrix.resourceLocation(name));
        }
        
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(forgeResourceLocation(name));
        }
    }
    
    public static class Items {
        public static final TagKey<Item> ADAMANTINE_INGOT = forgeTag("ingots/adamantine");
        public static final TagKey<Item> MITHRIL_INGOT = forgeTag("ingots/mithril");
        
        private static TagKey<Item> genericTag(String name) {
            return ItemTags.create(new ResourceLocation(name));
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
