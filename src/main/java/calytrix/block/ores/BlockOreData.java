package calytrix.block.ores;

import static calytrix.block.ores.BlockOreType.DEEPSLATE;
import static calytrix.block.ores.BlockOreType.STONE;
import lombok.Getter;
import lombok.experimental.Accessors;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

import calytrix.block.resources.BlockResourceData;
import calytrix.item.resources.ItemResourceMaterialData;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;
import calytrix.util.IResource;
import calytrix.util.ModTags;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public enum BlockOreData implements IBlockData, IResource {
    ADAMANTINE(BlockResourceData.ADAMANTINE, 10, 25, Material.STONE, CalytrixRarity.LEGENDARY, true, Tiers.NETHERITE, List.of(DEEPSLATE)),
    MITHRIL(BlockResourceData.MITHRIL, 6, 9, Material.STONE, Rarity.RARE, false, Tiers.DIAMOND,
            List.of(STONE, DEEPSLATE));
    
    private static final Map<ResourceType, BlockOreData> DATA_BY_TYPE = init();
    
    private final BlockResourceData blockResource;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops = true;
    private final Tier requiredToolTier;
    private final List<BlockOreType> types;
    private final int lightLevel;
    private final ItemResourceMaterialData resourceMaterialData;
    
    BlockOreData(
        BlockResourceData blockResource,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        Tier requiredToolTier,
        List<BlockOreType> types,
        int lightLevel
    ) {
        this.blockResource = blockResource;
        this.strength = strength;
        this.resistance = resistance;
        this.material = material;
        this.rarity = rarity;
        this.fireResistant = fireResistant;
        this.requiredToolTier = requiredToolTier;
        this.types = types;
        this.lightLevel = lightLevel;
        this.resourceMaterialData = ItemResourceMaterialData.fromType(getResourceType());
    }
    
    BlockOreData(
        BlockResourceData blockResource,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        Tier requiredToolTier,
        List<BlockOreType> types
    ) {
        this(blockResource, strength, resistance, material, rarity, fireResistant,
             requiredToolTier, types, 0);
    }
    
    public String getResourceName() {
        return blockResource.getResourceType().getResourceName();
    }
    
    public static BlockOreData fromType(ResourceType resourceType) {
        return DATA_BY_TYPE.get(resourceType);
    }
    
    @Override
    public ResourceType getResourceType() {
        return blockResource.getResourceType();
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
    
    private static Map<ResourceType, BlockOreData> init() {
        final Map<ResourceType, BlockOreData> map = new LinkedHashMap<>();
        for (var block : BlockOreData.values()) {
            map.put(block.blockResource.getResourceType(), block);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
