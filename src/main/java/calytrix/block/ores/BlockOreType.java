package calytrix.block.ores;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.Tags;

import calytrix.util.ModTags;

@Getter
@RequiredArgsConstructor
public enum BlockOreType {
    
    STONE("", Tags.Items.ORES_IN_GROUND_STONE),
    DEEPSLATE("deepslate", Tags.Items.ORES_IN_GROUND_DEEPSLATE),
    NETHER("nether", Tags.Items.ORES_IN_GROUND_NETHERRACK),
    END("end", ModTags.Items.ORES_IN_GROUND_END);
    
    private final String prefix;
    private final TagKey<Item> inGroundTag;
}
