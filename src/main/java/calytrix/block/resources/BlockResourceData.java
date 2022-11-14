package calytrix.block.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;
import calytrix.util.IResource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum BlockResourceData implements IBlockData, IResource {
    ADAMANTINE(ResourceType.ADAMANTINE, 8, 100, Material.HEAVY_METAL, CalytrixRarity.LEGENDARY, true, true),
    MITHRIL(ResourceType.MITHRIL, 6, 9, Material.HEAVY_METAL, Rarity.RARE, false, true);
    
    private static final Map<ResourceType, BlockResourceData> STORAGE_BLOCK_BY_RESOURCE_TYPE = init();
    
    private final ResourceType resourceType;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops;
    private final int lightLevel;

    BlockResourceData(
        ResourceType resourceType,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops
    ) {
        this(resourceType, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, 0);
    }
    
    public String resourceName() {
        return resourceType.getResourceName();
    }
    
    private static Map<ResourceType, BlockResourceData> init() {
        final Map<ResourceType, BlockResourceData> map = new LinkedHashMap<>();
        for(var block : BlockResourceData.values()) {
            map.put(block.resourceType, block);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
