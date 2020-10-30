package com.github.ilja615.worldupgrade.mixin;

import com.github.ilja615.worldupgrade.init.ModBlocks;
import com.github.ilja615.worldupgrade.init.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;

@Mixin(AnimalEntity.class)
public abstract class AnimalEntityMixin extends AgeableEntity
{
    @Inject(method = "canAnimalSpawn", at = @At("HEAD"), cancellable = true)
    private static void inject$canAnimalSpawn(EntityType<? extends AnimalEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue((worldIn.getBlockState(pos.down()).isIn(Blocks.GRASS_BLOCK) || SUPPORTS_ANIMAL_SPAWNS.contains(worldIn.getBlockState(pos.down()).getBlock())) && worldIn.getLightSubtracted(pos, 0) > 8);
    }

    private AnimalEntityMixin(EntityType<? extends AgeableEntity> entityType, World world)
    {
        super(entityType, world);
    }

    private static final HashSet<Block> SUPPORTS_ANIMAL_SPAWNS = new HashSet<Block>(Arrays.asList(ModBlocks.GRASSY_CLAY.get(), ModBlocks.GRASSY_STONE.get(), ModBlocks.JUNGLE_ROCK_OVERGROWN.get()));
}
