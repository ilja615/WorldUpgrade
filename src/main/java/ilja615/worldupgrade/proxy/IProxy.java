package ilja615.worldupgrade.proxy;

import net.minecraft.world.World;

public interface IProxy
{
    World getClientWorld();

    public void init();
}
