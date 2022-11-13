package calytrix.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import calytrix.block.ores.BlockOre;
import calytrix.block.ores.BlockOreData;
import calytrix.block.resources.BlockResource;
import calytrix.block.resources.BlockResourceData;
import calytrix.registry.BlockDeferredRegister;
import calytrix.util.CalytrixCreativeTab;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalytrixBlocks {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister();
    
    private static final Map<BlockResourceData, BlockRegistryObject<BlockResource, Item>> RESOURCE_BLOCKS = new LinkedHashMap<>();
    private static final Map<BlockOreData, BlockRegistryObject<BlockOre, Item>> ORE_BLOCKS = new LinkedHashMap<>();
    
    static {
        for (var resourceData : BlockResourceData.values()) {
            RESOURCE_BLOCKS.put(resourceData, registerResourceBlock(resourceData));
        }
        
        for (var oreData : BlockOreData.values()) {
            for (var type : oreData.getTypes()) {
                final String name = switch (type) {
                    case STONE -> "%s_ore".formatted(oreData.resourceName());
                    default -> "%s_%s_ore".formatted(type.getPrefix(), oreData.resourceName());
                };
                ORE_BLOCKS.put(oreData, registerOreBlock(name, oreData));
            }
        }
    }
    
    private static BlockRegistryObject<BlockResource, Item> registerResourceBlock(BlockResourceData resource) {
        final CreativeModeTab tab = CalytrixCreativeTab.TAB_CALYTRIX;
        
        return BLOCKS.registerBlock("block_of_%s".formatted(resource.resourceName()),
                                    () -> new BlockResource(resource),
                                    (block) -> new BlockItem(block.get(), resource.blockItemProperties(tab)));
    }
    
    private static BlockRegistryObject<BlockOre, Item> registerOreBlock(String name, BlockOreData ore) {
        final CreativeModeTab tab = CalytrixCreativeTab.TAB_CALYTRIX;
        
        return BLOCKS.registerBlock(name,
                                    () -> new BlockOre(ore),
                                    (block) -> new BlockItem(block.get(), ore.blockItemProperties(tab)));
    }
    
    public static Map<BlockResourceData, BlockRegistryObject<BlockResource, Item>> getResourceStorageBlocks() {
        return Collections.unmodifiableMap(RESOURCE_BLOCKS);
    }
    
    public static Map<BlockOreData, BlockRegistryObject<BlockOre, Item>> getOreBlocks() {
        return Collections.unmodifiableMap(ORE_BLOCKS);
    }
    
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
