package calytrix.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public interface IBlockData {
    Item.Properties blockItemProperties(CreativeModeTab tab);
}
