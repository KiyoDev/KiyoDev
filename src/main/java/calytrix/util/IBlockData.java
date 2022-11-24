package calytrix.util;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;

import calytrix.item.ItemPropertiesBuilder;
import calytrix.item.tools.ToolTierType;

public interface IBlockData {
    float getStrength();
    
    float getResistance();
    
    Material getMaterial();
    
    Rarity getRarity();
    
    boolean isFireResistant();
    
    boolean requiresCorrectToolForDrops();
    
    int getLightLevel();
    
    TagKey<Block> requiredToolTier();
    
    default Item.Properties blockItemProperties(CreativeModeTab tab) {
        return ItemPropertiesBuilder.builder()
                                    .tab(tab)
                                    .rarity(getRarity())
                                    .fireResistant(isFireResistant())
                                    .canRepair(false)
                                    .build();
    }
}
