package ilja615.worldupgrade.proxy;

import ilja615.worldupgrade.init.ModBlocksNew;
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
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_OAK_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getBirch(), ModBlocksNew.FALLEN_BIRCH_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getSpruce(), ModBlocksNew.FALLEN_SPRUZE_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_JUNGLE_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_ACACIA_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_DARK_OAK_LEAVES.get());

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocksNew.JUNGLE_ROCK_OVERGROWN.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocksNew.GRASSY_CLAY.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocksNew.GRASSY_STONE.get());

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_DEAD_LEAVES.get());
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocksNew.FALLEN_DRAGON_LEAVES.get());

        RenderTypeLookup.setRenderLayer(ModBlocksNew.DRAGON_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.DEAD_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_DRAGON_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_DEAD_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_OAK_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_BIRCH_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_SPRUZE_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_JUNGLE_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_ACACIA_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.FALLEN_DARK_OAK_LEAVES.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.ALOE_VERA.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.AGAVE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.TALL_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.TOP_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.DRY_TALL_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.DRY_TOP_REED.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.GRASSY_CLAY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.GRASSY_STONE.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocksNew.JUNGLE_ROCK_OVERGROWN.get(), RenderType.getCutout());

    }
}
