package calytrix.providers.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.item.CalytrixItems;

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
        addToolsModels();
    }
    
    private void addToolsModels() {
        basicItem(CalytrixItems.ADAMANTINE_AXE.get());
    }
    
    private ItemModelBuilder handheldItem(ResourceLocation item) {
        return getBuilder(item.toString())
                   .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                   .texture("layer0", new ResourceLocation(item.getNamespace(), "item/" + item.getPath()));
    }
}
