package com.kittykitcatcat.malum.particles.soulflameparticle;

import net.minecraft.client.particle.*;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class SoulFlameParticle extends SimpleAnimatedParticle
{

    private final IAnimatedSprite spriteSet;
    public float scale;
    protected SoulFlameParticle(World world, double xSpeed, double ySpeed, double zSpeed, double x, double y, double z, IAnimatedSprite spriteSet)
    {
        super(world, xSpeed, ySpeed, zSpeed, spriteSet, 0);
        this.spriteSet = spriteSet;
        motionX = xSpeed;
        motionY = ySpeed;
        motionZ = zSpeed;
        setPosition(x, y, z);
        setMaxAge(80);
    }

    @Override
    public void tick()
    {
        super.tick();
        selectSpriteWithAge(spriteSet);
        motionX *= 0.9f;
        motionX *= 0.9f;
        motionZ *= 0.9f;
        if (age < 10)
        {
            if (scale < 0.25f)
            {
                scale += 0.025f;
            }
        }
        if (age > 70)
        {
            if (scale > 0f)
            {
                scale -= 0.025f;
            }
        }
    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public float getScale(float p_217561_1_)
    {
        return scale;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<SoulFlameParticleData>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(SoulFlameParticleData data, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            SoulFlameParticle particle = new SoulFlameParticle(worldIn, xSpeed, ySpeed, zSpeed, x,y,z, spriteSet);
            particle.selectSpriteRandomly(this.spriteSet);
            if (xSpeed > 2f || xSpeed < -2f || ySpeed > 2f || ySpeed < -2f || zSpeed > 2f || zSpeed < -2f)
            {
                particle.setExpired();
            }
            return particle;
        }
    }
}