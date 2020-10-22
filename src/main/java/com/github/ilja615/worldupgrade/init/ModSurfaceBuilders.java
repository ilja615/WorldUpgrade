package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.surfacebuilding.*;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSurfaceBuilders
{
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<CharredForestSurfaceBuilder> CHARRED_FOREST = SURFACE_BUILDERS.register("charred_forest", () -> new CharredForestSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<DragonDesertSurfaceBuilder> DRAGON_DESERT = SURFACE_BUILDERS.register("dragon_desert", () -> new DragonDesertSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<DragonTreeForestSurfaceBuilder> DRAGON_TREE_FOREST = SURFACE_BUILDERS.register("dragon_tree_forest", () -> new DragonTreeForestSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<OvergrownPeaksSurfaceBuilder> OVERGROWN_PEAKS = SURFACE_BUILDERS.register("overgrown_peaks", () -> new OvergrownPeaksSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<PolderSurfaceBuilder> POLDER = SURFACE_BUILDERS.register("polder", () -> new PolderSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
    public static final RegistryObject<VolcanicSurfaceBuilder> VOLCANIC = SURFACE_BUILDERS.register("volcanic", () -> new VolcanicSurfaceBuilder(SurfaceBuilderConfig.field_237203_a_));
}
