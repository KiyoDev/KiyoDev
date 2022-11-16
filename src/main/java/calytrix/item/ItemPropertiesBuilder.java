package calytrix.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.Rarity;

import calytrix.util.CalytrixConstants;

import javax.annotation.Nullable;

public class ItemPropertiesBuilder {
    private int maxStackSize = 64;
    private int maxDamage;
    @Nullable
    private Item craftingRemainingItem;
    private CreativeModeTab tab;
    private Rarity rarity;
    @Nullable
    FoodProperties foodProperties;
    private boolean fireResistant;
    private boolean canRepair = true;
    
    private ItemPropertiesBuilder() {
    }
    
    public static ItemPropertiesBuilder builder() {
        return new ItemPropertiesBuilder();
    }
    
    public static Properties defaultStackableProperties() {
        return new Properties().tab(CalytrixConstants.CALYTRIX_TAB);
    }
    
    public static Properties defaultUnstackableProperties() {
        return new Properties().tab(CalytrixConstants.CALYTRIX_TAB)
                               .stacksTo(1);
    }
    
    public Properties build() {
        Properties props = new Properties().tab(tab)
                                           .rarity(rarity);
        
        if (fireResistant) {
            props = props.fireResistant();
        }
        
        if (!canRepair) {
            props = props.setNoRepair();
        }
        
        if (maxDamage > 0) {
            props = props.durability(maxDamage);
        }
        
        if (maxStackSize != 64 && maxDamage == 0) {
            props = props.stacksTo(maxStackSize);
        }
        
        if (foodProperties != null) {
            props = props.food(foodProperties);
        }
        
        if (craftingRemainingItem != null) {
            props = props.craftRemainder(craftingRemainingItem);
        }
        
        return props;
    }
    
    public ItemPropertiesBuilder maxStackSize(int maxStackSize) {
        if (this.maxDamage > 0) {
            throw new RuntimeException("Unable to have damage AND stack.");
        } else {
            this.maxStackSize = maxStackSize;
            return this;
        }
    }
    
    public ItemPropertiesBuilder durability(int maxDamage) {
        this.maxDamage = maxDamage;
        this.maxStackSize = 1;
        return this;
    }
    
    public ItemPropertiesBuilder craftingRemainingItem(Item craftingRemainingItem) {
        this.craftingRemainingItem = craftingRemainingItem;
        return this;
    }
    
    public ItemPropertiesBuilder foodProperties(FoodProperties foodProperties) {
        this.foodProperties = foodProperties;
        return this;
    }
    
    public ItemPropertiesBuilder tab(CreativeModeTab tab) {
        this.tab = tab;
        return this;
    }
    
    public ItemPropertiesBuilder rarity(Rarity rarity) {
        this.rarity = rarity;
        return this;
    }
    
    public ItemPropertiesBuilder fireResistant(boolean fireResistant) {
        this.fireResistant = fireResistant;
        return this;
    }
    
    public ItemPropertiesBuilder canRepair(boolean canRepair) {
        this.canRepair = canRepair;
        return this;
    }
}
