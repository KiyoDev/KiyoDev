package calytrix.item.resources;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import calytrix.block.resources.BlockResourceData;
import calytrix.util.CalytrixRarity;
import calytrix.util.IItemData;
import calytrix.util.IResource;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Nullable;

@Getter
@RequiredArgsConstructor
public enum ItemResourceMaterialData implements IItemData, IResource {
    ADAMANTINE(BlockResourceData.ADAMANTINE, CalytrixRarity.LEGENDARY, true),
    MITHRIL(BlockResourceData.MITHRIL, Rarity.RARE, false);
    
    private static final Map<ResourceType, ItemResourceMaterialData> DATA_BY_TYPE = init();
    
    private final BlockResourceData blockResourceData;
    private final int maxStackSize;
    private final int durability;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean canRepair;
    @Nullable
    private final Item craftRemainingItem;
    @Nullable
    private final FoodProperties foodProperties;
    
    ItemResourceMaterialData(
        BlockResourceData blockResourceData,
        Rarity rarity,
        boolean fireResistant
    ) {
        this(blockResourceData, 64, 0, rarity, fireResistant, false, null, null);
    }
    
    public String resourceName() {
        return blockResourceData.resourceName();
    }
    
    public static ItemResourceMaterialData fromType(ResourceType resourceType) {
        return DATA_BY_TYPE.get(resourceType);
    }
    
    @Override
    public ResourceType getResourceType() {
        return blockResourceData.getResourceType();
    }
    
    private static Map<ResourceType, ItemResourceMaterialData> init() {
        final Map<ResourceType, ItemResourceMaterialData> map = new LinkedHashMap<>();
        for(var data : ItemResourceMaterialData.values()) {
            map.put(data.getResourceType(), data);
        }
        
        return Collections.unmodifiableMap(map);
    }
}
