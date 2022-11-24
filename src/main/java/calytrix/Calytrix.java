package calytrix;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import com.mojang.logging.LogUtils;
import org.slf4j.Logger;

@Mod(Calytrix.MOD_ID)
public class Calytrix {
    public static final String MOD_ID = "calytrix";
    private static final Logger LOGGER = LogUtils.getLogger();
    
    public Calytrix() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        
        // Register Deferred Registers to the mod event bus so blocks get registered
        CalytrixBlocks.register(modEventBus);
        CalytrixItems.register(modEventBus);
    }
    
    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
    
    public static ResourceLocation toolsResourceLocation(String path) {
        return resourceLocation("tools/%s".formatted(path));
    }
    
    public static ResourceLocation toolsResourceLocation(RegistryObject<?> obj) {
        return resourceLocation("tools/%s".formatted(obj.getId().getPath()));
    }
    
    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.debug("HELLO FROM COMMON SETUP");
        LOGGER.debug("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }
    
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.debug("HELLO from server starting");
    }
    
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.debug("HELLO FROM CLIENT SETUP");
            LOGGER.debug("MINECRAFT NAME >> {}", Minecraft.getInstance()
                                                         .getUser()
                                                         .getName());
        }
    }
}
