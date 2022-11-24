package calytrix.item.tools;

import calytrix.item.ItemRegistryObject;

public record BaseToolSet(
    ItemRegistryObject<BasePickaxeItem> pickaxe,
    ItemRegistryObject<BaseAxeItem> axe,
    ItemRegistryObject<BaseShovelItem> shovel,
    ItemRegistryObject<BaseHoeItem> hoe,
    ItemRegistryObject<BaseSwordItem> sword
) {
}
