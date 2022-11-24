package calytrix.block.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;
import calytrix.util.IResource;
import calytrix.util.ModTags;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum BlockResourceData implements IBlockData, IResource {
    ADAMANTINE(ResourceType.ADAMANTINE, 8, 100, Material.HEAVY_METAL, CalytrixRarity.LEGENDARY, true, true, Tiers.NETHERITE),
    MITHRIL(ResourceType.MITHRIL, 6, 9, Material.HEAVY_METAL, Rarity.RARE, false, true, Tiers.DIAMOND);
    
    private static final Map<ResourceType, BlockResourceData> STORAGE_BLOCK_BY_RESOURCE_TYPE = init();
    
    private final ResourceType resourceType;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops;
    private final Tier requiredToolTier;
    private final int lightLevel;
    
    BlockResourceData(
        ResourceType resourceType,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops,
        Tier requiredToolTier
    ) {
        this(resourceType, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, requiredToolTier,
             0);
    }
    
    public String blockOfName() {
        return "block_of_%s".formatted(getResourceName());
    }
    
    public String getResourceName() {
        return resourceType.getResourceName();
    }
    
    public static BlockResourceData fromType(ResourceType type) {
        return STORAGE_BLOCK_BY_RESOURCE_TYPE.get(type);
    }
    
    public TagKey<Block> requiredToolTier() {
        if (requiredToolTier == ToolTierType.ADAMANTINE.getTier()) {
            return ModTags.Blocks.NEEDS_ADAMANTINE_TOOL;
        } else if (requiredToolTier == ToolTierType.MITHRIL.getTier()) {
            return ModTags.Blocks.NEEDS_MITHRIL_TOOL;
        } else if (requiredToolTier == Tiers.NETHERITE) {
            return Tags.Blocks.NEEDS_NETHERITE_TOOL;
        } else if (requiredToolTier == Tiers.DIAMOND) {
            return BlockTags.NEEDS_DIAMOND_TOOL;
        } else {
            return BlockTags.NEEDS_IRON_TOOL;
        }
    }
    
    private static Map<ResourceType, BlockResourceData> init() {
        final Map<ResourceType, BlockResourceData> map = new LinkedHashMap<>();
        for (var block : BlockResourceData.values()) {
            map.put(block.resourceType, block);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
