package ilja615.worldupgrade.proxy;

import ilja615.worldupgrade.blocks.BlockLeavesCarpet;
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
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.OAK_LEAVES_CARPET);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getBirch(), ModBlocks.BIRCH_LEAVES_CARPET);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getSpruce(), ModBlocks.SPRUCE_LEAVES_CARPET);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.JUNGLE_LEAVES_CARPET);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.ACACIA_LEAVES_CARPET);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.DARK_OAK_LEAVES_CARPET);

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.JUNGLE_ROCK_OVERGROWN);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_CLAY);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getGrassColor(reader, pos)
                : GrassColors.get(0.5D, 1.0D), ModBlocks.GRASSY_STONE);

        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.DRAGON_LEAVES);
        Minecraft.getInstance().getBlockColors().register((x, reader, pos, u)
                -> reader != null && pos != null ? BiomeColors.getFoliageColor(reader, pos)
                : FoliageColors.getDefault(), ModBlocks.DRAGON_LEAVES_CARPET);

        RenderTypeLookup.setRenderLayer(ModBlocks.DRAGON_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DEAD_LEAVES, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DRAGON_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DEAD_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.OAK_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BIRCH_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SPRUCE_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.JUNGLE_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.ACACIA_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DARK_OAK_LEAVES_CARPET, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.ALOE_VERA, RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.AGAVE, RenderType.getCutout());
    }
}
