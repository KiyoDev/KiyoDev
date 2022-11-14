package calytrix.providers.models;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockModelProvider extends net.minecraftforge.client.model.generators.BlockModelProvider {
    
    public BlockModelProvider(
        DataGenerator generator,
        String modid,
        ExistingFileHelper existingFileHelper
    ) {
        super(generator, modid, existingFileHelper);
    }
    
    @Override
    protected void registerModels() {
    
    }
}
