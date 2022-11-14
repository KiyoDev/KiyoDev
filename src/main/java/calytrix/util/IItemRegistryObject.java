package calytrix.util;

import net.minecraft.world.item.Item;

public interface IItemRegistryObject<ITEM extends Item> {
    ITEM getItem();
}
