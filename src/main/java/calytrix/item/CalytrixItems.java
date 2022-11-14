package calytrix.item;

import calytrix.item.resources.ItemResource;
import calytrix.item.resources.ItemResourceIngotData;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.BaseAxeItem;
import calytrix.item.tools.ToolTierType;
import calytrix.material.BaseMaterial;
import calytrix.registry.ItemDeferredRegister;
import calytrix.util.CalytrixConstants;

import net.minecraft.world.item.AxeItem;
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
import java.util.function.Function;

public class CalytrixItems {
    
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister();
    
    private static final Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> RESOURCE_INGOTS = new LinkedHashMap<>();
    private static final Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> RESOURCE_RAW = new LinkedHashMap<>();
    private static final Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> RESOURCE_DUST = new LinkedHashMap<>();
    
    static {
        for (var resource : ItemResourceIngotData.values()) {
            RESOURCE_INGOTS.put(resource, registerResourceIngot(resource));
            RESOURCE_RAW.put(resource, registerResourceRaw(resource));
            RESOURCE_DUST.put(resource, registerResourceDust(resource));
        }
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceIngot(ItemResourceIngotData resource) {
        return ITEMS.registerItem("%s_ingot".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceRaw(ItemResourceIngotData resource) {
        return ITEMS.registerItem("raw_%s".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceDust(ItemResourceIngotData resource) {
        return ITEMS.registerItem("%s_dust".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    public static Map<ItemResourceIngotData, ItemRegistryObject<ItemResource>> getResourceIngots() {
        return Collections.unmodifiableMap(RESOURCE_INGOTS);
    }
    
    // public static final RegistryObject<Item> RAW_ADAMANTINE =
    //     ITEMS.registerItem("raw_adamantine", () -> new Item(new Item.Properties()
    //                                                             .fireResistant()
    //                                                             .tab(ModCalytrixConstants.CALYTRIX_TAB))).getItem();
    
    // TODO - change tool registration
    
    public static final RegistryObject<PickaxeItem> ADAMANTINE_PICKAXE =
        ITEMS.registerItem("adamantine_pickaxe",
                           () -> new PickaxeItem(ToolTierType.ADAMANTINE.getTier(), 0, -2.8f,
                                                 new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<ShovelItem> ADAMANTINE_SHOVEL =
        ITEMS.registerItem("adamantine_shovel",
                           () -> new ShovelItem(ToolTierType.ADAMANTINE.getTier(), 0, -3,
                                                new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<HoeItem> ADAMANTINE_HOE =
        ITEMS.registerItem("adamantine_hoe",
                           () -> new HoeItem(ToolTierType.ADAMANTINE.getTier(), -5, 0,
                                             new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<AxeItem> ADAMANTINE_AXE =
        ITEMS.registerItem("adamantine_axe",
                           () -> new AxeItem(ToolTierType.ADAMANTINE.getTier(), 5, -3f,
                                             new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<SwordItem> ADAMANTINE_SWORD =
        ITEMS.registerItem("adamantine_sword",
                           () -> new SwordItem(ToolTierType.ADAMANTINE.getTier(), 3, -2.4f,
                                               new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    
    public static final RegistryObject<PickaxeItem> MITHRIL_PICKAXE =
        ITEMS.registerItem("mithril_pickaxe",
                           () -> new PickaxeItem(ToolTierType.MITHRIL.getTier(), 1, -2.8f,
                                                 new Item.Properties().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<ShovelItem> MITHRIL_SHOVEL =
        ITEMS.registerItem("mithril_shovel",
                           () -> new ShovelItem(ToolTierType.MITHRIL.getTier(), 0, -3,
                                                new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<HoeItem> MITHRIL_HOE =
        ITEMS.registerItem("mithril_hoe",
                           () -> new HoeItem(ToolTierType.MITHRIL.getTier(), -2, 1,
                                             new Item.Properties().fireResistant().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<AxeItem> MITHRIL_AXE =
        ITEMS.registerItem("mithril_axe",
                           () -> new AxeItem(ToolTierType.MITHRIL.getTier(), 5, -2.8f,
                                             new Item.Properties().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    public static final RegistryObject<SwordItem> MITHRIL_SWORD =
        ITEMS.registerItem("mithril_sword",
                           () -> new SwordItem(ToolTierType.MITHRIL.getTier(), 3, -1.8f,
                                               new Item.Properties().tab(CalytrixConstants.CALYTRIX_TAB))).getItemObj();
    
    private static <MAT extends BaseMaterial> void registerToolSet(MAT material) {
        registerItem("%s_pickaxe".formatted(material.resourceName()), material, BaseAxeItem::new);
        registerItem("%s_axe".formatted(material.resourceName()), material, BaseAxeItem::new);
        registerItem("%s_shovel".formatted(material.resourceName()), material, BaseAxeItem::new);
        registerItem("%s_hoe".formatted(material.resourceName()), material, BaseAxeItem::new);
        registerItem("%s_sword".formatted(material.resourceName()), material, BaseAxeItem::new);
    }
    
    private static <ITEM extends Item, MAT extends BaseMaterial> ItemRegistryObject<ITEM> registerItem(
        String toolName,
        MAT material,
        Function<MAT, ITEM> supplier
    ) {
        return ITEMS.registerItem(toolName, () -> supplier.apply(material));
    }
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
