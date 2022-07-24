package com.sammy.malum.common.entity.nitrate;

import com.sammy.malum.core.helper.SpiritHelper;
import com.sammy.malum.core.setup.content.SoundRegistry;
import com.sammy.malum.core.setup.content.entity.EntityRegistry;
import com.sammy.ortus.helpers.ColorHelper;
import com.sammy.ortus.setup.OrtusParticleRegistry;
import com.sammy.ortus.systems.easing.Easing;
import com.sammy.ortus.systems.rendering.particle.ParticleBuilders;
import com.sammy.ortus.systems.rendering.particle.ParticleRenderTypes;
import com.sammy.ortus.systems.rendering.particle.SimpleParticleOptions;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static net.minecraft.util.Mth.nextFloat;

public class VividNitrateEntity extends AbstractNitrateEntity {

    public static final ArrayList<Color> COLORS = new ArrayList<>(List.of(
            new Color(231, 58, 73),
            new Color(246, 125, 70),
            new Color(249, 206, 77),
            new Color(48, 242, 71),
            new Color(48, 208, 242),
            new Color(59, 48, 242),
            new Color(145, 42, 247),
            new Color(231, 58, 73)));
    //PRIDE!!! :)

    public VividNitrateEntity(Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), level);
    }

    public VividNitrateEntity(double x, double y, double z, Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), x, y, z, level);
    }

    public VividNitrateEntity(LivingEntity owner, Level level) {
        super(EntityRegistry.VIVID_NITRATE.get(), owner, level);
    }

    @Override
    public float getExplosionRadius() {
        return 3f;
    }

    @Override
    public int getPierce() {
        return 20;
    }

    @Override
    public void onExplode() {
        Vec3 deltaMovement = getDeltaMovement();
        Random random = level.random;
        double x = Mth.lerp(0.5f, deltaMovement.x, Math.min(2, (0.9f + random.nextFloat() * 0.3f) * (random.nextBoolean() ? -deltaMovement.x : deltaMovement.x) + 0.45f - random.nextFloat() * 0.9f));
        double y = Mth.lerp(0.5f, deltaMovement.y, Math.min(2, (deltaMovement.y * (0.7f + random.nextFloat() * 0.3f) + 0.2f + random.nextFloat() * 0.4f)));
        double z = Mth.lerp(0.5f, deltaMovement.z, Math.min(2, (0.9f + random.nextFloat() * 0.3f) * (random.nextBoolean() ? -deltaMovement.z : deltaMovement.z) + 0.45f - random.nextFloat() * 0.9f));
        if (random.nextFloat() < 0.2f) {
            x *= 1.5f+random.nextFloat()*0.5f;
            y += 0.25f+random.nextFloat()*0.5f;
            z *= 1.5f+random.nextFloat()*0.5f;
        }
        y = Math.min(y, 1f);
        setDeltaMovement(x, y, z);
    }

    @Override
    public void spawnParticles() {
        float time = ((level.getGameTime()) % 24f) / 24f;
        Function<Float, Color> colorFunction = f -> {
            float lerp = time + f;
            if (lerp > 1) {
                lerp -= Math.floor(lerp);
            }
            return ColorHelper.multicolorLerp(Easing.SINE_IN, lerp, VividNitrateEntity.COLORS);
        };

        double x = getX(), y = getY() + getYOffset(0) + 0.25f, z = getZ();
        Vec3 motion = getDeltaMovement();
        Vec3 norm = motion.normalize().scale(0.1f);
        float extraAlpha = (float) motion.length();
        float cycles = 4;
        Color firstColor = colorFunction.apply(time).brighter();
        Color secondColor = colorFunction.apply(time+0.125f).darker();
        Random rand = level.getRandom();
        for (int i = 0; i < cycles; i++) {
            float pDelta = i / cycles;
            double lerpX = Mth.lerp(pDelta, x - motion.x, x) + norm.x * pDelta;
            double lerpY = Mth.lerp(pDelta, y - motion.y, y) + norm.y * pDelta;
            double lerpZ = Mth.lerp(pDelta, z - motion.z, z) + norm.z * pDelta;
            float alphaMultiplier = (0.20f + extraAlpha) * Math.min(1, windUp * 2);
            SpiritHelper.spawnSpiritParticles(level, lerpX, lerpY, lerpZ, alphaMultiplier, norm, firstColor, secondColor);

            ParticleBuilders.create(OrtusParticleRegistry.SMOKE_PARTICLE)
                    .setAlpha(0.3f, 0.7f * alphaMultiplier, 0f)
                    .setAlphaEasing(Easing.SINE_IN, Easing.SINE_OUT)
                    .setLifetime(65 + rand.nextInt(15))
                    .setSpin(nextFloat(rand, -0.1f, 0.1f))
                    .setSpinOffset(rand.nextFloat() * 6.28f)
                    .setScale(0.2f + rand.nextFloat() * 0.075f, 0f)
                    .setColor(secondColor, Color.DARK_GRAY)
                    .setColorEasing(Easing.SINE_OUT)
                    .setColorCoefficient(1.5f)
                    .randomOffset(0.02f)
                    .enableNoClip()
                    .randomMotion(0.01f, 0.01f)
                    .overwriteRenderType(ParticleRenderTypes.TRANSPARENT)
                    .overwriteRemovalProtocol(SimpleParticleOptions.SpecialRemovalProtocol.INVISIBLE)
                    .repeat(level, lerpX, lerpY, lerpZ, 1);
        }
    }
}