package calytrix.item.tools;

import net.minecraft.world.item.AxeItem;

import calytrix.material.BaseMaterial;

public class BaseAxeItem<MAT extends BaseMaterial> extends AxeItem {
    
    public BaseAxeItem(MAT material) {
        super(material.getTier(), material.axeAttackDamage(), material.axeAttackSpeed(), material.toolProperties());
    }
}
