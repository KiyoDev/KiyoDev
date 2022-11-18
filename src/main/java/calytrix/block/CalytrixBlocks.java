package calytrix.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import calytrix.block.ores.BlockOre;
import calytrix.block.ores.BlockOreData;
import calytrix.block.ores.BlockOreType;
import calytrix.block.ores.BlockRaw;
import calytrix.block.resources.BlockResource;
import calytrix.block.resources.BlockResourceData;
import calytrix.registry.BlockDeferredRegister;
import calytrix.util.CalytrixConstants;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalytrixBlocks {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister();
    
    private static final Map<BlockResourceData, BlockRegistryObject<BlockResource, Item>> RESOURCE_STORAGE_BLOCKS = new LinkedHashMap<>();
    private static final Multimap<BlockOreData, BlockRegistryObject<BlockOre, Item>> ORE_BLOCKS = ArrayListMultimap.create();
    private static final Map<BlockOreData, BlockRegistryObject<BlockRaw, Item>> RAW_BLOCKS = new LinkedHashMap<>();
    
    static {
        final var tab = CalytrixConstants.CALYTRIX_TAB;
        
        for (var resourceData : BlockResourceData.values()) {
            RESOURCE_STORAGE_BLOCKS.put(resourceData, registerResourceBlock(resourceData, tab));
        }
        
        for (var oreData : BlockOreData.values()) {
            LOGGER.info("ore - {}", oreData);
            for (var type : oreData.getTypes()) {
                final String name;
                if (type == BlockOreType.STONE) {
                    name = "%s_ore".formatted(oreData.getResourceName());
                } else {
                    name = "%s_%s_ore".formatted(type.getPrefix(), oreData.getResourceName());
                }
                LOGGER.info("type - {}, {}", type, name);
                ORE_BLOCKS.put(oreData, registerOreBlock(name, oreData, type, tab));
            }
            RAW_BLOCKS.put(oreData, registerRawBlock("raw_%s_block".formatted(oreData.getResourceName()), oreData, tab));
        }
        
        ORE_BLOCKS.forEach((d, o) -> LOGGER.info("ore blocks - {}, [{}]", d, o.toString()));
    }
    
    private static BlockRegistryObject<BlockResource, Item> registerResourceBlock(
        BlockResourceData resource,
        CreativeModeTab tab
    ) {
        return BLOCKS.registerBlock(resource.blockOfName(),
                                    () -> new BlockResource(resource),
                                    (block) -> new BlockItem(block.get(), resource.blockItemProperties(tab)));
    }
    
    private static BlockRegistryObject<BlockOre, Item> registerOreBlock(
        String name,
        BlockOreData ore,
        BlockOreType oreType,
        CreativeModeTab tab
    ) {
        return BLOCKS.registerBlock(name,
                                    () -> new BlockOre(ore, oreType),
                                    (block) -> new BlockItem(block.get(), ore.blockItemProperties(tab)));
    }
    
    private static BlockRegistryObject<BlockRaw, Item> registerRawBlock(
        String name,
        BlockOreData ore,
        CreativeModeTab tab
    ) {
        return BLOCKS.registerBlock(name,
                                    () -> new BlockRaw(ore),
                                    (block) -> new BlockItem(block.get(), ore.blockItemProperties(tab)));
    }
    
    public static Map<BlockResourceData, BlockRegistryObject<BlockResource, Item>> getResourceStorageBlocks() {
        return Collections.unmodifiableMap(RESOURCE_STORAGE_BLOCKS);
    }
    
    public static Multimap<BlockOreData, BlockRegistryObject<BlockOre, Item>> getOreBlocks() {
        return ORE_BLOCKS;
    }
    
    public static Map<BlockOreData, BlockRegistryObject<BlockRaw, Item>> getRawBlocks() {
        return Collections.unmodifiableMap(RAW_BLOCKS);
    }
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
    
    private CalytrixBlocks() {
        // Utility
    }
}
