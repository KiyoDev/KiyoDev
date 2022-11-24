
package calytrix.registry;

import static calytrix.Calytrix.MOD_ID;
import lombok.Getter;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import calytrix.item.ItemRegistryObject;

import java.util.function.Supplier;

@Getter
public class ItemDeferredRegister {
    private final DeferredRegister<Item> items;
    
    public ItemDeferredRegister() {
        items = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    }
    
    public <ITEM extends Item> ItemRegistryObject<ITEM> registerItem(
        String name,
        Supplier<ITEM> itemSupplier
    ) {
        return new ItemRegistryObject<>(items.register(name, itemSupplier));
    }
    
    public void register(IEventBus bus) {
        items.register(bus);
    }
}
