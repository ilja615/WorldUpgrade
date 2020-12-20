package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.entities.BeaverEntity;
import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.github.ilja615.worldupgrade.entities.GribberEntity;
import com.github.ilja615.worldupgrade.entities.SpoonBillEntity;
import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;

public class ModEntities
{
    public static final EntityType<BubbleEelEntity> BUBBLE_EEL = register("worldupgrade:bubble_eel", EntityType.Builder.create(BubbleEelEntity::new, EntityClassification.WATER_CREATURE).size(0.4F, 0.4F));
    public static final EntityType<SpoonBillEntity> SPOONBILL = register("worldupgrade:spoonbill", EntityType.Builder.create(SpoonBillEntity::new, EntityClassification.CREATURE).size(0.8F, 1.5F));
    public static final EntityType<GribberEntity> GRIBBER = register("worldupgrade:gribber", EntityType.Builder.create(GribberEntity::new, EntityClassification.CREATURE).size(2.5F, 2.0F));
    public static final EntityType<BeaverEntity> BEAVER = register("worldupgrade:beaver", EntityType.Builder.create(BeaverEntity::new, EntityClassification.CREATURE).size(0.4F, 0.4F));
    public static final EntityType[] ENTITY_TYPES_FOR_REGISTRY = new EntityType[]{BUBBLE_EEL, SPOONBILL, GRIBBER, BEAVER};
    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                registerEntitySpawnEgg(BUBBLE_EEL, 0xffffff, 0x121212, "bubble_eel_egg"),
                registerEntitySpawnEgg(SPOONBILL, 0xff345f, 0xaa8112, "spoonbill_egg"),
                registerEntitySpawnEgg(GRIBBER, 0xaa08dd, 0x40ff12, "gribber_egg"),
                registerEntitySpawnEgg(BEAVER, 0xaf816b, 0x012345, "beaver_egg")
        );
    }

    public static void registerEntityWorldSpawns()
    {
        //registerEntityWorldSpawn(WEB_SPIDER, 1000, 2,4, Biomes.PLAINS);
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
    {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(WorldUpgradeItemGroup.INSTANCE));
        item.setRegistryName(name);
        return item;
    }

    public static void SetupEntityAttributes()
    {
        System.out.println("Setting up entity attributes for WorldUpgrade entities.");
        GlobalEntityTypeAttributes.put(BUBBLE_EEL, BubbleEelEntity.prepareAttributes().create());
        GlobalEntityTypeAttributes.put(SPOONBILL, SpoonBillEntity.prepareAttributes().create());
        GlobalEntityTypeAttributes.put(GRIBBER, GribberEntity.prepareAttributes().create());
        GlobalEntityTypeAttributes.put(BEAVER, BeaverEntity.prepareAttributes().create());
    }

//    public static void registerEntityWorldSpawn(EntityType<?> entity, int weight, int minGroupCountIn, int maxGroupCountIn, Biome... biomes)
//    {
//        for (Biome biome : biomes)
//        {
//            if (biome != null)
//            {
//                biome.getSpawns(entity.getClassification()).add(new Biome.SpawnListEntry(entity, weight, minGroupCountIn, maxGroupCountIn));
//            }
//        }
//    }

    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder)
    {
        return Registry.register(Registry.ENTITY_TYPE, key, builder.build(key));
    }
}
