package com.github.ilja615.worldupgrade.capability.fog;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerFogProvider implements ICapabilityProvider, INBTSerializable<CompoundTag>
{
    public static Capability<PlayerFog> PLAYER_FOG = CapabilityManager.get(new CapabilityToken<PlayerFog>() {});
    private PlayerFog fog = null;
    private final LazyOptional<PlayerFog> optional = LazyOptional.of(this::createPlayerFog);

    private PlayerFog createPlayerFog()
    {
        if(this.fog == null)
        {
            this.fog = new PlayerFog();
        }
        return this.fog;
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == PLAYER_FOG)
        {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerFog().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerFog().loadNBTData(nbt);
    }
}
