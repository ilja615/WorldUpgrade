package ilja615.worldupgrade.client;

import ilja615.worldupgrade.WorldUpgrade;
import ilja615.worldupgrade.world.biomes.BiomeCloudForrest;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WorldUpgrade.MOD_ID, value = Dist.CLIENT)
public class ClientEventsForMod
{
    @SubscribeEvent
    public static void onFogColor(EntityViewRenderEvent.FogColors event) {
        if (event.getInfo().getRenderViewEntity().world.getBiome(event.getInfo().getRenderViewEntity().getPosition()) instanceof BiomeCloudForrest)
        {
            event.setRed(0.92F);
            event.setGreen(0.95F);
            event.setBlue(0.95F);
        }
    }

    @SubscribeEvent
    public static void onFogDensity(EntityViewRenderEvent.FogDensity event) {
        if (event.getInfo().getRenderViewEntity().world.getBiome(event.getInfo().getRenderViewEntity().getPosition()) instanceof BiomeCloudForrest)
        {
            event.setDensity(0.05F);
            event.setCanceled(true);
        }
    }
}
