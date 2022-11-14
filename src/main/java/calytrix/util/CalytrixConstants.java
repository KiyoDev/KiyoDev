package calytrix.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class CalytrixConstants {
    
    public static final CreativeModeTab CALYTRIX_TAB = new CreativeModeTab(1, "calytrix") {
        public ItemStack makeIcon() {
            return new ItemStack(Blocks.AZALEA);
        }
    };
    
    private CalytrixConstants() {
        // Utility
    }
}
