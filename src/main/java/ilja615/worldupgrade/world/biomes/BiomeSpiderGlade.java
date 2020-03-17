package ilja615.worldupgrade.world.biomes;

import ilja615.worldupgrade.init.ModBiomeFeatures;
import ilja615.worldupgrade.init.ModEntities;
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
        super((new Builder()).surfaceBuilder(new ConfiguredSurfaceBuilder(ModBiomeFeatures.SPIDERGLADE_SURFACE_BUILDER, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)).precipitation(RainType.RAIN).category(Category.PLAINS).depth(0.01F).scale(0.3F).temperature(0.25F).downfall(0.6F).waterColor(4159204).waterFogColor(11649740).parent((String)null));

        this.addStructure(Feature.MINESHAFT.withConfiguration(new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL)));
        this.addStructure(Feature.STRONGHOLD.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG));

        DefaultBiomeFeatures.addOres(this);
        DefaultBiomeFeatures.addStoneVariants(this);
        DefaultBiomeFeatures.addMushrooms(this);
        DefaultBiomeFeatures.addTaigaLargeFerns(this);
        this.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.NORMAL_TREE.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG).withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(3, 0.1F, 1))));

        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 200, 1, 3));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CAVE_SPIDER, 50, 2, 4));
        this.addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(ModEntities.BABY_SPIDER, 150, 3, 6));
    }

    @Override
    public int getGrassColor(double p_225528_1_, double p_225528_3_) {
        return 0xECECEC;
    }

    @Override
    public int getFoliageColor() {
        return 0xDDDDDD;
    }

    @Override
    public int getSkyColor() {
        return 0x434343;
    }
}
