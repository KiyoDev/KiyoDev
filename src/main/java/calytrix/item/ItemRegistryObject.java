package calytrix.item;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

@Getter
@RequiredArgsConstructor
public class ItemRegistryObject<ITEM extends Item> {
    private final RegistryObject<ITEM> item;
    
    public String registry() {
        return item.getId().getPath();
    }
}
