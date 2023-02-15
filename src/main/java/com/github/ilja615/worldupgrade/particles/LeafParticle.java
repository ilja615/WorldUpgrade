package com.github.ilja615.worldupgrade.particles;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class LeafParticle extends TextureSheetParticle
{
    private double anglularDirection;
    private double initialyd;
    private double initialxzd;

    protected LeafParticle(ClientLevel world, double x, double y, double z)
    {
        super(world, x, y, z);
        this.lifetime = 70;
        this.quadSize = 0.4f + level.random.nextFloat()*0.06f;
        this.anglularDirection = (random.nextDouble() + 0.5d) * 2*Math.PI;
        this.initialyd = 0.4d + level.random.nextDouble()*0.1d;
        this.initialxzd = 0.1d + level.random.nextDouble()*0.04d;

    }

    @Override
    public ParticleRenderType getRenderType()
    {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick()
    {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            if (this.age < 20)
            {
                this.yd = initialyd/(this.age + 1);
                this.xd = Math.cos(this.anglularDirection)*initialxzd;
                this.zd = Math.sin(this.anglularDirection)*initialxzd;
            } else {
                this.yd = Math.max(this.yd - 0.01d, -0.08d);
                this.xd = Math.cos(this.anglularDirection)*(Math.cos(this.age)*3*initialxzd + 0.05d);
                this.zd = Math.sin(this.anglularDirection)*(Math.cos(this.age)*3*initialxzd + 0.05d);
            }
            this.move(this.xd, this.yd, this.zd);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements ParticleProvider<SimpleParticleType>
    {
        private final SpriteSet sprite;

        public Factory(SpriteSet p_i232340_1_) {
            this.sprite = p_i232340_1_;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType p_199234_1_, ClientLevel p_199234_2_, double p_199234_3_, double p_199234_5_, double p_199234_7_, double p_199234_9_, double p_199234_11_, double p_199234_13_) {
            LeafParticle leafParticle = new LeafParticle(p_199234_2_, p_199234_3_, p_199234_5_, p_199234_7_);
            leafParticle.pickSprite(this.sprite);
            leafParticle.setColor(1.0F, 1.0F, 1.0F);
            return leafParticle;
        }
    }
}
