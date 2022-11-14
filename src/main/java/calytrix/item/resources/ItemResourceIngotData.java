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

import javax.annotation.Nullable;

@Getter
@RequiredArgsConstructor
public enum ItemResourceIngotData implements IItemData, IResource {
    ADAMANTINE(BlockResourceData.ADAMANTINE, CalytrixRarity.LEGENDARY, true),
    MITHRIL(BlockResourceData.MITHRIL, Rarity.RARE, false);
    
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
    
    ItemResourceIngotData(
        BlockResourceData blockResourceData,
        Rarity rarity,
        boolean fireResistant
    ) {
        this(blockResourceData, 64, 0, rarity, fireResistant, false, null, null);
    }
    
    public String resourceName() {
        return blockResourceData.resourceName();
    }
    
    @Override
    public ResourceType getResourceType() {
        return blockResourceData.getResourceType();
    }
}
