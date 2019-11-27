package ilja615.worldupgrade.init;

import ilja615.worldupgrade.world.features.CourseDirtPatchFeature;
import ilja615.worldupgrade.world.features.VolcanicFeature;
import ilja615.worldupgrade.world.surfacebuilding.OvergrownPeeksSurfaceBuilder;
import ilja615.worldupgrade.world.surfacebuilding.VolcanicSurfaceBuilder;
import ilja615.worldupgrade.world.trees.DeadTreeFeature;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class ModBiomeFeatures
{
    public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANIC_SURFACE_BUILDER = new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize);
    public static final SurfaceBuilder<SurfaceBuilderConfig> OVERGROWN_PEAKS_SURFACE_BUILDER = new OvergrownPeeksSurfaceBuilder(SurfaceBuilderConfig::deserialize);

    public static final Feature<NoFeatureConfig> DEAD_TREE = register("dead_tree", new DeadTreeFeature(NoFeatureConfig::deserialize, false, false));
    public static final Feature<NoFeatureConfig> COURSE_DIRT_PATCH = register("course_dirt_patch", new CourseDirtPatchFeature(NoFeatureConfig::deserialize, false));
    public static final Feature<NoFeatureConfig> SMOKE_VENT_FIRE_JET = register("smoke_vent_fire_jet", new VolcanicFeature(NoFeatureConfig::deserialize, false));


    private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value) {
        return (F)(Registry.<Feature<?>>register(Registry.FEATURE, key, value));
    }
}
