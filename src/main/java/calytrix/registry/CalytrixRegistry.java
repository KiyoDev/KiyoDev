package calytrix.registry;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import net.minecraftforge.eventbus.api.IEventBus;

public class CalytrixRegistry {
    
    public void register(IEventBus eventBus) {
        CalytrixItems.register(eventBus);
        // CalytrixBlocks.register(eventBus);
    }
}
