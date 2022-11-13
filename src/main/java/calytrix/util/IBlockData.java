package calytrix.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

public interface IBlockData {
    float getStrength();
    
    float getResistance();
    
    Material getMaterial();
    
    Rarity getRarity();
    
    boolean isFireResistant();
    
    boolean requiresCorrectToolForDrops();
    
    int getLightLevel();
    
    default Item.Properties blockItemProperties(CreativeModeTab tab) {
        return ItemPropertiesBuilder.builder()
                                    .tab(tab)
                                    .rarity(getRarity())
                                    .fireResistant(isFireResistant())
                                    .canRepair(false)
                                    .build();
    }
}
