package com.github.ilja615.worldupgrade.capability.fog;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class PlayerFog
{
    private int fog;
    private final int MAX_FOG = 100;
    private final int MIN_FOG = 0;

    public int getFog()
    {
        return fog;
    }

    public void addFog(int amount)
    {
        this.fog = Math.min(fog + amount, MAX_FOG);
    }

    public void substractFog(int amount)
    {
        this.fog = Math.max(fog - amount, MAX_FOG);
    }

    public void copyFrom(PlayerFog source)
    {
        this.fog = source.fog;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("fog", fog);
    }

    public void loadNBTData(CompoundTag nbt) {
        fog = nbt.getInt("fog");
    }
}
