package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.entities.BubbleEelEntity;
import com.github.ilja615.worldupgrade.entities.SpoonBillEntity;
import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.RegistryEvent;

public class ModEntities
{
    public static final EntityType<BubbleEelEntity> BUBBLE_EEL = register("worldupgrade:bubble_eel", EntityType.Builder.create(BubbleEelEntity::new, EntityClassification.WATER_CREATURE).size(0.4F, 0.4F));
    public static final EntityType<SpoonBillEntity> SPOONBILL = register("worldupgrade:spoonbill", EntityType.Builder.create(SpoonBillEntity::new, EntityClassification.CREATURE).size(0.8F, 1.5F));

    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                /*ModSpawnEggs.BUBBLE_EEL_EGG = */registerEntitySpawnEgg(BUBBLE_EEL, 0xffffff, 0x121212, "bubble_eel_egg"),
                /*ModSpawnEggs.SPOONBILL_EGG = */registerEntitySpawnEgg(SPOONBILL, 0xff345f, 0xaa8112, "spoonbill_egg")
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
