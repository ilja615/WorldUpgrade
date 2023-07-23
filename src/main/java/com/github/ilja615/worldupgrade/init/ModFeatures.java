package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.feature.*;
import com.github.ilja615.worldupgrade.world.trunk_placers.CrookedTrunkPlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.HugeMushroomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES = DeferredRegister.create(Registries.TRUNK_PLACER_TYPE, WorldUpgrade.MOD_ID);

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
    public static final RegistryObject<HugeBogBeaconMushroomFeature> HUGE_BOG_BEACON_MUSHROOM = FEATURES.register("huge_bog_beacon_mushroom", () -> new
            HugeBogBeaconMushroomFeature(HugeMushroomFeatureConfiguration.CODEC));

    public static final RegistryObject<TrunkPlacerType> CROOKED_TRUNK_PLACER_TYPE = TRUNK_PLACER_TYPES.register("crooked_trunk_placer_type", () -> new
            TrunkPlacerType(CrookedTrunkPlacer.CODEC));
}
