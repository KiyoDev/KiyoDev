package calytrix.util;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public interface IBlockRegistryObject {
    Block getBlock();
    Item getItem();
}
