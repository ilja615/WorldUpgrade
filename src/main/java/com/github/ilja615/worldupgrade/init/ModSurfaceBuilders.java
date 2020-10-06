package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.surfacebuilding.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSurfaceBuilders
{
    public static final DeferredRegister<SurfaceBuilder<?>> SURFACE_BUILDERS = DeferredRegister.create(ForgeRegistries.SURFACE_BUILDERS, WorldUpgrade.MOD_ID);

    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> VOLCANIC_SURFACE_BUILDER = SURFACE_BUILDERS.register("volcanic", () -> new VolcanicSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> OVERGROWN_PEAKS_SURFACE_BUILDER = SURFACE_BUILDERS.register("overgrown_peaks", () -> new OvergrownPeaksSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DRAGON_DESERT_SURFACE_BUILDER = SURFACE_BUILDERS.register("dragon_desert", () -> new DragonDesertSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> POLDER_SURFACE_BUILDER = SURFACE_BUILDERS.register("polder", () -> new PolderSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> CHARRED_FOREST_SURFACE_BUILDER = SURFACE_BUILDERS.register("charred_forest", () -> new CharredForestSurfaceBuilder(SurfaceBuilderConfig::deserialize));
    public static final RegistryObject<SurfaceBuilder<SurfaceBuilderConfig>> DRAGON_TREE_FOREST_SURFACE_BUILDER = SURFACE_BUILDERS.register("dragon_tree_forest", () -> new DragonTreeForestSurfaceBuilder(SurfaceBuilderConfig::deserialize));
}
