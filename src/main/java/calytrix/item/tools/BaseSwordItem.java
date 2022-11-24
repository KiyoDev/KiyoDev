package calytrix.item.tools;

import net.minecraft.world.item.SwordItem;

import calytrix.material.EquipmentMaterial;

public class BaseSwordItem extends SwordItem {
    
    public <MAT extends EquipmentMaterial> BaseSwordItem(MAT material) {
        super(material.getStats().getTier(),
              material.getStats().swordAttackDamage(),
              material.getStats().swordAttackSpeed(),
              material.getStats().toolProperties());
    }
}
