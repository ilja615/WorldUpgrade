package com.github.ilja615.worldupgrade.items;

import com.github.ilja615.worldupgrade.entities.SpoonBillEggEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

public class SpoonBillEggItem extends Item
{
    public SpoonBillEggItem(Properties p_i48466_1_) {
        super(p_i48466_1_);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        world.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), SoundEvents.ENTITY_EGG_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (random.nextFloat() * 0.4F + 0.8F));
        if (!world.isRemote)
        {
            SpoonBillEggEntity eggEntity = new SpoonBillEggEntity(world, player);
            eggEntity.setItem(stack);
            eggEntity.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0.0F, 1.5F, 1.0F);
            world.addEntity(eggEntity);
        }

        player.addStat(Stats.ITEM_USED.get(this));
        if (!player.abilities.isCreativeMode)
        {
            stack.shrink(1);
        }

        return ActionResult.func_233538_a_(stack, world.isRemote());
    }
}
