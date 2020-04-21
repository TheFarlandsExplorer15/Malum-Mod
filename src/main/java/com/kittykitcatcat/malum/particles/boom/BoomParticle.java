package com.kittykitcatcat.malum.particles.boom;

import net.minecraft.client.particle.*;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class BoomParticle extends SimpleAnimatedParticle
{

    private final IAnimatedSprite spriteSet;
    protected BoomParticle(World world, double xSpeed, double ySpeed, double zSpeed, double x, double y, double z, IAnimatedSprite spriteSet)
    {
        super(world, xSpeed, ySpeed, zSpeed, spriteSet, 0);
        this.spriteSet = spriteSet;
        motionX = xSpeed;
        motionY = ySpeed;
        motionZ = zSpeed;
        setPosition(x, y, z);
        setMaxAge(20);
    }

    @Override
    public void tick()
    {
        super.tick();
        selectSpriteWithAge(spriteSet);
    }

    @Override
    public float getScale(float p_217561_1_)
    {
        return 1f;
    }

    @Override
    public IParticleRenderType getRenderType()
    {
        return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }


    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BoomParticleData>
    {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet)
        {
            this.spriteSet = spriteSet;
        }

        public Particle makeParticle(BoomParticleData data, World worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed)
        {
            BoomParticle particle = new BoomParticle(worldIn, xSpeed, ySpeed, zSpeed, x,y,z, spriteSet);
            particle.selectSpriteRandomly(this.spriteSet);
            if (xSpeed > 2f || xSpeed < -2f || ySpeed > 2f || ySpeed < -2f || zSpeed > 2f || zSpeed < -2f)
            {
                particle.setExpired();
            }
            return particle;
        }
    }
}