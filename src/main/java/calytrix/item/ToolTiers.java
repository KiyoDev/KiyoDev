package calytrix.item;

import calytrix.Calytrix;
import calytrix.util.ModTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import java.util.List;

public class ToolTiers {
    public static Tier ADAMANTINE;
    public static Tier MITHRIL;
    
    private static final ForgeTier FORGE_ADAMANTINE = new ForgeTier(5,
                                                                    4000,
                                                                    10f,
                                                                    6,
                                                                    22,
                                                                    ModTags.Blocks.NEEDS_ADAMANTINE_TOOL,
                                                                    () -> Ingredient.of(CalytrixItems.ADAMANTINE_INGOT.get()));
    
    private static final ForgeTier FORGE_MITHRIL = new ForgeTier(4,
                                                                 1561,
                                                                 25f,
                                                                 3,
                                                                 10,
                                                                 ModTags.Blocks.NEEDS_MITHRIL_TOOL,
                                                                 () -> Ingredient.of(CalytrixItems.MITHRIL_INGOT.get()));
    
    static {
        ADAMANTINE = TierSortingRegistry.registerTier(
            FORGE_ADAMANTINE, Calytrix.resourceLocation("adamantine"), List.of(Tiers.NETHERITE), List.of());
        
        MITHRIL = TierSortingRegistry.registerTier(
            FORGE_MITHRIL, Calytrix.resourceLocation("mithril"), List.of(Tiers.DIAMOND), List.of(Tiers.NETHERITE));
    }
}
