package calytrix.providers.tags;

import calytrix.item.CalytrixItems;
import calytrix.util.ModTags;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;

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
        forgeTags();
    }
    
    private void forgeTags() {
        tag(ModTags.Items.ADAMANTINE_INGOT)
            .add(CalytrixItems.ADAMANTINE_INGOT.get());
        tag(ModTags.Items.MITHRIL_INGOT)
            .add(CalytrixItems.MITHRIL_INGOT.get());
    }
}
