package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.features.*;
import com.github.ilja615.worldupgrade.world.surfacebuilding.*;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBiomeFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<AgaveFeature> AGAVE = FEATURES.register("agave", () -> new AgaveFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<AloeVeraFeature> ALOE_VERA = FEATURES.register("aloe_vera", () -> new AloeVeraFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<BrambleFeature> BRAMBLE = FEATURES.register("bramble", () -> new BrambleFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<CharEmbersFeature> CHAR_EMBERS = FEATURES.register("char_embers", () -> new CharEmbersFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<ReedsFeature> REEDS = FEATURES.register("reeds", () -> new ReedsFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<TallDeadBushFeature> TALL_DEAD_BUSH = FEATURES.register("tall_dead_bush", () -> new TallDeadBushFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<VolcanicFeature> GEOTHERMAL_VENT = FEATURES.register("geothermal_vent", () -> new VolcanicFeature(NoFeatureConfig.field_236558_a_));
}
