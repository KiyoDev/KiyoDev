package calytrix.block.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

import calytrix.item.resources.ResourceType;
import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;

@Getter
@RequiredArgsConstructor
public enum BlockResourceData implements IBlockData {
    ADAMANTINE(ResourceType.ADAMANTINE, 8, 100, Material.HEAVY_METAL, CalytrixRarity.LEGENDARY, true, true),
    MITHRIL(ResourceType.MITHRIL, 6, 9, Material.HEAVY_METAL, Rarity.RARE, false, true);

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
}
