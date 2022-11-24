package calytrix.item.tools;

import net.minecraft.world.item.PickaxeItem;

import calytrix.material.EquipmentMaterial;

public class BasePickaxeItem extends PickaxeItem {
    
    public <MAT extends EquipmentMaterial> BasePickaxeItem(MAT material) {
        super(material.getStats().getTier(),
              material.getStats().pickaxeAttackDamage(),
              material.getStats().pickaxeAttackSpeed(),
              material.getStats().toolProperties());
    }
}
