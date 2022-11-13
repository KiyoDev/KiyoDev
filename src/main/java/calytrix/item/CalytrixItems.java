package calytrix.item;

import calytrix.item.resources.ItemResource;
import calytrix.item.resources.ItemResourceIngotData;
import calytrix.registry.ItemDeferredRegister;
import calytrix.util.CalytrixCreativeTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class CalytrixItems {
    
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister();
    
    private static final Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> RESOURCE_INGOTS =
        new LinkedHashMap<>();
    
    static {
        for(var resource : ItemResourceIngotData.values()) {
            RESOURCE_INGOTS.put(resource, registerResourceIngot(resource));
        }
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceIngot(ItemResourceIngotData resource) {
        return ITEMS.registerItem("%s_ingot".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    public static Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> getResourceIngots() {
        return Collections.unmodifiableMap(RESOURCE_INGOTS);
    }
    
    public static final RegistryObject<Item> RAW_ADAMANTINE =
        ITEMS.registerItem("raw_adamantine", () -> new Item(new Item.Properties()
                                                                    .fireResistant()
                                                                    .tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    
    // TODO - change tool registration
    
    public static final RegistryObject<PickaxeItem> ADAMANTINE_PICKAXE = ITEMS.registerItem("adamantine_pickaxe",
                                                                                     () -> new PickaxeItem(ToolTiers.ADAMANTINE, 0, -2.8f,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<ShovelItem> ADAMANTINE_SHOVEL = ITEMS.registerItem("adamantine_shovel",
                                                                                          () -> new ShovelItem(ToolTiers.ADAMANTINE, 0,
                                                                                                       -3,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<HoeItem> ADAMANTINE_HOE = ITEMS.registerItem("adamantine_hoe",
                                                                                    () -> new HoeItem(ToolTiers.ADAMANTINE, -5, 0,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<AxeItem> ADAMANTINE_AXE = ITEMS.registerItem("adamantine_axe",
                                                                                 () -> new AxeItem(ToolTiers.ADAMANTINE, 5, -3f,
                           new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<SwordItem> ADAMANTINE_SWORD = ITEMS.registerItem("adamantine_sword",
                                                                                   () -> new SwordItem(ToolTiers.ADAMANTINE, 3, -2.4f,
                             new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    
    public static final RegistryObject<PickaxeItem> MITHRIL_PICKAXE = ITEMS.registerItem("mithril_pickaxe",
                                                                                  () -> new PickaxeItem(ToolTiers.MITHRIL, 1, -2.8f,
                               new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<ShovelItem> MITHRIL_SHOVEL = ITEMS.registerItem("mithril_shovel",
                                                                                 () -> new ShovelItem(ToolTiers.MITHRIL, 0, -3,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<HoeItem> MITHRIL_HOE = ITEMS.registerItem("mithril_hoe",
                                                                              () -> new HoeItem(ToolTiers.MITHRIL, -2, 1,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<AxeItem> MITHRIL_AXE = ITEMS.registerItem("mithril_axe",
                                                                              () -> new AxeItem(ToolTiers.MITHRIL, 5, -2.8f,
                           new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    public static final RegistryObject<SwordItem> MITHRIL_SWORD = ITEMS.registerItem("mithril_sword",
                                                                                () -> new SwordItem(ToolTiers.MITHRIL, 3, -1.8f,
                             new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX))).getItem();
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
