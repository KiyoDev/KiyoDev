package calytrix.providers.tags;

import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class CalytrixTagProvider<TYPE> extends TagsProvider<TYPE> {
    protected CalytrixTagProvider(
        DataGenerator dataGenerator,
        Registry<TYPE> registry,
        String modId, ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, registry, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
    
    }
}
