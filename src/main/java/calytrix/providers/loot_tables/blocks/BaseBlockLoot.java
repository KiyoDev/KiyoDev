package calytrix.providers.loot_tables.blocks;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import calytrix.block.BlockRegistryObject;
import calytrix.block.CalytrixBlocks;
import calytrix.block.ores.BlockOreData;
import calytrix.item.CalytrixItems;
import calytrix.util.IBlockData;
import calytrix.util.IBlockRegistryObject;
import com.mojang.logging.LogUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class BaseBlockLoot extends BlockLoot {
    private static final Logger LOGGER = LogUtils.getLogger();
    
    private final Set<Block> knownBlocks = new HashSet<>();
    
    @Override
    protected void addTables() {
        // selfDropTable(CalytrixBlocks.BLOCKS.getAllRegisteredBlocks());
        // CalytrixBlocks.BLOCKS.getAllRegisteredBlocks().forEach(b -> createSingleItemTable(b.getBlock()));
        
        CalytrixBlocks.getResourceStorageBlocks().forEach(this::createSelfDrop);
        CalytrixBlocks.getRawBlocks().forEach(this::createSelfDrop);
        CalytrixBlocks.getOreBlocks().forEach(this::createOreDrop);
    }
    
    @Override
    protected void add(@NotNull Block block, @NotNull LootTable.Builder table) {
        super.add(block, table);
        knownBlocks.add(block);
    }
    
    @NotNull
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return knownBlocks;
    }
    
    // Block block, LootItemCondition.Builder builder, LootPoolEntryContainer.Builder<?> container
    private void selfDropTable(Set<IBlockRegistryObject> blocks) {
        // createSelfDropDispatchTable();
        
    }
    
    private <BLOCK extends Block> void createSelfDrop(IBlockData data, BlockRegistryObject<BLOCK, Item> blockObj) {
        dropSelf(blockObj.getBlock());
    }
    
    private <BLOCK extends Block> void createOreDrop(BlockOreData data, BlockRegistryObject<BLOCK, Item> blockObj) {
        LOGGER.debug("ore: { data: {}, block:{} }", data, blockObj.getBlock());
        
        final var ore = blockObj.getBlock();
        final var rawMaterial = CalytrixItems.getRawMaterials().get(data).getItem();
        
        this.add(ore, (b) -> createOreDrop(b, rawMaterial));
    }
}
