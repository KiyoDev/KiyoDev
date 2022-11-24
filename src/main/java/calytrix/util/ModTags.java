package calytrix.util;

import calytrix.Calytrix;
import calytrix.block.ores.BlockOreData;
import calytrix.item.resources.ResourceType;

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
        
        public static final TagKey<Block> ORES_IN_GROUND_END = forgeTag("ores_in_ground/end_stone");
        
        private static final Map<ResourceType, TagKey<Block>> RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Block>> ORE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Block>> RAW_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        // private static final Map<BlockResourceData, TagKey<Block>> RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        
        static {
            for (var resourceType : ResourceType.values()) {
                RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE.put(resourceType,
                                                        forgeTag("storage_blocks/%s".formatted(resourceType.getResourceName())));
            }
            
            for (var ore : BlockOreData.values()) {
                final var resourceType = ore.getResourceType();
                final var resourceName = ore.getResourceName();
                ORE_BLOCK_TAGS_BY_TYPE.put(resourceType, forgeTag("ores/%s".formatted(resourceName)));
                RAW_STORAGE_BLOCK_TAGS_BY_TYPE.put(resourceType, forgeTag("storage_blocks/raw_%s".formatted(resourceName)));
            }
            // for (var block : BlockResourceData.values()) {
            //     RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE.put(block, forgeTag("storage_blocks/%s".formatted(block.resourceName())));
            // }
        }
        
        public static Map<ResourceType, TagKey<Block>> resourceForgeTags() {
            return Collections.unmodifiableMap(RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Block>> oresForgeTags() {
            return Collections.unmodifiableMap(ORE_BLOCK_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Block>> rawStorageBlockForgeTags() {
            return Collections.unmodifiableMap(RAW_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(Calytrix.resourceLocation(name));
        }
        
        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(forgeResourceLocation(name));
        }
    }
    
    public static class Items {
        
        public static final TagKey<Item> ORES_IN_GROUND_END = forgeTag("ores_in_ground/end_stone");
        
        private static final Map<ResourceType, TagKey<Item>> RESOURCE_INGOTS_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Item>> RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Item>> RAW_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Item>> RAW_MATERIAL_TAGS_BY_TYPE = new LinkedHashMap<>();
        private static final Map<ResourceType, TagKey<Item>> ORE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        
        static {
            for (var resource : ResourceType.values()) {
                if (resource == ResourceType.ORICHALCUM) {
                    continue;
                }
                RESOURCE_INGOTS_TAGS_BY_TYPE.put(resource,
                                                 forgeTag("ingots/%s".formatted(resource.getResourceName())));
                RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE.put(resource,
                                                        forgeTag("storage_blocks/%s".formatted(resource.getResourceName())));
            }
            
            addOreTags();
        }
        
        public static Map<ResourceType, TagKey<Item>> resourceIngotsTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_INGOTS_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Item>> resourceStorageBlocksTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Item>> oreBlocksTagsByType() {
            return ORE_BLOCK_TAGS_BY_TYPE;
        }
        
        public static Map<ResourceType, TagKey<Item>> rawMaterialTagsByType() {
            return Collections.unmodifiableMap(RAW_MATERIAL_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Item>> rawStorageBlocksTagsByType() {
            return Collections.unmodifiableMap(RAW_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        private static void addOreTags() {
            // ores/...
            oreAndRawTags();
        }
        
        private static void oreAndRawTags() {
            for (var ore : BlockOreData.values()) {
                ORE_BLOCK_TAGS_BY_TYPE.put(ore.getResourceType(), forgeTag("ores/%s".formatted(ore.getResourceName())));
                RAW_MATERIAL_TAGS_BY_TYPE.put(ore.getResourceType(),
                                              forgeTag("raw_materials/%s".formatted(ore.getResourceName())));
                RAW_STORAGE_BLOCK_TAGS_BY_TYPE.put(ore.getResourceType(),
                                                   forgeTag("storage_blocks/raw_%s".formatted(ore.getResourceName())));
            }
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
}
