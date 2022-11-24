package calytrix.providers.loot_tables;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import calytrix.providers.loot_tables.blocks.BaseBlockLoot;
import com.mojang.datafixers.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CalytrixLootTableProvider extends LootTableProvider {
    
    private final String modId;
    
    public CalytrixLootTableProvider(DataGenerator dataGenerator, String modId) {
        super(dataGenerator);
        this.modId = modId;
    }
    
    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> tables =
            new ArrayList<>();
    
        tables.add(Pair.of(BaseBlockLoot::new, LootContextParamSets.BLOCK));
    
        return tables;
    }
    
    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationtracker) {
    }
}
