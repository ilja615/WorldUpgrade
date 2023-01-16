package com.github.ilja615.worldupgrade.init;

import com.github.ilja615.worldupgrade.WorldUpgrade;
import com.github.ilja615.worldupgrade.world.surfaceruledata.BogSurfaceRuleData;
import terrablender.api.SurfaceRuleManager;

public class ModSurfaceRules
{
    public static void makeRules() {
        SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, WorldUpgrade.MOD_ID, BogSurfaceRuleData.makeRules());
    }
}
