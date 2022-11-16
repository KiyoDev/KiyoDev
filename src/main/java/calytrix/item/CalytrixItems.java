package calytrix.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import calytrix.block.ores.BlockOreData;
import calytrix.item.resources.ItemRawMaterial;
import calytrix.item.resources.ItemResource;
import calytrix.item.resources.ItemResourceMaterialData;
import calytrix.item.resources.ResourceType;
import calytrix.item.tools.BaseAxeItem;
import calytrix.item.tools.BaseHoeItem;
import calytrix.item.tools.BasePickaxeItem;
import calytrix.item.tools.BaseShovelItem;
import calytrix.item.tools.BaseSwordItem;
import calytrix.material.EquipmentMaterial;
import calytrix.registry.ItemDeferredRegister;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public class CalytrixItems {
    
    public static final ItemDeferredRegister ITEMS = new ItemDeferredRegister();
    
    private static final Map<ItemResourceMaterialData, ItemRegistryObject<ItemResource>> RESOURCE_INGOTS = new LinkedHashMap<>();
    private static final Map<ItemResourceMaterialData, ItemRegistryObject<ItemResource>> RESOURCE_DUST = new LinkedHashMap<>();
    
    private static final Map<BlockOreData, ItemRegistryObject<ItemRawMaterial>> RESOURCE_RAW = new LinkedHashMap<>();
    
    public static final Multimap<ResourceType, ItemRegistryObject<?>> TOOLS_MULTIMAP = ArrayListMultimap.create();
    
    public static final ItemRegistryObject<BasePickaxeItem> MITHRIL_PICKAXE = registerPickaxeItem(EquipmentMaterial.MITHRIL);
    public static final ItemRegistryObject<BaseAxeItem> MITHRIL_AXE = registerAxeItem(EquipmentMaterial.MITHRIL);
    public static final ItemRegistryObject<BaseShovelItem> MITHRIL_SHOVEL = registerShovelItem(EquipmentMaterial.MITHRIL);
    public static final ItemRegistryObject<BaseHoeItem> MITHRIL_HOE = registerHoeItem(EquipmentMaterial.MITHRIL);
    public static final ItemRegistryObject<BaseSwordItem> MITHRIL_SWORD = registerSwordItem(EquipmentMaterial.MITHRIL);
    
    public static final ItemRegistryObject<BasePickaxeItem> ADAMANTINE_PICKAXE =
        registerPickaxeItem(EquipmentMaterial.ADAMANTINE);
    public static final ItemRegistryObject<BaseAxeItem> ADAMANTINE_AXE = registerAxeItem(EquipmentMaterial.ADAMANTINE);
    public static final ItemRegistryObject<BaseShovelItem> ADAMANTINE_SHOVEL = registerShovelItem(EquipmentMaterial.ADAMANTINE);
    public static final ItemRegistryObject<BaseHoeItem> ADAMANTINE_HOE = registerHoeItem(EquipmentMaterial.ADAMANTINE);
    public static final ItemRegistryObject<BaseSwordItem> ADAMANTINE_SWORD = registerSwordItem(EquipmentMaterial.ADAMANTINE);
    
    static {
        for (var resource : ItemResourceMaterialData.values()) {
            RESOURCE_INGOTS.put(resource, registerResourceIngot(resource));
            RESOURCE_DUST.put(resource, registerResourceDust(resource));
        }
        
        for(var ore : BlockOreData.values()) {
            RESOURCE_RAW.put(ore, registerResourceRaw(ore));
        }
        
        TOOLS_MULTIMAP.put(ResourceType.MITHRIL, MITHRIL_PICKAXE);
        TOOLS_MULTIMAP.put(ResourceType.MITHRIL, MITHRIL_AXE);
        TOOLS_MULTIMAP.put(ResourceType.MITHRIL, MITHRIL_SHOVEL);
        TOOLS_MULTIMAP.put(ResourceType.MITHRIL, MITHRIL_HOE);
        TOOLS_MULTIMAP.put(ResourceType.MITHRIL, MITHRIL_SWORD);
        
        TOOLS_MULTIMAP.put(ResourceType.ADAMANTINE, ADAMANTINE_PICKAXE);
        TOOLS_MULTIMAP.put(ResourceType.ADAMANTINE, ADAMANTINE_AXE);
        TOOLS_MULTIMAP.put(ResourceType.ADAMANTINE, ADAMANTINE_SHOVEL);
        TOOLS_MULTIMAP.put(ResourceType.ADAMANTINE, ADAMANTINE_HOE);
        TOOLS_MULTIMAP.put(ResourceType.ADAMANTINE, ADAMANTINE_SWORD);
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceIngot(ItemResourceMaterialData resource) {
        return ITEMS.registerItem("%s_ingot".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    private static ItemRegistryObject<ItemResource> registerResourceDust(ItemResourceMaterialData resource) {
        return ITEMS.registerItem("%s_dust".formatted(resource.resourceName()),
                                  () -> new ItemResource(resource));
    }
    
    private static ItemRegistryObject<ItemRawMaterial> registerResourceRaw(BlockOreData ore) {
        return ITEMS.registerItem("raw_%s".formatted(ore.resourceName()),
                                  () -> new ItemRawMaterial(ore));
    }
    
    public static Map<ItemResourceMaterialData, ItemRegistryObject<ItemResource>> getResourceIngots() {
        return Collections.unmodifiableMap(RESOURCE_INGOTS);
    }
    
    public static Map<BlockOreData, ItemRegistryObject<ItemRawMaterial>> getRawMaterials() {
        return Collections.unmodifiableMap(RESOURCE_RAW);
    }
    
    public static Map<ItemResourceMaterialData, ItemRegistryObject<ItemResource>> getDusts() {
        return Collections.unmodifiableMap(RESOURCE_DUST);
    }
    
    private static <MAT extends EquipmentMaterial> void registerToolSet(MAT material) {
        registerPickaxeItem(material);
        registerAxeItem(material);
        registerShovelItem(material);
        registerHoeItem(material);
        registerSwordItem(material);
    }
    
    private static <MAT extends EquipmentMaterial> ItemRegistryObject<BasePickaxeItem> registerPickaxeItem(MAT material) {
        return registerItem("%s_pickaxe".formatted(material.resourceName()), material, BasePickaxeItem::new);
    }
    
    private static <MAT extends EquipmentMaterial> ItemRegistryObject<BaseAxeItem> registerAxeItem(MAT material) {
        return registerItem("%s_axe".formatted(material.resourceName()), material, BaseAxeItem::new);
    }
    
    private static <MAT extends EquipmentMaterial> ItemRegistryObject<BaseShovelItem> registerShovelItem(MAT material) {
        return registerItem("%s_shovel".formatted(material.resourceName()), material, BaseShovelItem::new);
    }
    
    private static <MAT extends EquipmentMaterial> ItemRegistryObject<BaseHoeItem> registerHoeItem(MAT material) {
        return registerItem("%s_hoe".formatted(material.resourceName()), material, BaseHoeItem::new);
    }
    
    private static <MAT extends EquipmentMaterial> ItemRegistryObject<BaseSwordItem> registerSwordItem(MAT material) {
        return registerItem("%s_sword".formatted(material.resourceName()), material, BaseSwordItem::new);
    }
    
    private static <ITEM extends Item, MAT extends EquipmentMaterial> ItemRegistryObject<ITEM> registerItem(
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
