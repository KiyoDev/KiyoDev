package calytrix.util;

import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class CalytrixRarity {
    public static final Rarity LEGENDARY = Rarity.create("Legendary", ChatFormatting.GOLD);
    
    private CalytrixRarity() {
        // Utility
    }
}
