package calytrix.block.resources;

import calytrix.block.CalytrixBlock;

public class BlockResource extends CalytrixBlock {
    
    public BlockResource(BlockResourceData resourceData) {
        super(properties(resourceData));
    }
}
