package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.features.*;
import com.github.ilja615.worldupgrade.world.features.DragonTreeFeature;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<BrambleFeature> BRAMBLE = FEATURES.register("bramble", () -> new BrambleFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<ReedsFeature> REEDS = FEATURES.register("reeds", () -> new ReedsFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<TallDeadBushFeature> TALL_DEAD_BUSH = FEATURES.register("tall_dead_bush", () -> new TallDeadBushFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<GeothermalVentFeature> GEOTHERMAL_VENT = FEATURES.register("geothermal_vent", () -> new GeothermalVentFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<DragonTreeFeature> DRAGON_TREE = FEATURES.register("dragon_tree", () -> new DragonTreeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<DeadTreeFeature> DEAD_TREE = FEATURES.register("dead_tree", () -> new DeadTreeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<CharredTreeFeature> CHARRED_TREE = FEATURES.register("charred_tree", () -> new CharredTreeFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<TallCharredBushFeature> TALL_CHARRED_BUSH = FEATURES.register("tall_charred_bush", () -> new TallCharredBushFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<GunneraPlantFeature> GUNNERA_PLANT = FEATURES.register("gunnera_plant", () -> new GunneraPlantFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<BigFlowerFeature> BIG_FLOWER = FEATURES.register("big_flower", () -> new BigFlowerFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<RockFeature> ROCK1 = FEATURES.register("rock1", () -> new RockFeature(BlockStateFeatureConfig.CODEC));
    public static final RegistryObject<BigWaterLilyFeature> BIG_LILY = FEATURES.register("big_lily", () -> new BigWaterLilyFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<DryReedsFeature> DRY_REEDS = FEATURES.register("dry_reeds", () -> new DryReedsFeature(NoFeatureConfig.CODEC));
    public static final RegistryObject<RockWithDenseBuilderFeature> ROCK2 = FEATURES.register("rock2", () -> new RockWithDenseBuilderFeature(BlockStateFeatureConfig.CODEC));

    // configured feature
    public static final Lazy<ConfiguredFeature<?, ?>> CONFIGURED_BIG_FLOWER = Lazy.of(() -> Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(WorldUpgrade.MOD_ID, "big_flower"), BIG_FLOWER.get().configured(IFeatureConfig.NONE).squared().decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.06F, 3)))));
    public static final Lazy<ConfiguredFeature<?, ?>> CONFIGURED_BIG_LILY = Lazy.of(() -> Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(WorldUpgrade.MOD_ID, "big_lily"), BIG_LILY.get().configured(IFeatureConfig.NONE).squared().decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.4F, 2)))));

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class FeatureEvents
    {
        @SubscribeEvent
        public static void biomeModification(final BiomeLoadingEvent event)
        {
            if (RegistryKey.create(Registry.BIOME_REGISTRY, event.getName()).equals(Biomes.FLOWER_FOREST)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CONFIGURED_BIG_FLOWER.get());
            }
            if (RegistryKey.create(Registry.BIOME_REGISTRY, event.getName()).equals(Biomes.SWAMP)) {
                event.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(() -> CONFIGURED_BIG_LILY.get());
            }
        }
    }
}
