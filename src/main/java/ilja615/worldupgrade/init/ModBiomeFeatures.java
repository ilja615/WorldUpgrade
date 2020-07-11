package ilja615.worldupgrade.init;

import ilja615.worldupgrade.world.features.*;
import ilja615.worldupgrade.world.surfacebuilding.*;
import ilja615.worldupgrade.world.trees.DeadTreeFeature;
import ilja615.worldupgrade.world.trees.DragonTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;


public class ModBiomeFeatures
{
    public static final BlockClusterFeatureConfig FERN_CONFIG = (new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.FERN.getDefaultState()), new SimpleBlockPlacer())).tries(32).build();

    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC_SURFACE_BUILDER = new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> OVERGROWN_PEAKS_SURFACE_BUILDER = new OvergrownPeeksSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> DRAGON_DESSERT_SURFACE_BUILDER = new DragonDessertSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> POLDER_SUFACE_BUILDER = new PolderSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    public static final Feature<BaseTreeFeatureConfig> DEAD_TREE = register("dead_tree", new DeadTreeFeature(BaseTreeFeatureConfig::deserialize, false));
    public static final Feature<NoFeatureConfig> COURSE_DIRT_PATCH = register("course_dirt_patch", new CourseDirtPatchFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> SMOKE_VENT_FIRE_JET = register("smoke_vent_fire_jet", new VolcanicFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> AGAVE = register("agave", new AgaveFeature(NoFeatureConfig::deserialize));
    public static final Feature<BaseTreeFeatureConfig> DRAGON_TREE = register("dragon_tree", new DragonTreeFeature(BaseTreeFeatureConfig::deserialize, false));
    public static final Feature<NoFeatureConfig> DRY_REEDS = register("dry_reeds", new ReedsFeature(NoFeatureConfig::deserialize));
    public static final Feature<NoFeatureConfig> BRAMBLE = register("bramble", new BrambleFeature(NoFeatureConfig::deserialize));

    //public static final WorldCarver<ProbabilityConfig> TINY_RIVER = registerCarver("tiny_river", new PolderTinyRiverCarver(ProbabilityConfig::deserialize, 256));

    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, key, value));
    }
//    private static <C extends ICarverConfig, F extends WorldCarver<C>> F registerCarver(String key, F carver) {
//        return (F)(Registry.<WorldCarver<?>>register(Registry.CARVER, key, carver));
//    }
}
