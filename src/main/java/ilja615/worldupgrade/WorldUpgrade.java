package ilja615.worldupgrade;

import ilja615.worldupgrade.client.ModRenderRegistry;
import ilja615.worldupgrade.init.*;
import ilja615.worldupgrade.proxy.ClientProxy;
import ilja615.worldupgrade.proxy.IProxy;
import ilja615.worldupgrade.proxy.ServerProxy;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import static ilja615.worldupgrade.WorldUpgrade.MOD_ID;

@Mod(MOD_ID)
public class WorldUpgrade
{
    public static final String MOD_ID = "worldupgrade";
    public static IProxy proxy = DistExecutor.runForDist(() -> () -> new ClientProxy(), () -> () -> new ServerProxy());

    public WorldUpgrade()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        proxy.init();
    }



    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event) {
            ModRenderRegistry.registerEntityRenderers();
        }

        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().registerAll
                    (
                            ModEntities.WEB_SPIDER,
                            ModEntities.BABY_SPIDER
                    );
            ModEntities.registerEntityWorldSpawns();
        }

        @SubscribeEvent
        public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
            final IForgeRegistry<Item> registry = event.getRegistry();
            ModBlocksNew.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
                final BlockItem blockItem = new BlockItem(block, ModProperties.ITEM_PROPERTY);
                blockItem.setRegistryName(block.getRegistryName());
                registry.register(blockItem);
            });
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> event)
        {
            ModEntities.registerEntitySpawnEggs(event); //It registers the spawn egg items
        }

        /*@SubscribeEvent

        [OLD CODE]

        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event)
        {
            for (Block block : ModBlocks.BLOCKS)
            {
                event.getRegistry().register(block);
            }
        }

        @SubscribeEvent
        public static void onItemRegistry(final RegistryEvent.Register<Item> event)
        {
            for (Block block : ModBlocks.BLOCKS)
            {
                event.getRegistry().register(new BlockItem(block, ModProperties.ITEM_PROPERTY).setRegistryName(block.getRegistryName()));
            }
            for (Item item : ModItems.ITEMS)
            {
                event.getRegistry().register(item);
            }
            ModEntities.registerEntitySpawnEggs(event);
        }*/
    }
}
