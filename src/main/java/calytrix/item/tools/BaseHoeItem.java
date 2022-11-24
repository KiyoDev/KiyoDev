package calytrix.item.tools;

import net.minecraft.world.item.HoeItem;

import calytrix.material.EquipmentMaterial;

public class BaseHoeItem extends HoeItem {
    
    public <MAT extends EquipmentMaterial> BaseHoeItem(MAT material) {
        super(material.getStats().getTier(),
              material.getStats().hoeAttackDamage(),
              material.getStats().hoeAttackSpeed(),
              material.getStats().toolProperties());
    }
}
