package calytrix.block.ores;

import static calytrix.block.ores.BlockOreType.DEEPSLATE;
import static calytrix.block.ores.BlockOreType.STONE;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

import calytrix.block.resources.BlockResourceData;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.IBlockData;
import calytrix.util.IResource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum BlockOreData implements IBlockData, IResource {
    ADAMANTINE(BlockResourceData.ADAMANTINE, 10, 25, Material.STONE, Rarity.RARE, true, true, new BlockOreType[]{DEEPSLATE}),
    MITHRIL(BlockResourceData.MITHRIL, 6, 9, Material.STONE, Rarity.RARE, false, true, new BlockOreType[]{STONE, DEEPSLATE});
    
    private static final Map<ResourceType, BlockOreData> STORAGE_BLOCK_BY_RESOURCE_TYPE = init();
    
    private final BlockResourceData blockResource;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops;
    private final BlockOreType[] types;
    private final int lightLevel;
    
    BlockOreData(
        BlockResourceData blockResource,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops,
        BlockOreType[] types
    ) {
        this(blockResource, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, types, 0);
    }
    
    public String resourceName() {
        return blockResource.getResourceType().getResourceName();
    }
    
    @Override
    public ResourceType getResourceType() {
        return blockResource.getResourceType();
    }
    
    private static Map<ResourceType, BlockOreData> init() {
        final Map<ResourceType, BlockOreData> map = new LinkedHashMap<>();
        for(var block : BlockOreData.values()) {
            map.put(block.blockResource.getResourceType(), block);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
