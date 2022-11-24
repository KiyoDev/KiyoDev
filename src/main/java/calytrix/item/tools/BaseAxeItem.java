package calytrix.item.tools;

import net.minecraft.world.item.AxeItem;

import calytrix.material.EquipmentMaterial;

public class BaseAxeItem extends AxeItem {
    
    public <MAT extends EquipmentMaterial> BaseAxeItem(MAT material) {
        super(material.getStats().getTier(),
              material.getStats().axeAttackDamage(),
              material.getStats().axeAttackSpeed(),
              material.getStats().toolProperties());
    }
}
