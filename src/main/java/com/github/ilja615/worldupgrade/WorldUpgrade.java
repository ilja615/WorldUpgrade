package com.github.ilja615.worldupgrade;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.github.ilja615.worldupgrade.WorldUpgrade.MOD_ID;

@Mod(MOD_ID)
public class WorldUpgrade
{
    public static final String MOD_ID = "worldupgrade";

    public WorldUpgrade()
    {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::clientSetup);

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(WorldUpgrade::afterCommonSetup);
    }

    static void afterCommonSetup()
    {
        System.out.println("WorldUpgrade afterCommonSetup now run.");
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        event.enqueueWork(WorldUpgrade::afterClientSetup);
    }

    static void afterClientSetup()
    {
        System.out.println("WorldUpgrade afterClientSetup now run.");
    }

    public static CreativeModeTab WORLDUPGRADE_CREATIVE_TAB;

    @SubscribeEvent
    private void registerTabs(CreativeModeTabEvent.Register event)
    {
        WORLDUPGRADE_CREATIVE_TAB = event.registerCreativeModeTab(new ResourceLocation(MOD_ID, "worldupgrade_tab"), builder -> builder
                .icon(() -> new ItemStack(ModBlocks.BIG_PLANT_LEAF.get()))
                .title(Component.translatable("tabs.modid.main_tab"))
                .displayItems((featureFlags, output, hasOp) -> {
                    output.accept(ModBlocks.BIG_PLANT_LEAF.get());
                    output.accept(Blocks.OAK_SAPLING, CreativeModeTab.TabVisibility.SEARCH_TAB_ONLY);
                })
        );
    }
}

