package com.github.ilja615.worldupgrade.util;

import com.mojang.datafixers.util.Pair;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.potion.Potions;

public class FoodChecker
{
    public static boolean isPoisonous(Item item)
    {
        boolean r = false;
        for (Pair<EffectInstance, Float> effectInstanceFloatPair : item.getFoodProperties().getEffects())
        {
            if (effectInstanceFloatPair.getFirst().getEffect() == Effects.POISON) {
                r = true;
            }
        }
        return r;
    }
}
