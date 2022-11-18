package calytrix.providers.loot_tables.blocks;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootTable;

import calytrix.block.BlockRegistryObject;
import calytrix.block.CalytrixBlocks;
import calytrix.block.ores.BlockOreData;
import calytrix.item.CalytrixItems;
import calytrix.util.IBlockData;
import calytrix.util.IBlockRegistryObject;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

import java.util.Set;
import java.util.function.Function;

public class BaseBlockLoot extends BlockLoot {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    @Override
    protected void addTables() {
        super.addTables();
        // selfDropTable(CalytrixBlocks.BLOCKS.getAllRegisteredBlocks());
        // CalytrixBlocks.BLOCKS.getAllRegisteredBlocks().forEach(b -> createSingleItemTable(b.getBlock()));
        
        CalytrixBlocks.getResourceStorageBlocks().forEach(this::createSelfDrop);
        CalytrixBlocks.getRawBlocks().forEach(this::createSelfDrop);
        CalytrixBlocks.getOreBlocks().forEach(this::createOreDrop);
    }
    
    // Block block, LootItemCondition.Builder builder, LootPoolEntryContainer.Builder<?> container
    private void selfDropTable(Set<IBlockRegistryObject> blocks) {
        // createSelfDropDispatchTable();
        
    }
    
    private <BLOCK extends Block> void createSelfDrop(IBlockData data, BlockRegistryObject<BLOCK, Item> blockObj) {
        dropSelf(blockObj.getBlock());
    }
    
    private <BLOCK extends Block> void createOreDrop(BlockOreData data, BlockRegistryObject<BLOCK, Item> blockObj) {
        LOGGER.info("ore: { data: {}, block:{} }", data, blockObj.getBlock());
        
        final var ore = blockObj.getBlock();
        final var rawMaterial = CalytrixItems.getRawMaterials().get(data).getItem();
        
        this.add(ore, (b) -> createOreDrop(b, rawMaterial));
    }
}
