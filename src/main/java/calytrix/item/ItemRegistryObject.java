package calytrix.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import calytrix.util.IItemRegistryObject;

@Getter
@RequiredArgsConstructor
public class ItemRegistryObject<ITEM extends Item> implements IItemRegistryObject {
    private final RegistryObject<ITEM> itemObj;
    
    public String registry() {
        return itemObj.getId().getPath();
    }
    
    @Override
    public ITEM getItem() {
        return itemObj.get();
    }
}
