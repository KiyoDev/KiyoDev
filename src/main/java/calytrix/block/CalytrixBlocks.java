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
    
    public static Map<BlockResourceData, BlockRegistryObject<BlockResource, Item>> getResourceBlocks() {
        return Collections.unmodifiableMap(RESOURCE_BLOCKS);
    }
    
    // public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
    //
    // // public static final RegistryObject<Block> BLOCK_OF_ADAMANTINE =
    // //     registerFireResistantBlock("block_of_adamantine",
    // //                                () -> new Block(Properties.of(Material.HEAVY_METAL)
    // //                                                          .strength(6f)
    // //                                                          .requiresCorrectToolForDrops()),
    // //                                CalytrixCreativeTab.TAB_CALYTRIX);
    //
    // public static final RegistryObject<Block> BLOCK_OF_RAW_ADAMANTINE =
    //     registerFireResistantBlock("block_of_raw_adamantine",
    //                                () -> new Block(Properties.of(Material.HEAVY_METAL)
    //                                                          .strength(6f)
    //                                                          .requiresCorrectToolForDrops()),
    //                                CalytrixCreativeTab.TAB_CALYTRIX);
    //
    // public static final RegistryObject<Block> DEEPSLATE_ADAMANTINE_ORE =
    //     registerFireResistantBlock("deepslate_adamantine_ore",
    //                                () -> new Block(Properties.of(Material.STONE)
    //                                                          .strength(10f)
    //                                                          .requiresCorrectToolForDrops()),
    //                                CalytrixCreativeTab.TAB_CALYTRIX);
    
    // public static final RegistryObject<Block> BLOCK_OF_MITHRIL =
    //     registerFireResistantBlock("block_of_mithril",
    //                                () -> new Block(Properties.of(Material.HEAVY_METAL)
    //                                                          .strength(6f)
    //                                                          .requiresCorrectToolForDrops()),
    //                                CalytrixCreativeTab.TAB_CALYTRIX);
    
    // private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
    //     final RegistryObject<T> obj = BLOCKS.register(name, block);
    //     registerBlockItem(name, obj, tab);
    //     return obj;
    // }
    //
    // private static <T extends Block> RegistryObject<Item> registerBlockItem(
    //     String name,
    //     RegistryObject<T> block,
    //     CreativeModeTab tab
    // ) {
    //     return CalytrixItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    // }
    //
    // private static <T extends Block> RegistryObject<T> registerFireResistantBlock(
    //     String name,
    //     Supplier<T> block,
    //     CreativeModeTab tab
    // ) {
    //     final RegistryObject<T> obj = BLOCKS.register(name, block);
    //     registerFireResistantBlockItem(name, obj, tab);
    //     return obj;
    // }
    //
    // private static <T extends Block> RegistryObject<Item> registerFireResistantBlockItem(
    //     String name,
    //     RegistryObject<T> block,
    //     CreativeModeTab tab
    // ) {
    //     return CalytrixItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant().tab
    //     (tab)));
    // }
    //
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
