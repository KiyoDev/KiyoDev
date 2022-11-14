package calytrix.material;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;

import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;

public class OrichalcumMaterial extends BaseMaterial {
    public OrichalcumMaterial() {
        super(ResourceType.ORICHALCUM,
              Tiers.NETHERITE,
              new ArmorStats(4, 3, 1, 407),
              new ArmorStats(8, 3, 2, 529),
              new ArmorStats(6, 3, 2, 555),
              new ArmorStats(4, 3, 1, 481),
              Rarity.EPIC,
              true);
    }
}
