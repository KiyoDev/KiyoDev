package calytrix.material;

import net.minecraft.world.item.Rarity;

import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;

public class MithrilMaterial extends BaseMaterial {
    public MithrilMaterial() {
        super(ResourceType.MITHRIL,
              ToolTierType.MITHRIL.getTier(),
              new ArmorStats(3, 2, 0, 363),
              new ArmorStats(8, 2, 0, 528),
              new ArmorStats(6, 2, 0, 495),
              new ArmorStats(3, 2, 0, 429),
              Rarity.RARE,
              false);
    }
}
