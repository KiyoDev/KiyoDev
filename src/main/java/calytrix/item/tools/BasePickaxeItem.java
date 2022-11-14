package calytrix.item.tools;

import net.minecraft.world.item.PickaxeItem;

import calytrix.material.BaseMaterial;

public class BasePickaxeItem<MAT extends BaseMaterial> extends PickaxeItem {
    
    public BasePickaxeItem(MAT material) {
        super(material.getTier(), material.pickaxeAttackDamage(), material.pickaxeAttackSpeed(), material.toolProperties());
    }
}
