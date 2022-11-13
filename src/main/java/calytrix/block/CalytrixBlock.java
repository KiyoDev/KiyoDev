package calytrix.block;

import net.minecraft.world.level.block.Block;

import calytrix.util.BlockProperties;
import calytrix.util.IBlockData;

public abstract class CalytrixBlock extends Block {
    
    public CalytrixBlock(Properties properties) {
        super(properties);
    }
    
    protected static <TYPE extends IBlockData> Properties properties(TYPE data) {
        final BlockProperties props = BlockProperties.builder(data.getMaterial())
                                                     .strength(data.getStrength(), data.getResistance())
                                                     .requiresCorrectToolForDrops(data.requiresCorrectToolForDrops())
                                                     .lightLevel(data.getLightLevel())
                                                     .build();
        return props.getProperties();
    }
}
