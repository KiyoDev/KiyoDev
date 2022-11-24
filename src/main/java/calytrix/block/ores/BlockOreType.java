package calytrix.block.ores;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockOreType {
    
    STONE(""),
    DEEPSLATE("deepslate"),
    NETHER("nether"),
    END("end");
    
    private final String prefix;
}
