package calytrix.block.ores;

import lombok.Getter;

import calytrix.block.CalytrixBlock;

@Getter
public class BlockOre extends CalytrixBlock {
    
    private final BlockOreType oreType;
    
    public BlockOre(
        BlockOreData oreData,
        BlockOreType oreType
    ) {
        super(properties(oreData));
        this.oreType = oreType;
    }
}
