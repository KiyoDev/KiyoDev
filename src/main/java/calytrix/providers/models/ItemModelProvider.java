package calytrix.providers.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import calytrix.item.CalytrixItems;
import calytrix.item.ItemRegistryObject;
import calytrix.item.resources.ResourceType;
import calytrix.material.EquipmentMaterial;

import java.util.Map;
import java.util.Objects;

public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {
    
    public ItemModelProvider(
        DataGenerator generator,
        String modid,
        ExistingFileHelper existingFileHelper
    ) {
        super(generator, modid, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
        addItems(CalytrixItems.getResourceIngots());
        addItems(CalytrixItems.getRawMaterials());
        addItems(CalytrixItems.getDusts());
        
        addToolsModels();
    }
    
    private void addToolsModels() {
        addTools(EquipmentMaterial.ADAMANTINE);
        addTools(EquipmentMaterial.MITHRIL);
    }
    
    private void addTools(EquipmentMaterial equipmentMaterial) {
        var toolSet = CalytrixItems.TOOL_SETS_BY_RESOURCE_TYPE.get(equipmentMaterial);
        
        basicHandheldItem(toolSet.pickaxe().getItem());
        basicHandheldItem(toolSet.axe().getItem());
        basicHandheldItem(toolSet.shovel().getItem());
        basicHandheldItem(toolSet.hoe().getItem());
        basicHandheldItem(toolSet.sword().getItem());
    }
    
    private void addItems(Map<?, ? extends ItemRegistryObject<?>> items) {
        for(var entry : items.entrySet()) {
            basicItem(entry.getValue().getItem());
        }
    }
    
    public ItemModelBuilder basicHandheldItem(Item item) {
        return basicHandheldItem(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item)));
    }
    
    private ItemModelBuilder basicHandheldItem(ResourceLocation item) {
        return getBuilder(item.toString())
                   .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                   .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}
