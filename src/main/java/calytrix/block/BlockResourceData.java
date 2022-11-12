package calytrix.block;

import calytrix.util.CalytrixRarity;
import calytrix.util.IBlockData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.material.Material;

@Getter
@RequiredArgsConstructor
public enum BlockResourceData implements IBlockData {
    ADAMANTINE("adamantine", 8, 100, Material.HEAVY_METAL, CalytrixRarity.LEGENDARY, true, true),
    MITHRIL("mithril", 6, 9, Material.HEAVY_METAL, Rarity.RARE, false, true);

    private final String suffix;
    private final float strength;
    private final float resistance;
    private final Material material;
    private final Rarity rarity;
    private final boolean fireResistant;
    @Accessors(fluent = true)
    private final boolean requiresCorrectToolForDrops;
    private final int lightLevel;

    BlockResourceData(
        String suffix,
        float strength,
        float resistance,
        Material material,
        Rarity rarity,
        boolean fireResistant,
        boolean requiresCorrectToolForDrops
    ) {
        this(suffix, strength, resistance, material, rarity, fireResistant, requiresCorrectToolForDrops, 0);
    }

    @Override
    public Item.Properties blockItemProperties(CreativeModeTab tab) {
        Item.Properties properties = new Item.Properties().tab(tab).rarity(rarity);
        
        if(fireResistant) {
            properties = properties.fireResistant();
        }
        
        return properties;
    }
}
