package calytrix.providers.recipes;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.nio.file.Path;
import java.util.function.Consumer;

public abstract class CalytrixRecipeProvider extends RecipeProvider implements DataProvider {
    private final ExistingFileHelper existingFileHelper;
    
    public CalytrixRecipeProvider(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator);
        this.existingFileHelper = existingFileHelper;
    }
    
    private static Path recipesPath(Path path, String modId, String recipePath) {
        return path.resolve("data/%s/recipes/%s.json".formatted(modId, recipePath));
    }
    
    private static Path advancementsPath(Path path, String modId, ResourceLocation advancementId) {
        return path.resolve("data/%s/advancements/%s.json".formatted(modId, advancementId.getPath()));
    }
    
    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        Consumer<FinishedRecipe> trackingConsumer =
            consumer.andThen(recipe ->
                                 existingFileHelper.trackGenerated(recipe.getId(),
                                                                   PackType.SERVER_DATA,
                                                                   ".json",
                                                                   "recipes"));
        buildRecipes(trackingConsumer);
    }
    
    protected abstract void buildRecipes(Consumer<FinishedRecipe> consumer);
}
