package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.entities.*;
import com.github.ilja615.worldupgrade.util.WorldUpgradeItemGroup;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, WorldUpgrade.MOD_ID);

    public static final EntityType<BubbleEelEntity> TYPE_BUBBLE_EEL = EntityType.Builder.of(BubbleEelEntity::new, EntityClassification.WATER_CREATURE).sized(0.4F, 0.4F).build("worldupgrade:bubble_eel");
    public static final EntityType<SpoonBillEntity> TYPE_SPOONBILL = EntityType.Builder.of(SpoonBillEntity::new, EntityClassification.CREATURE).sized(0.8F, 1.5F).build("worldupgrade:spoonbill");
    public static final EntityType<GribberEntity> TYPE_GRIBBER = EntityType.Builder.of(GribberEntity::new, EntityClassification.CREATURE).sized(2.5F, 2.0F).build("worldupgrade:gribber");
    public static final EntityType<BeaverEntity> TYPE_BEAVER = EntityType.Builder.of(BeaverEntity::new, EntityClassification.CREATURE).sized(0.4F, 0.4F).build("worldupgrade:beaver");
    public static final EntityType<SpoonBillEggEntity> TYPE_SPOONBILL_EGG = EntityType.Builder.<SpoonBillEggEntity>of(SpoonBillEggEntity::new, EntityClassification.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10).build("worldupgrade:spoonbill_egg");
    public static final EntityType<FlightArrowEntity> TYPE_FLIGHT_ARROW = EntityType.Builder.<FlightArrowEntity>of(FlightArrowEntity::new, EntityClassification.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("worldupgrade:flight_arrow");

    public static final RegistryObject<EntityType<BubbleEelEntity>> BUBBLE_EEL = ENTITIES.register("bubble_eel", () -> TYPE_BUBBLE_EEL);
    public static final RegistryObject<EntityType<SpoonBillEntity>> SPOONBILL = ENTITIES.register("spoonbill", () -> TYPE_SPOONBILL);
    public static final RegistryObject<EntityType<GribberEntity>> GRIBBER = ENTITIES.register("gribber", () -> TYPE_GRIBBER);
    public static final RegistryObject<EntityType<BeaverEntity>> BEAVER = ENTITIES.register("beaver", () -> TYPE_BEAVER);
    public static final RegistryObject<EntityType<SpoonBillEggEntity>> SPOONBILL_EGG = ENTITIES.register("spoonbill_egg", () -> TYPE_SPOONBILL_EGG);
    public static final RegistryObject<EntityType<FlightArrowEntity>> FLIGHT_ARROW = ENTITIES.register("flight_arrow", () -> TYPE_FLIGHT_ARROW);


    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(
                registerEntitySpawnEgg(TYPE_BUBBLE_EEL, 0xffffff, 0x121212, "bubble_eel_spawn_egg"),
                registerEntitySpawnEgg(TYPE_SPOONBILL, 0xff345f, 0xaa8112, "spoonbill_spawn_egg"),
                registerEntitySpawnEgg(TYPE_GRIBBER, 0xaa08dd, 0x40ff12, "gribber_spawn_egg"),
                registerEntitySpawnEgg(TYPE_BEAVER, 0xaf816b, 0x012345, "beaver_spawn_egg")
        );
    }

    public static void registerEntityWorldSpawns()
    {
        //registerEntityWorldSpawn(WEB_SPIDER, 1000, 2,4, Biomes.PLAINS);
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
    {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().tab(WorldUpgradeItemGroup.INSTANCE));
        item.setRegistryName(name);
        return item;
    }

    public static void SetupEntityAttributes()
    {
        System.out.println("Setting up entity attributes for WorldUpgrade entities.");
        GlobalEntityTypeAttributes.put(BUBBLE_EEL.get(), BubbleEelEntity.prepareAttributes().build());
        GlobalEntityTypeAttributes.put(SPOONBILL.get(), SpoonBillEntity.prepareAttributes().build());
        GlobalEntityTypeAttributes.put(GRIBBER.get(), GribberEntity.prepareAttributes().build());
        GlobalEntityTypeAttributes.put(BEAVER.get(), BeaverEntity.prepareAttributes().build());
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
}
