package calytrix.providers.tags;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import calytrix.util.ModTags;

public class ItemTagsProvider extends net.minecraft.data.tags.ItemTagsProvider {
    public ItemTagsProvider(
        DataGenerator dataGenerator,
        BlockTagsProvider blockTagsProvider,
        String modId,
        ExistingFileHelper existingFileHelper
    ) {
        super(dataGenerator, blockTagsProvider, modId, existingFileHelper);
    }
    
    @Override
    protected void addTags() {
        resourceIngotsTags();
    }
    
    private void resourceIngotsTags() {
        final var ingots = CalytrixItems.getResourceIngots();
        final var resourceForgeTagsByType = ModTags.Items.resourceForgeTagsByType();
    
        ingots.forEach((type, ingot) -> tag(resourceForgeTagsByType.get(type)).add(ingot.getItem().get()));
    }
    
    // private void forgeTags() {
    //     tag(ModTags.Items.ADAMANTINE_INGOT)
    //         .add(CalytrixItems.ADAMANTINE_INGOT.get());
    //     tag(ModTags.Items.MITHRIL_INGOT)
    //         .add(CalytrixItems.MITHRIL_INGOT.get());
    // }
}
