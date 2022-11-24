package calytrix.item.tools;

import net.minecraft.world.item.ShovelItem;

import calytrix.material.EquipmentMaterial;

public class BaseShovelItem extends ShovelItem {
    
    public <MAT extends EquipmentMaterial> BaseShovelItem(MAT material) {
        super(material.getStats().getTier(),
              material.getStats().shovelAttackDamage(),
              material.getStats().shovelAttackSpeed(),
              material.getStats().toolProperties());
    }
}
