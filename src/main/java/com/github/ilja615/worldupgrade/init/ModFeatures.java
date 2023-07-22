package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.feature.*;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<RandomPatchWithNeedsAdjacentFeature> RANDOM_PATCH_WITH_NEEDS_ADJACENT = FEATURES.register("random_patch_with_needs_adjacent", () -> new
            RandomPatchWithNeedsAdjacentFeature(RandomPatchConfiguration.CODEC));
    public static final RegistryObject<GunneraFeature> GUNNERA = FEATURES.register("gunnera", () -> new
            GunneraFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<BigLilyPadFeature> BIG_LILY_PAD = FEATURES.register("big_lily_pad", () -> new
            BigLilyPadFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<ReedsFeature> REEDS = FEATURES.register("reeds", () -> new
            ReedsFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<DryReedsFeature> DRY_REEDS = FEATURES.register("dry_reeds", () -> new
            DryReedsFeature(NoneFeatureConfiguration.CODEC));
}
