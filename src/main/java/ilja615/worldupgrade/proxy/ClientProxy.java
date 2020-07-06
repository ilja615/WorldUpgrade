package ilja615.worldupgrade.proxy;

import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.world.FoliageColors;
import net.minecraft.world.GrassColors;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeColors;

public class ClientProxy implements IProxy
    {
        @Override
        public World getClientWorld()
        {
            return Minecraft.getInstance().world;
        }

        @Override
        public void init()
        {
            //Block colors

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.JUNGLE_ROCK_OVERGROWN.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_CLAY.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_STONE.get());

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.DRAGON_LEAVES.get());

        // Item colors/*GrassColors.get(0.5D, 1.0D)*/

        Minecraft.getInstance().getItemColors().register((x, u)
                -> GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_STONE.get());
        Minecraft.getInstance().getItemColors().register((x, u)
                -> GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_CLAY.get());
        Minecraft.getInstance().getItemColors().register((x, u)
                -> GrassColors.get(0.5D, 1.0D), ModBlocks.JUNGLE_ROCK_OVERGROWN.get());

        Minecraft.getInstance().getItemColors().register((x, u)
                -> FoliageColors.getDefault(), ModBlocks.DRAGON_LEAVES.get());

            // Cutout s render layer;
            RenderTypeLookup.setRenderLayer(ModBlocks.DRAGON_LEAVES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.DEAD_LEAVES.get(), RenderType.getCutout());
            RenderTypeLookup.setRenderLayer(ModBlocks.ALOE_VERA.get(), RenderType.getCutout());
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
            RenderTypeLookup.setRenderLayer(ModBlocks.ALOE_FULL_BLOCK.get(), RenderType.getTranslucent());
        }
}
