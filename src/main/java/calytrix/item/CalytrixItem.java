package calytrix.item;

import net.minecraft.world.item.Item;

import calytrix.util.CalytrixCreativeTab;
import calytrix.util.IItemData;

public abstract class CalytrixItem extends Item {
    public CalytrixItem(Properties properties) {
        super(properties);
    }
    
    protected static <TYPE extends IItemData> Item.Properties properties(TYPE data) {
        return data.itemProperties(CalytrixCreativeTab.TAB_CALYTRIX);
    }
}
