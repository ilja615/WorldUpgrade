package ilja615.worldupgrade;

import ilja615.worldupgrade.blocks.*;
import ilja615.worldupgrade.blocks.special.FireJet;
import ilja615.worldupgrade.blocks.special.SmokeVent;
import ilja615.worldupgrade.client.ModRenderRegistry;
import ilja615.worldupgrade.init.ModEntities;
import ilja615.worldupgrade.util.ItemGroupWU;
import ilja615.worldupgrade.init.ModBlocks;
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
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.client.event.ModelRegistryEvent;

import javax.swing.text.html.parser.Entity;

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
    public static class RegistryEvents
    {
        @SubscribeEvent
        public static void clientSetup(final FMLClientSetupEvent event)
        {
            ModRenderRegistry.registerEntityRenderers();
        }

        @SubscribeEvent
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
            event.getRegistry().registerAll
            (
                    new BlockItem(ModBlocks.GRAVEL_DARK, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("gravel_dark"),
                    new BlockItem(ModBlocks.GRAVEL_LIGHT, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("gravel_light"),
                    new BlockItem(ModBlocks.DEAD_LOG, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("dead_log"),
                    new BlockItem(ModBlocks.SMOKE_VENT, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("smoke_vent"),
                    new BlockItem(ModBlocks.FIRE_JET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("fire_jet"),
                    new BlockItem(ModBlocks.DEAD_LEAVES, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("dead_leaves"),
                    new BlockItem(ModBlocks.DEAD_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("dead_leaves_carpet"),
                    new BlockItem(ModBlocks.OAK_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("oak_leaves_carpet"),
                    new BlockItem(ModBlocks.BIRCH_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("birch_leaves_carpet"),
                    new BlockItem(ModBlocks.SPRUCE_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("spruce_leaves_carpet"),
                    new BlockItem(ModBlocks.JUNGLE_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("jungle_leaves_carpet"),
                    new BlockItem(ModBlocks.ACACIA_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("acacia_leaves_carpet"),
                    new BlockItem(ModBlocks.DARK_OAK_LEAVES_CARPET, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("dark_oak_leaves_carpet"),
                    new BlockItem(ModBlocks.JUNGLE_ROCK, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("jungle_rock"),
                    new BlockItem(ModBlocks.JUNGLE_ROCK_OVERGROWN, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("jungle_rock_overgrown"),
                    new BlockItem(ModBlocks.COBBLEWEBSTONE, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("cobblewebstone"),
                    new BlockItem(ModBlocks.JUNGLE_COBBLE, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("jungle_cobble"),
                    new BlockItem(ModBlocks.JUNGLE_FOSSIL, new Item.Properties().group(ItemGroupWU.instance)).setRegistryName("jungle_fossil")
            );

            ModEntities.registerEntitySpawnEggs(event);

        }

        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event)
        {
            event.getRegistry().registerAll
            (
                ModEntities.WEB_SPIDER
            );
            ModEntities.registerEntityWorldSpawns();
        }
    }

}
