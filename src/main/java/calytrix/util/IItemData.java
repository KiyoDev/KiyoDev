package calytrix.util;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import calytrix.item.ItemPropertiesBuilder;

public interface IItemData {
    
    int getMaxStackSize();
    
    int getDurability();
    
    Rarity getRarity();
    
    boolean isFireResistant();
    
    boolean canRepair();
    
    Item getCraftRemainingItem();
    
    FoodProperties getFoodProperties();
    
    default Item.Properties itemProperties(CreativeModeTab tab) {
        return ItemPropertiesBuilder.builder()
                                    .tab(tab)
                                    .rarity(getRarity())
                                    .fireResistant(isFireResistant())
                                    .durability(getDurability())
                                    .maxStackSize(getMaxStackSize())
                                    .canRepair(canRepair())
                                    .craftingRemainingItem(getCraftRemainingItem())
                                    .foodProperties(getFoodProperties())
                                    .build();
    }
}
