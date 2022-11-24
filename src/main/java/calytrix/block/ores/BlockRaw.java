package calytrix.block.ores;

import lombok.Getter;

import calytrix.block.CalytrixBlock;

@Getter
public class BlockRaw extends CalytrixBlock {
    private final BlockOreData blockOreData;
    
    public BlockRaw(
        BlockOreData blockOreData
    ) {
        super(properties(blockOreData));
        this.blockOreData = blockOreData;
    }
}
