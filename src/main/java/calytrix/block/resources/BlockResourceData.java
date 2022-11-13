package calytrix.block.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

import calytrix.item.resources.Resource;
import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;

@Getter
@RequiredArgsConstructor
public enum BlockResourceData implements IBlockData {
    ADAMANTINE(Resource.ADAMANTINE, 8, 100, Material.HEAVY_METAL, CalytrixRarity.LEGENDARY, true, true),
    MITHRIL(Resource.MITHRIL, 6, 9, Material.HEAVY_METAL, Rarity.RARE, false, true);

    private final Resource resource;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops;
    private final int lightLevel;

    BlockResourceData(
        Resource resource,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops
    ) {
        this(resource, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, 0);
    }
    
    public String resourceName() {
        return resource.getResourceName();
    }
}
