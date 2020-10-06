package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.github.ilja615.worldupgrade.entities.SpoonBillEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, WorldUpgrade.MOD_ID);
    public static final RegistryObject<EntityType<BubbleEelEntity>> BUBBLE_EEL = register("bubble_eel", EntityType.Builder.create(BubbleEelEntity::new, EntityClassification.WATER_CREATURE)
            .size(0.4F, 0.4F));
    public static final RegistryObject<EntityType<SpoonBillEntity>> SPOONBILL = register("spoonbill", EntityType.Builder.create(SpoonBillEntity::new, EntityClassification.CREATURE)
            .size(0.8F, 1.5F));

    /*
    public static void registerEntityWorldSpawns()
    {
        registerEntityWorldSpawn(WEB_SPIDER, 1000, 2,4, Biomes.PLAINS);
    }

    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn, Biome... biomes)
    {
        for (Biome biome : biomes)
        {
            if (biome != null)
            {
                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
            }
        }
    }
    */

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String key, EntityType.Builder<T> builder)
    {
        return ENTITY_TYPES.register(key, () -> builder.build(WorldUpgrade.MOD_ID + ":" + key));
    }
}
