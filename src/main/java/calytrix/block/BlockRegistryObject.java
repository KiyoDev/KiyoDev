package calytrix.block;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

@Getter
@RequiredArgsConstructor
public class BlockRegistryObject<BLOCK extends Block, ITEM extends Item> {
    private final RegistryObject<BLOCK> block;
    private final RegistryObject<ITEM> item;
    
    public String registry() {
        return block.getId().getPath();
    }
}
