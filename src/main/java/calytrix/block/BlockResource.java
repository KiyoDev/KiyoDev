package calytrix.block;

import net.minecraft.world.level.block.Block;

import calytrix.util.BlockProperties;

public class BlockResource extends Block {
    
    public BlockResource(BlockResourceData resourceData) {
        super(properties(resourceData));
    }
    
    private static Properties properties(BlockResourceData resourceData) {
        final BlockProperties props = BlockProperties.builder(resourceData.getMaterial())
                                                     .strength(resourceData.getStrength(), resourceData.getResistance())
                                                     .requiresCorrectToolForDrops(resourceData.requiresCorrectToolForDrops())
                                                     .lightLevel(resourceData.getLightLevel())
                                                     .build();
        return props.getProperties();
    }
}
