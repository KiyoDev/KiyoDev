package calytrix.item.tools;

import net.minecraft.world.item.TridentItem;

import calytrix.material.EquipmentMaterial;

public class BaseTridentItem extends TridentItem {
    public BaseTridentItem(EquipmentMaterial material) {
        super(material.getStats().toolProperties());
    }
}
