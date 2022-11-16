package calytrix.item.armor;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

public class BaseArmorItem extends ArmorItem {
    public BaseArmorItem(
        ArmorMaterial armorMaterial,
        EquipmentSlot equipmentSlot,
        Properties properties
    ) {
        super(armorMaterial, equipmentSlot, properties);
    }
}
