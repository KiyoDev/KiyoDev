package calytrix.item;

import static calytrix.Calytrix.MOD_ID;
import calytrix.util.CalytrixCreativeTab;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CalytrixItems {
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    
    public static final RegistryObject<Item> RAW_ADAMANTINE =
            ITEMS.register("raw_adamantine", () -> new Item(new Item.Properties()
                                                                    .fireResistant()
                                                                    .tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    
    public static final RegistryObject<Item> ADAMANTINE_INGOT =
            ITEMS.register("adamantine_ingot", () -> new Item(new Item.Properties()
                                                                      .fireResistant()
                                                                      .tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> MITHRIL_INGOT =
            ITEMS.register("mithril_ingot", () -> new Item(new Item.Properties()
                                                                   .fireResistant()
                                                                   .tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    
    public static final RegistryObject<Item> ADAMANTINE_PICKAXE = ITEMS.register("adamantine_pickaxe",
         () -> new PickaxeItem(ToolTiers.ADAMANTINE, 0, -2.8f,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> ADAMANTINE_SHOVEL = ITEMS.register("adamantine_shovel",
         () -> new PickaxeItem(ToolTiers.ADAMANTINE, 0, -3,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> ADAMANTINE_HOE = ITEMS.register("adamantine_hoe",
         () -> new PickaxeItem(ToolTiers.ADAMANTINE, -5, 0,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> ADAMANTINE_AXE = ITEMS.register("adamantine_axe",
         () -> new AxeItem(ToolTiers.ADAMANTINE, 5, -3f,
                           new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> ADAMANTINE_SWORD = ITEMS.register("adamantine_sword",
         () -> new SwordItem(ToolTiers.ADAMANTINE, 3, -2.4f,
                             new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    
    public static final RegistryObject<Item> MITHRIL_PICKAXE = ITEMS.register("mithril_pickaxe",
         () -> new PickaxeItem(ToolTiers.MITHRIL, 1, -2.8f,
                               new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> MITHRIL_SHOVEL = ITEMS.register("mithril_shovel",
         () -> new PickaxeItem(ToolTiers.ADAMANTINE, 0, -3,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> MITHRIL_HOE = ITEMS.register("mithril_hoe",
         () -> new PickaxeItem(ToolTiers.ADAMANTINE, -2, 1,
                               new Item.Properties().fireResistant().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> MITHRIL_AXE = ITEMS.register("mithril_axe",
         () -> new AxeItem(ToolTiers.MITHRIL, 5, -2.8f,
                           new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    public static final RegistryObject<Item> MITHRIL_SWORD = ITEMS.register("mithril_sword",
         () -> new SwordItem(ToolTiers.MITHRIL, 3, -1.8f,
                             new Item.Properties().tab(CalytrixCreativeTab.TAB_CALYTRIX)));
    
    public static void register(IEventBus eventBus) {
    
        
        ITEMS.register(eventBus);
    }
}
