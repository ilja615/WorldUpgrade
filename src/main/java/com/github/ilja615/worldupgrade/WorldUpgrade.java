package com.github.ilja615.worldupgrade;

import com.github.ilja615.worldupgrade.blocks.BrambleBushBlock;
import com.github.ilja615.worldupgrade.client.ModRenderRegistry;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.init.ModEntities;
import com.github.ilja615.worldupgrade.init.ModItems;
import com.github.ilja615.worldupgrade.init.ModProperties;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.Objects;

@Mod(WorldUpgrade.MOD_ID)
public class WorldUpgrade
{
    public static final String MOD_ID = "worldupgrade";

    public WorldUpgrade()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
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
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event)
        {
            event.getRegistry().registerAll(
                    ModEntities.BUBBLE_EEL,
                    ModEntities.SPOONBILL
            );
            ModEntities.registerEntityWorldSpawns();
        }

        @SubscribeEvent
        public static void onRegisterItems(final RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();
            ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
            {
                BlockItem blockItem;
                if (block instanceof BrambleBushBlock)
                {
                    blockItem = new BlockItem(block, ModProperties.BRAMBLE_FOOD_ITEM_PROPERTY);
                } else
                {
                    blockItem = new BlockItem(block, ModProperties.ITEM_PROPERTY);
                }
                blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                registry.register(blockItem);
            });
            ModEntities.registerEntitySpawnEggs(event); //It registers the spawn egg items
        }
    }
}
