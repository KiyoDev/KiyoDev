package calytrix.material;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;

import calytrix.item.ItemPropertiesBuilder;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.CalytrixConstants;
import calytrix.util.CalytrixRarity;

@Getter
@RequiredArgsConstructor
public enum EquipmentMaterial {
    ADAMANTINE(new Stats(ResourceType.ADAMANTINE,
                         ToolTierType.ADAMANTINE.getTier(),
                         new ArmorStats(4, 4, 2, 525),
                         new ArmorStats(10, 4, 3, 730),
                         new ArmorStats(8, 4, 3, 690),
                         new ArmorStats(4, 4, 2, 625),
                         CalytrixRarity.LEGENDARY,
                         true
    ) {
    }),
    ORICHALCUM(new Stats(ResourceType.ORICHALCUM,
                         Tiers.NETHERITE,
                         new ArmorStats(4, 3, 1, 407),
                         new ArmorStats(8, 3, 2, 529),
                         new ArmorStats(6, 3, 2, 555),
                         new ArmorStats(4, 3, 1, 481),
                         Rarity.EPIC,
                         true
    ) {
    }),
    MITHRIL(new Stats(ResourceType.MITHRIL,
                      ToolTierType.MITHRIL.getTier(),
                      new ArmorStats(3, 2, 0, 363),
                      new ArmorStats(8, 2, 0, 528),
                      new ArmorStats(6, 2, 0, 495),
                      new ArmorStats(3, 2, 0, 429),
                      Rarity.RARE,
                      false
    ) {
        @Override
        public float axeAttackSpeed() {
            return -2.8f;
        }
    
        @Override
        public float swordAttackSpeed() {
            return -1.8f;
        }
    
        @Override
        public float hoeAttackSpeed() {
            return 1;
        }
        
    });
    
    private final Stats stats;
    
    public String resourceName() {
        return stats.resourceName();
    }
    
    @Getter
    @RequiredArgsConstructor
    public abstract static class Stats {
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
            return -3;
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
                                        .durability(tier.getUses())
                                        .tab(CalytrixConstants.CALYTRIX_TAB)
                                        .build();
        }
    }
}
