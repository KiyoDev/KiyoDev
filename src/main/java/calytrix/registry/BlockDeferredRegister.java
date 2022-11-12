package calytrix.registry;

import static calytrix.Calytrix.MOD_ID;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import calytrix.block.BlockRegistryObject;
import calytrix.util.CalytrixCreativeTab;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;


public class BlockDeferredRegister {
    private final DeferredRegister<Block> blocks;
    private final DeferredRegister<Item> items;
    
    public BlockDeferredRegister() {
        blocks = DeferredRegister.create(ForgeRegistries.BLOCKS, MOD_ID);
        items = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    }

    public <BLOCK extends Block, ITEM extends Item> BlockRegistryObject<BLOCK, ITEM> registerBlock(
        String name,
        Supplier<BLOCK> blockSupplier,
        Function<RegistryObject<BLOCK>, ITEM> blockItemCreator
    ) {
        var block = blocks.register(name, blockSupplier);
        final CreativeModeTab tab = CalytrixCreativeTab.TAB_CALYTRIX;
        // final RegistryObject<BLOCK> obj = blocks.register(name, blockSupplier);
        // final CreativeModeTab tab = CalytrixCreativeTab.TAB_CALYTRIX;
        
        // createItem.apply();
        // final RegistryObject<ITEM> itemRegistryObject = registerBlockItem(name, obj, tab);
        
        // return new BlockRegistryObject<>();
        // return register(name, block, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)), BlockRegistryObject::new);
        return register(name, block, () -> blockItemCreator.apply(block), BlockRegistryObject::new);
    }
    
    public <BLOCK extends Block, ITEM extends Item> BlockRegistryObject<BLOCK, ITEM> register(
        String name,
        RegistryObject<BLOCK> block,
        Supplier<ITEM> blockItemSupplier,
        BiFunction<RegistryObject<BLOCK>, RegistryObject<ITEM>, BlockRegistryObject<BLOCK, ITEM>> registryObjectCreator
    ) {
        var blockItem = items.register(name, registerBlockItem(name, blockItemSupplier));
        return registryObjectCreator.apply(block, blockItem);
    }
    
    private <ITEM extends Item> RegistryObject<ITEM> registerBlockItem(
        String name,
        Supplier<ITEM> blockItemSupplier
    ) {
        // return items.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
        return items.register(name, blockItemSupplier);
    }
    
}
