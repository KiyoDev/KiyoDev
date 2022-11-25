package calytrix.providers.loot_tables;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;

import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.mojang.datafixers.util.Pair;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTableBuilder {
    
    private final ImmutableList.Builder<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                                             LootContextParamSet>> tableBuilder = ImmutableList.builder();
    
    @CanIgnoreReturnValue
    public ImmutableList.Builder<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                                      LootContextParamSet>> add(
        Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                LootContextParamSet> pair
    ) {
        return tableBuilder.add(pair);
    }
    
    @SafeVarargs
    @CanIgnoreReturnValue
    public final ImmutableList.Builder<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                                         LootContextParamSet>> add(
        Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                LootContextParamSet>... pair
    ) {
        return tableBuilder.add(pair);
    }
    
    public ImmutableList.Builder<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>,
                                      LootContextParamSet>> addAll(
        Iterable<? extends Pair<Supplier<Consumer<BiConsumer<ResourceLocation,
                                                                LootTable.Builder>>>,
                                   LootContextParamSet>> pairs
    ) {
        return tableBuilder.addAll(pairs);
    }
    
    public List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> build() {
        return tableBuilder.build();
    }
}