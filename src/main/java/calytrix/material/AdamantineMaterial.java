package calytrix.material;

import calytrix.item.resources.ResourceType;
import calytrix.item.tools.ToolTierType;
import calytrix.util.CalytrixRarity;

public class AdamantineMaterial extends BaseMaterial {
    public AdamantineMaterial() {
        super(ResourceType.ADAMANTINE,
              ToolTierType.ADAMANTINE.getTier(),
              new ArmorStats(4, 4, 2, 525),
              new ArmorStats(10, 4, 3, 730),
              new ArmorStats(8, 4, 3, 690),
              new ArmorStats(4, 4, 2, 625),
              CalytrixRarity.LEGENDARY,
              true);
    }
}
