package calytrix.common;

import calytrix.Calytrix;
import calytrix.providers.models.ItemModelProvider;
import calytrix.providers.recipes.ShapedRecipesProvider;
import calytrix.providers.recipes.SmithingRecipesProvider;
import calytrix.providers.tags.BlockTagsProvider;
import calytrix.providers.tags.ItemTagsProvider;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import org.slf4j.Logger;

@EventBusSubscriber(modid = Calytrix.MOD_ID, bus = Bus.MOD)
public class CalytrixDataGenerator {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    private CalytrixDataGenerator() {
        // Utility
    }
    
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        LOGGER.info("CalytrixDataGenerator...");
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        final boolean includeClient = event.includeClient();
        final String modId = Calytrix.MOD_ID;
    
        // Tags
        final BlockTagsProvider blockTagsProvider = new BlockTagsProvider(generator, modId, existingFileHelper);
        generator.addProvider(includeClient, blockTagsProvider);
        generator.addProvider(includeClient, new ItemTagsProvider(generator, blockTagsProvider, modId, existingFileHelper));
        
        // Recipes
        generator.addProvider(includeClient, new ShapedRecipesProvider(generator, existingFileHelper));
        generator.addProvider(includeClient, new SmithingRecipesProvider(generator, existingFileHelper));
        
        // Loot
        
        // Models
        generator.addProvider(includeClient, new ItemModelProvider(generator, modId, existingFileHelper));
        
        // Misc
    }
}
