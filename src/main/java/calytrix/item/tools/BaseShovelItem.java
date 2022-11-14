package calytrix.item.tools;

import net.minecraft.world.item.ShovelItem;

import calytrix.material.BaseMaterial;

public class BaseShovelItem<MAT extends BaseMaterial> extends ShovelItem {
    
    public BaseShovelItem(MAT material) {
        super(material.getTier(), material.shovelAttackDamage(), material.shovelAttackSpeed(), material.toolProperties());
    }
}
