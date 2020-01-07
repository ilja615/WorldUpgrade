package ilja615.worldupgrade.world.biomes;

import ilja615.worldupgrade.init.ModBiomeFeatures;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.MultipleRandomFeatureConfig;
import net.minecraft.world.gen.feature.SeaGrassConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BiomeSpiderGlade extends Biome
{
    public BiomeSpiderGlade()
    {
        super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.02F).scale(0.06F).temperature(0.25F).downfall(0.6F).waterColor(4159204).waterFogColor(11649740).parent((String)null));

        this.addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
        this.addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);

        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);

        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 200, 1, 3));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CAVE_SPIDER, 50, 2, 6));

    }

    @Override
    public int getGrassColor(BlockPos pos)
    {
        return 0xE5E5E5;
    }

    @Override
    public int getFoliageColor(BlockPos pos)
    {
        return 0xC5C5C5;
    }

    @Override
    public int getSkyColorByTemp(float currentTemperature)
    {
        //return super.getSkyColorByTemp(currentTemperature);
        return 0x434343;
    }
}
