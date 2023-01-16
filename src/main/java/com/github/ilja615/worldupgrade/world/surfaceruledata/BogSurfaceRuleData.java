package com.github.ilja615.worldupgrade.world.surfaceruledata;

import com.github.ilja615.worldupgrade.init.ModBiomes;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraftforge.fml.common.Mod;

public class BogSurfaceRuleData
{
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource MUD = makeStateRule(Blocks.MUD);
    private static final SurfaceRules.RuleSource WATER = makeStateRule(Blocks.WATER);

    public static SurfaceRules.RuleSource makeRules()
    {
        SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        SurfaceRules.ConditionSource mud1 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.9D, -0.7D);
        SurfaceRules.ConditionSource mud2 = SurfaceRules.noiseCondition(Noises.SURFACE, -0.5D, -0.1D);
        SurfaceRules.ConditionSource mud3 = SurfaceRules.noiseCondition(Noises.SURFACE, 0.1D, 0.15D);

        SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);
        SurfaceRules.ConditionSource atY62 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(62), 0);
        SurfaceRules.ConditionSource atY63 = SurfaceRules.yBlockCheck(VerticalAnchor.absolute(63), 0);

        return SurfaceRules.sequence(
            SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BOG),
                SurfaceRules.ifTrue(atY62,
                    SurfaceRules.ifTrue(SurfaceRules.not(atY63),
                            SurfaceRules.ifTrue(SurfaceRules.noiseCondition(Noises.SWAMP, 0.0D), WATER)
                    )
                )
            ),
            SurfaceRules.ifTrue(mud1, SurfaceRules.sequence(
                SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BOG), MUD),
                // Default to a grass and dirt surface
                SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
            )),
            SurfaceRules.ifTrue(mud2, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BOG), MUD),
                    // Default to a grass and dirt surface
                    SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
            )),
            SurfaceRules.ifTrue(mud3, SurfaceRules.sequence(
                    SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.BOG), MUD),
                    // Default to a grass and dirt surface
                    SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, grassSurface)
            ))
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block)
    {
        return SurfaceRules.state(block.defaultBlockState());
    }
}
