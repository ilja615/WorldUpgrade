package com.github.ilja615.worldupgrade.client;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.biome.BiomeColors;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber
{
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event)
    {
        DeferredWorkQueue.runLater(() ->
        {
            //block color
            Minecraft.getInstance().getBlockColors().register(
                    (x, reader, pos, u) -> reader != null && pos != null
                            ? BiomeColors.getGrassColor(reader, pos)
                            : GrassColors.get(0.5D, 1.0D),
                    ModBlocks.JUNGLE_ROCK_OVERGROWN.get(),
                    ModBlocks.GRASSY_CLAY.get(),
                    ModBlocks.GRASSY_STONE.get()
            );
//            Minecraft.getInstance().getBlockColors().register((x, reader, pos, u) -> reader != null && pos != null
//                            ? BiomeColors.getFoliageColor(reader, pos)
//                            : FoliageColors.getDefault(),
//                    ModBlocks.DRAGON_LEAVES.get());
        });
        //render layers
        RenderTypeLookup.setRenderLayer(ModBlocks.DRAGON_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CHARRED_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.AGAVE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TOP_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DRY_TALL_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DRY_TOP_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GRASSY_CLAY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GRASSY_STONE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.JUNGLE_ROCK_OVERGROWN.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BRAMBLE_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BRAMBLE_FULL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.FALLEN_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.AUTUMN_FALLEN_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.ALOE_VERY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MOSS.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_DEAD_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CHARRED_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.TALL_CHARRED_BUSH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.ASH_DIRT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.AUTUMN_LEAVES_PILE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.LEAVES_PILE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GUNNERA_TALLFLOWER.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CHAMAENERION.get(), RenderType.getCutout());

    }
}