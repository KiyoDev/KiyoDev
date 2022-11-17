package calytrix.util;

import calytrix.Calytrix;
import calytrix.block.ores.BlockOreData;
import calytrix.block.resources.BlockResourceData;
import calytrix.item.CalytrixItems;
import calytrix.item.ItemRegistryObject;
import calytrix.item.resources.ItemRawMaterial;
import calytrix.item.resources.ItemResourceMaterialData;
import calytrix.item.resources.ResourceType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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
        
        private static final Map<BlockResourceData, TagKey<Block>> RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE = new LinkedHashMap<>();
        
        static {
            for (var block : BlockResourceData.values()) {
                RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE.put(block, forgeTag("storage_blocks/%s".formatted(block.resourceName())));
            }
        }
        
        public static Map<BlockResourceData, TagKey<Block>> resourceForgeTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE);
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
        private static final Multimap<ResourceType, TagKey<Item>> ORE_BLOCK_TAGS_BY_TYPE = ArrayListMultimap.create();
        
        static {
            for (var ingot : ItemResourceMaterialData.values()) {
                RESOURCE_INGOTS_TAGS_BY_TYPE.put(ingot.getResourceType(),
                                                 forgeTag("ingots/%s_ingot".formatted(ingot.resourceName())));
            }
            
            for (var block : BlockResourceData.values()) {
                RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE.put(block.getResourceType(),
                                                        forgeTag("storage_blocks/%s_block".formatted(block.resourceName())));
            }
            
            // for (var block : BlockOreData.values()) {
            //     // final ItemRegistryObject<ItemRawMaterial> itemRawMaterialItemRegistryObject =
            //     //     CalytrixItems.getRawMaterials().get(block);
            //     RAW_STORAGE_BLOCK_TAGS_BY_TYPE.put(block.getResourceType(),
            //                                             forgeTag("storage_blocks/raw_%s".formatted(block.resourceName())));
            // }
            
            addOreTags();
        }
        
        public static Map<ResourceType, TagKey<Item>> resourceIngotsTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_INGOTS_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Item>> resourceStorageBlocksTagsByType() {
            return Collections.unmodifiableMap(RESOURCE_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        public static Multimap<ResourceType, TagKey<Item>> oreBlocksTagsByType() {
            return ORE_BLOCK_TAGS_BY_TYPE;
        }
        
        public static Map<ResourceType, TagKey<Item>> rawMaterialTagsByType() {
            return Collections.unmodifiableMap(RAW_MATERIAL_TAGS_BY_TYPE);
        }
        
        public static Map<ResourceType, TagKey<Item>> rawStorageBlocksTagsByType() {
            return Collections.unmodifiableMap(RAW_STORAGE_BLOCK_TAGS_BY_TYPE);
        }
        
        private static void addOreTags() {
            // ores
            oreAndRawTags();
            // ores/...
            // ores_in_ground/...
            // ore_rates/...
        }
        
        private static void oreAndRawTags() {
            for (var ore : BlockOreData.values()) {
                for (var type : ore.getTypes()) {
                    ORE_BLOCK_TAGS_BY_TYPE.put(ore.getResourceType(), forgeTag("ores/%s".formatted(ore.resourceName())));
                }
                RAW_MATERIAL_TAGS_BY_TYPE.put(ore.getResourceType(),
                                              forgeTag("raw_materials/%s".formatted(ore.resourceName())));
                RAW_STORAGE_BLOCK_TAGS_BY_TYPE.put(ore.getResourceType(),
                                                   forgeTag("storage_blocks/raw_%s".formatted(ore.resourceName())));
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
