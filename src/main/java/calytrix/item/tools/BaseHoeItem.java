package calytrix.item.tools;

import net.minecraft.world.item.HoeItem;

import calytrix.material.BaseMaterial;

public class BaseHoeItem<MAT extends BaseMaterial> extends HoeItem {
    
    public BaseHoeItem(MAT material) {
        super(material.getTier(), material.hoeAttackDamage(), material.hoeAttackSpeed(), material.toolProperties());
    }
}
