package calytrix.item.tools;

import net.minecraft.world.item.SwordItem;

import calytrix.material.BaseMaterial;

public class BaseSwordItem<MAT extends BaseMaterial> extends SwordItem {
    
    public BaseSwordItem(MAT material) {
        super(material.getTier(), material.swordAttackDamage(), material.swordAttackSpeed(), material.toolProperties());
    }
}
