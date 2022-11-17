package calytrix.providers.loot_tables.blocks;

import net.minecraft.data.loot.BlockLoot;

import calytrix.block.CalytrixBlocks;
import calytrix.util.IBlockRegistryObject;

import java.util.Set;

public class BaseBlockLoot extends BlockLoot {
    @Override
    protected void addTables() {
        selfDropTable(CalytrixBlocks.BLOCKS.getAllRegisteredBlocks());
    }
    
    // Block block, LootItemCondition.Builder builder, LootPoolEntryContainer.Builder<?> container
    private void selfDropTable(Set<IBlockRegistryObject> blocks) {
        // createSelfDropDispatchTable();
    }
}
