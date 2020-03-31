package ilja615.worldupgrade.init;

import ilja615.worldupgrade.entities.BabySpiderEntity;
import ilja615.worldupgrade.entities.WebSpiderEntity;
import ilja615.worldupgrade.util.ItemGroupWU;
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
    //public static EntityType<WebSpiderEntity> WEB_SPIDER = EntityType.Builder.create(WebSpiderEntity::new, EntityClassification.MONSTER).build(WorldUpgrade.MOD_ID + ":web_spider").setRegistryName("web_spider");
   // public static EntityType<BabySpiderEntity> BABY_SPIDER = EntityType.Builder.create(BabySpiderEntity::new, EntityClassification.MONSTER).build(WorldUpgrade.MOD_ID + ":baby_spider").setRegistryName("baby_spider");

    public static final EntityType<WebSpiderEntity> WEB_SPIDER = register("worldupgrade:web_spider", EntityType.Builder.create(WebSpiderEntity::new, EntityClassification.MONSTER).size(0.7F, 0.6F));
    public static final EntityType<BabySpiderEntity> BABY_SPIDER = register("worldupgrade:baby_spider", EntityType.Builder.create(BabySpiderEntity::new, EntityClassification.MONSTER).size(0.7F, 0.6F));


    public static void registerEntitySpawnEggs(final RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll
        (
            ModSpawnEggs.WEB_SPIDER_EGG = registerEntitySpawnEgg(WEB_SPIDER, 0xf2f0f0, 0x5e565e, "web_spider_egg"),
            ModSpawnEggs.BABY_SPIDER_EGG = registerEntitySpawnEgg(BABY_SPIDER, 0xffffff, 0x121212, "baby_spider_egg")

        );
    }

    public static void registerEntityWorldSpawns()
    {
        //registerEntityWorldSpawn(WEB_SPIDER, 1000, 2,4, Biomes.PLAINS);
    }

    public static Item registerEntitySpawnEgg(EntityType<?> type, int color1, int color2, String name)
    {
        SpawnEggItem item = new SpawnEggItem(type, color1, color2, new Item.Properties().group(ItemGroupWU.instance));
        item.setRegistryName(name);
        return item;
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

    private static <T extends Entity> EntityType<T> register(String key, EntityType.Builder<T> builder) {
        return Registry.register(Registry.ENTITY_TYPE, key, builder.build(key));
    }
}
