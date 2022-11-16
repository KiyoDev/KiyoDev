package calytrix.item.resources;

import calytrix.block.ores.BlockOreData;
import calytrix.item.CalytrixItem;

public class ItemRawMaterial extends CalytrixItem {
    public ItemRawMaterial(BlockOreData ore) {
        super(properties(ore.getResourceMaterialData()));
    }
}
