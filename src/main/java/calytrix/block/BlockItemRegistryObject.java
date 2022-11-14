package calytrix.block;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import calytrix.util.IItemRegistryObject;

@Getter
@RequiredArgsConstructor
public class BlockItemRegistryObject<BLOCK extends Block, ITEM extends Item> implements IItemRegistryObject<ITEM> {
    private final RegistryObject<BLOCK> blockObj;
    private final RegistryObject<ITEM> itemObj;
    
    public String registry() {
        return blockObj.getId().getPath();
    }
    
    public ITEM getItem() {
        return itemObj.get();
    }
}
