package com.github.ilja615.worldupgrade;

import com.github.ilja615.worldupgrade.blocks.BrambleBushBlock;
import com.github.ilja615.worldupgrade.blocks.DoubleReedPlantBlock;
import com.github.ilja615.worldupgrade.blocks.DriedExtraReedBlock;
import com.github.ilja615.worldupgrade.blocks.ExtraReedBlock;
import com.github.ilja615.worldupgrade.client.ModRenderRegistry;
import com.github.ilja615.worldupgrade.entities.BeaverEntity;
import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.github.ilja615.worldupgrade.entities.GribberEntity;
import com.github.ilja615.worldupgrade.entities.SpoonBillEntity;
import com.github.ilja615.worldupgrade.init.*;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Mod(WorldUpgrade.MOD_ID)
public class WorldUpgrade
{
    public static final String MOD_ID = "worldupgrade";

    public WorldUpgrade()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModFeatures.FEATURES.register(modEventBus);
        ModSurfaceBuilders.SURFACE_BUILDERS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        ModBiomes.BIOMES.register(modEventBus);
        ModBiomes.registerBiomes();
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(WorldUpgrade::afterCommonSetup);
        ModEntities.SetupEntityAttributes();
    }

    static void afterCommonSetup()
    {
        ModBlocks.initializeLists();
        ForgeRegistries.WORLD_CARVERS.getEntries().forEach(c ->
        {
            Set<Block> cb = new HashSet<Block>(c.getValue().replaceableBlocks);
            for (Block block : ModBlocks.CARVABLE_BLOCKS) cb.add(block);
            c.getValue().replaceableBlocks = cb;
        });
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
            ModEntities.registerEntityWorldSpawns();
        }

        @SubscribeEvent
        public static void onRegisterItems(final RegistryEvent.Register<Item> event)
        {
            final IForgeRegistry<Item> registry = event.getRegistry();
            ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block ->
            {
                if ((block instanceof ExtraReedBlock) || (block instanceof DriedExtraReedBlock))
                {
                    // it not register item.
                } else {
                    BlockItem blockItem;
                    if (block instanceof BrambleBushBlock) {
                        blockItem = new BlockItem(block, ModProperties.BRAMBLE_FOOD_ITEM_PROPERTY);
                    } else {
                        blockItem = new BlockItem(block, ModProperties.ITEM_PROPERTY);
                    }
                    blockItem.setRegistryName(Objects.requireNonNull(block.getRegistryName()));
                    registry.register(blockItem);
                }
            });
            ModEntities.registerEntitySpawnEggs(event); //It registers the spawn egg items
        }
    }
}
