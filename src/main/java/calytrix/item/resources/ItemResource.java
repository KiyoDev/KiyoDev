package calytrix.item.resources;

import calytrix.item.CalytrixItem;

public class ItemResource extends CalytrixItem {
    
    public ItemResource(ItemResourceMaterialData resourceData) {
        super(properties(resourceData));
    }
}
