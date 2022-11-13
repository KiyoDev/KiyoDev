package calytrix.registry;

import static calytrix.Calytrix.MOD_ID;

import lombok.Getter;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import calytrix.block.BlockRegistryObject;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
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
        return register(name, block, () -> blockItemCreator.apply(block), BlockRegistryObject::new);
    }
    
    public <BLOCK extends Block, ITEM extends Item> BlockRegistryObject<BLOCK, ITEM> register(
        String name,
        RegistryObject<BLOCK> block,
        Supplier<ITEM> blockItemSupplier,
        BiFunction<RegistryObject<BLOCK>, RegistryObject<ITEM>, BlockRegistryObject<BLOCK, ITEM>> registryObjectCreator
    ) {
        var blockItem = items.register(name, blockItemSupplier);
        return registryObjectCreator.apply(block, blockItem);
    }
    
    public void register(IEventBus bus) {
        blocks.register(bus);
        items.register(bus);
    }
}
