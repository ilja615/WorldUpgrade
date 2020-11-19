package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.features.*;
import com.github.ilja615.worldupgrade.world.features.DragonTreeFeature;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<BrambleFeature> BRAMBLE = FEATURES.register("bramble", () -> new BrambleFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<ReedsFeature> REEDS = FEATURES.register("reeds", () -> new ReedsFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<TallDeadBushFeature> TALL_DEAD_BUSH = FEATURES.register("tall_dead_bush", () -> new TallDeadBushFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<GeothermalVentFeature> GEOTHERMAL_VENT = FEATURES.register("geothermal_vent", () -> new GeothermalVentFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<DragonTreeFeature> DRAGON_TREE = FEATURES.register("dragon_tree", () -> new DragonTreeFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<DeadTreeFeature> DEAD_TREE = FEATURES.register("dead_tree", () -> new DeadTreeFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<CharredTreeFeature> CHARRED_TREE = FEATURES.register("charred_tree", () -> new CharredTreeFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<TallCharredBushFeature> TALL_CHARRED_BUSH = FEATURES.register("tall_charred_bush", () -> new TallCharredBushFeature(NoFeatureConfig.field_236558_a_));
    public static final RegistryObject<GunneraPlantFeature> GUNNERA_PLANT = FEATURES.register("gunnera_plant", () -> new GunneraPlantFeature(NoFeatureConfig.field_236558_a_));

}
