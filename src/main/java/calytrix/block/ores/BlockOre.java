package calytrix.block.ores;

import calytrix.block.CalytrixBlock;

public class BlockOre extends CalytrixBlock {
    
    public BlockOre(BlockOreData oreData) {
        super(properties(oreData));
    }
}
