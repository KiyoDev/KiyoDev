package calytrix.providers.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import calytrix.block.BlockRegistryObject;
import calytrix.block.CalytrixBlocks;
import com.google.common.collect.Multimap;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Map;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public BlockStateProvider(
        DataGenerator generator,
        String modId,
        ExistingFileHelper existingFileHelper
    ) {
        super(generator, modId, existingFileHelper);
    }
    
    @Override
    protected void registerStatesAndModels() {
        addBlockItems(CalytrixBlocks.getResourceStorageBlocks());
        addBlockItems(CalytrixBlocks.getOreBlocks());
        addBlockItems(CalytrixBlocks.getRawBlocks());
    }
    
    private void addBlockItems(Map<?, ? extends BlockRegistryObject<?, ?>> items) {
        for (var entry : items.entrySet()) {
            final var blockObj = entry.getValue().getBlockObj();
            final var block = blockObj.get();
    
            simpleBlock(block);
            simpleBlockItem(block, blockFile(block));
        }
    }
    
    private void addBlockItems(Multimap<?, ? extends BlockRegistryObject<?, ?>> items) {
        for (var entry : items.entries()) {
            final var blockObj = entry.getValue().getBlockObj();
            final var block = blockObj.get();
    
            simpleBlock(block);
            simpleBlockItem(block, blockFile(block));
        }
    }
    
    private ModelFile blockFile(Block block) {
        return new ModelFile.UncheckedModelFile(blockTexture(block));
    }
}
