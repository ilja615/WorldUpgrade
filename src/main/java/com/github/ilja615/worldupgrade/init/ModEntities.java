package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.entity.Disguisager;
import com.github.ilja615.worldupgrade.entity.Sloth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities
{
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, WorldUpgrade.MOD_ID);

    public static final RegistryObject<EntityType<Disguisager>> DISGUISAGER = ENTITY_TYPES.register("disguisager",
            () -> EntityType.Builder.of(Disguisager::new, MobCategory.MISC).sized(0.45F, 2.0F).build("worldupgrade:disguisager"));
    public static final RegistryObject<EntityType<Sloth>> SLOTH = ENTITY_TYPES.register("sloth",
            () -> EntityType.Builder.of(Sloth::new, MobCategory.MISC).sized(1.0F, 1.0F).build("worldupgrade:sloth"));

    // The event is passed in via main class
    public static void createEntityAttributes(final EntityAttributeCreationEvent event)
    {
        event.put(ModEntities.DISGUISAGER.get(), Disguisager.createAttributes().build());
        event.put(ModEntities.SLOTH.get(), Sloth.createAttributes().build());
    }
}
