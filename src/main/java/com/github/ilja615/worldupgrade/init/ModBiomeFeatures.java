package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.features.*;
import com.github.ilja615.worldupgrade.world.features.trees.DeadTreeFeature;
import com.github.ilja615.worldupgrade.world.features.trees.DragonTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModBiomeFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);
    public static final BlockClusterFeatureConfig FERN_CONFIG = new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.FERN.getDefaultState()), new SimpleBlockPlacer()).tries(32).build();

    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> DEAD_TREE = FEATURES.register("dead_tree", () -> new DeadTreeFeature(BaseTreeFeatureConfig::deserialize/*, false*/));
    public static final RegistryObject<Feature<NoFeatureConfig>> CHAR_EMBER = FEATURES.register("char_embers", () -> new CharEmbersFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> TALL_DEADBUSH = FEATURES.register("tall_deadbush", () -> new TallDeadBushFeature(NoFeatureConfig::deserialize));

    public static final RegistryObject<Feature<NoFeatureConfig>> SMOKE_VENT_FIRE_JET = FEATURES.register("smoke_vent_fire_jet", () -> new VolcanicFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> AGAVE = FEATURES.register("agave", () -> new AgaveFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<BaseTreeFeatureConfig>> DRAGON_TREE = FEATURES.register("dragon_tree", () -> new DragonTreeFeature(BaseTreeFeatureConfig::deserialize, false));
    public static final RegistryObject<Feature<NoFeatureConfig>> DRY_REEDS = FEATURES.register("dry_reeds", () -> new ReedsFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> BRAMBLE = FEATURES.register("bramble", () -> new BrambleFeature(NoFeatureConfig::deserialize));
    public static final RegistryObject<Feature<NoFeatureConfig>> ALOE_VERA = FEATURES.register("aloe_vera", () -> new AloeVeraFeature(NoFeatureConfig::deserialize));
}
