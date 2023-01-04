package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.feature.RandomPatchWithNeedsAdjacentFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<RandomPatchWithNeedsAdjacentFeature> RANDOM_PATCH_WITH_NEEDS_ADJACENT = FEATURES.register("random_patch_with_needs_adjacent", () -> new
            RandomPatchWithNeedsAdjacentFeature(RandomPatchConfiguration.CODEC));
}
