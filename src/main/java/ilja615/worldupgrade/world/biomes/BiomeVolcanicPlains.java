package ilja615.worldupgrade.world.biomes;

import com.google.common.collect.Lists;
import ilja615.worldupgrade.init.ModBiomeFeatures;
import ilja615.worldupgrade.init.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BiomeVolcanicPlains extends Biome
{
    public BiomeVolcanicPlains()
    {
        super((new Biome.Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(ModBiomeFeatures.VOLCANIC_SURFACE_BUILDER, SurfaceBuilder.GRAVEL_CONFIG)).precipitation(RainType.NONE).category(Category.PLAINS).depth(0.08F).scale(0.0F).temperature(0.4F).downfall(0.4F).waterColor(4159204).waterFogColor(329011).parent((String)null));

//        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.FOREST_ROCK, new BlockBlobConfig(Blocks.OBSIDIAN.getDefaultState(), 0), Placement.FOREST_ROCK, new FrequencyConfig(2)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModBiomeFeatures.COURSE_DIRT_PATCH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.5F, 1)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(ModBiomeFeatures.SMOKE_VENT_FIRE_JET, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.3F, 1)));

        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.GRASS.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.DEAD_BUSH, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(128)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(Feature.GRASS, new GrassFeatureConfig(Blocks.FERN.getDefaultState()), Placement.COUNT_HEIGHTMAP_DOUBLE, new FrequencyConfig(1)));
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Biome.createDecoratedFeature(ModBiomeFeatures.DEAD_TREE, IFeatureConfig.NO_FEATURE_CONFIG, Placement.COUNT_EXTRA_HEIGHTMAP, new AtSurfaceWithExtraConfig(0, 0.4F, 1)));
        this.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Biome.createDecoratedFeature(Feature.LAKE, new LakesConfig(Blocks.LAVA.getDefaultState()), Placement.LAVA_LAKE, new LakeChanceConfig(20)));


        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addStoneVariants(this);
    }

    @Override
    public int getGrassColor(BlockPos pos)
    {
        return 0x90826C;
    }

    @Override
    public int getFoliageColor(BlockPos pos)
    {
        return 0x746B4E;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
        //return super.getSkyColorByTemp(currentTemperature);
        return 0xB68A86;
    }
}
