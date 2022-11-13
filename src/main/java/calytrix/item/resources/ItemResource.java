package calytrix.item.resources;

import calytrix.item.CalytrixItem;
import calytrix.item.resources.ItemResourceIngotData;

public class ItemResource extends CalytrixItem {
    
    public ItemResource(ItemResourceIngotData resourceData) {
        super(properties(resourceData));
    }
}
