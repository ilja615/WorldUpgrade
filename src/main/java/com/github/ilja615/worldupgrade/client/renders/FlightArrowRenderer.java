package com.github.ilja615.worldupgrade.client.renders;

import com.github.ilja615.worldupgrade.entities.FlightArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FlightArrowRenderer extends ArrowRenderer<FlightArrowEntity>
{
    public FlightArrowRenderer(EntityRendererManager manager) {
        super(manager);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(FlightArrowEntity entity) {
        return new ResourceLocation("worldupgrade:textures/entity/flight_arrow.png");
    }
}