package calytrix.util;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class CalytrixCreativeTab {
    public static final CreativeModeTab TAB_CALYTRIX = new CreativeModeTab(1, "calytrix") {
        public ItemStack makeIcon() {
        return new ItemStack(Blocks.AZALEA);
    }
    };
}
