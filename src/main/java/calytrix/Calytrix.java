package calytrix;

import calytrix.block.CalytrixBlocks;
import calytrix.item.CalytrixItems;
import calytrix.registry.CalytrixRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Calytrix.MOD_ID)
public class Calytrix {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "calytrix";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Items which will all be registered under the "examplemod" namespace
    // public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    // public static final ItemRegistry ITEM_REGISTRY = new ItemRegistry();

    // // Creates a new Block with the id "examplemod:example_block", combining the namespace and path
    // public static final RegistryObject<Block> EXAMPLE_BLOCK =
    //     BLOCKS.register("example_block",
    //                     () -> new Block(BlockBehaviour.Properties.of(Material.STONE)));
    // Creates a new BlockItem with the id "examplemod:example_block", combining the namespace and path
    // public static final RegistryObject<Item> EXAMPLE_BLOCK_ITEM =
    //     ITEMS.register("example_block",
    //                    () -> new BlockItem(EXAMPLE_BLOCK.get(),
    //                                        new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS)));

    private static final CalytrixRegistry REGISTRY = new CalytrixRegistry();
    
    public Calytrix() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get()
                                                        .getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        // BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        // ITEMS.register(modEventBus);
        // ITEM_REGISTRY.register(modEventBus);
        
        // REGISTRY.register(modEventBus);
        
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    
        CalytrixBlocks.register(modEventBus);
        CalytrixItems.register(modEventBus);
    }
    
    public static ResourceLocation resourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance()
                                                         .getUser()
                                                         .getName());
        }
    }
}
