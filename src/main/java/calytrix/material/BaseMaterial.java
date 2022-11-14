package calytrix.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

import calytrix.item.ItemPropertiesBuilder;
import calytrix.item.resources.ResourceType;
import calytrix.util.CalytrixConstants;

@Getter
@RequiredArgsConstructor
public abstract class BaseMaterial {
    protected final ResourceType resourceType;
    protected final Tier tier;
    protected final ArmorStats helmetStats;
    protected final ArmorStats chestplateStats;
    protected final ArmorStats leggingsStats;
    protected final ArmorStats bootsStats;
    protected final Rarity rarity;
    protected final boolean fireResistant;
    
    public String resourceName() {
        return resourceType.getResourceName();
    }
    
    public float axeAttackDamage() {
        return tier.getAttackDamageBonus() + 5;
    }
    
    public float axeAttackSpeed() {
        return -3f;
    }
    
    public int pickaxeAttackDamage() {
        return 0;
    }
    
    public float pickaxeAttackSpeed() {
        return -2.8f;
    }
    
    public int swordAttackDamage() {
        return 3;
    }
    
    public float swordAttackSpeed() {
        return -2.4f;
    }
    
    public float shovelAttackDamage() {
        return 0.5f;
    }
    
    public float shovelAttackSpeed() {
        return -2.8f;
    }
    
    public int hoeAttackDamage() {
        return (int) -(tier.getAttackDamageBonus() - 1);
    }
    
    public float hoeAttackSpeed() {
        return 0;
    }
    
    public Item.Properties toolProperties() {
        return ItemPropertiesBuilder.builder()
                                    .rarity(rarity)
                                    .fireResistant(fireResistant)
                                    .maxStackSize(1)
                                    .durability(tier.getUses())
                                    .tab(CalytrixConstants.CALYTRIX_TAB)
                                    .build();
    }
}
