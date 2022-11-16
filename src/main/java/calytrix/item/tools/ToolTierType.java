package calytrix.item.tools;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import calytrix.item.resources.ResourceType;
import calytrix.util.ModTags;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum ToolTierType {
    
    ADAMANTINE(ResourceType.ADAMANTINE,
               registerTier(ResourceType.ADAMANTINE, 6, 4000, 10f, 6, 22,
                            ModTags.Blocks.NEEDS_ADAMANTINE_TOOL,
                            () -> Ingredient.of(ResourceType.ADAMANTINE.getIngotTag()))),
    MITHRIL(ResourceType.MITHRIL,
            registerTier(ResourceType.MITHRIL, 4, 1561, 35f, 3, 10,
                         ModTags.Blocks.NEEDS_MITHRIL_TOOL,
                         () -> Ingredient.of(ResourceType.MITHRIL.getIngotTag())));
    
    private static final Map<ResourceType, ToolTierType> TOOL_TIER_BY_RESOURCE_TYPE = init();
    
    private final ResourceType resourceType;
    private final Tier tier;
    
    private static Tier registerTier(
        ResourceType resourceType,
        int level,
        int uses,
        float speed,
        float attackDamageBonus,
        int enchantmentValue,
        TagKey<Block> tag,
        Supplier<Ingredient> repairIngredient
    ) {
        final var forgeTier = new ForgeTier(level, uses, speed, attackDamageBonus, enchantmentValue, tag, repairIngredient);
        return TierSortingRegistry.registerTier(forgeTier,
                                                resourceType.getResourceLocation(),
                                                resourceType.getAfter(),
                                                resourceType.getBefore());
    }
    
    private static Map<ResourceType, ToolTierType> init() {
        final Map<ResourceType, ToolTierType> map = new LinkedHashMap<>();
        for (var tier : ToolTierType.values()) {
            map.put(tier.resourceType, tier);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
