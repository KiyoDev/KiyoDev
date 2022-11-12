package calytrix.block;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

import calytrix.util.IBlockData;
import calytrix.util.ItemPropertiesBuilder;

@Getter
@RequiredArgsConstructor
public enum BlockOreData implements IBlockData {
    ADAMANTINE(BlockResourceData.ADAMANTINE, 10, 25, Material.STONE, Rarity.RARE, true, true),
    MITHRIL(BlockResourceData.MITHRIL, 6, 9, Material.STONE, Rarity.RARE, false, true);
    
    private final BlockResourceData blockResource;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    private final boolean requiresCorrectToolForDrops;
    private final int lightLevel;
    
    BlockOreData(
        BlockResourceData blockResource,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops
    ) {
        this(blockResource, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, 0);
    }
    
    @Override
    public Item.Properties blockItemProperties(CreativeModeTab tab) {
        return ItemPropertiesBuilder.builder()
                                    .tab(tab)
                                    .rarity(rarity)
                                    .fireResistant(fireResistant)
                                    .canRepair(false)
                                    .build();
    }
}
