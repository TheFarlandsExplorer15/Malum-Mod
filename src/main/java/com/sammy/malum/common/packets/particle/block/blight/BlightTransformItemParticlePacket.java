package com.sammy.malum.common.packets.particle.block.blight;

import com.sammy.malum.common.packets.particle.SpiritBasedParticleEffectPacket;
import com.sammy.malum.core.helper.SpiritHelper;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import team.lodestar.lodestone.helpers.ColorHelper;
import team.lodestar.lodestone.setup.LodestoneParticleRegistry;
import team.lodestar.lodestone.systems.easing.Easing;
import team.lodestar.lodestone.systems.particle.SimpleParticleOptions;
import team.lodestar.lodestone.systems.particle.WorldParticleBuilder;
import team.lodestar.lodestone.systems.particle.data.ColorParticleData;
import team.lodestar.lodestone.systems.particle.data.GenericParticleData;
import team.lodestar.lodestone.systems.particle.data.SpinParticleData;
import team.lodestar.lodestone.systems.particle.world.LodestoneWorldParticleRenderType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class BlightTransformItemParticlePacket extends SpiritBasedParticleEffectPacket {
    public BlightTransformItemParticlePacket(List<String> spirits, Vec3 vec3) {
        super(spirits, vec3);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void execute(Supplier<NetworkEvent.Context> context) {
        Level level = Minecraft.getInstance().level;
        Random rand = level.random;
        List<MalumSpiritType> types = new ArrayList<>();
        for (String string : spirits) {
            types.add(SpiritHelper.getSpiritType(string));
        }
        for (MalumSpiritType type : types) {
            Color color = type.getColor();
            for (int i = 0; i < 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                int spinOffset = rand.nextInt(360);
                WorldParticleBuilder.create(LodestoneParticleRegistry.TWINKLE_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(0.4f, 0.8f, 0).build())
                        .setLifetime(20)
                        .setSpinData(SpinParticleData.create(0.7f * spinDirection, 0).setSpinOffset(spinOffset).setSpinOffset(1.25f).setEasing(Easing.CUBIC_IN).build())
                        .setScaleData(GenericParticleData.create(0.075f, 0.15f, 0).setCoefficient(0.8f).setEasing(Easing.QUINTIC_OUT, Easing.EXPO_IN_OUT).build())
                        .setColorData(ColorParticleData.create(ColorHelper.brighter(color, 2), color).build())
                        .enableNoClip()
                        .setRandomOffset(0.6f)
                        .setGravity(1.1f)
                        .addMotion(0, 0.28f + rand.nextFloat() * 0.15f, 0)
                        .disableNoClip()
                        .setRandomMotion(0.1f, 0.15f)
                        .setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
                        .repeat(level, posX, posY, posZ, 2);
            }
            int spinOffset = rand.nextInt(360);
            for (int i = 0; i < 3; i++) {
                int spinDirection = (rand.nextBoolean() ? 1 : -1);
                WorldParticleBuilder.create(LodestoneParticleRegistry.SPARKLE_PARTICLE)
                        .setTransparencyData(GenericParticleData.create(0.12f, 0.06f, 0).setEasing(Easing.SINE_IN, Easing.CIRC_IN).build())
                        .setSpinData(SpinParticleData.create((0.125f + rand.nextFloat() * 0.075f) * spinDirection).setSpinOffset(spinOffset).build())
                        .setScaleData(GenericParticleData.create(0.85f, 0.5f, 0).setEasing(Easing.EXPO_OUT, Easing.SINE_IN).build())
                        .setColorData(ColorParticleData.create(color.brighter(), color.darker()).build())
                        .setLifetime(30)
                        .setRandomOffset(0.4f)
                        .enableNoClip()
                        .setRandomMotion(0.01f, 0.01f)
                        .setDiscardFunction(SimpleParticleOptions.ParticleDiscardFunctionType.ENDING_CURVE_INVISIBLE)
                        .repeat(level, posX, posY, posZ, 5);
            }
        }

        for (int i = 0; i < 3; i++) {
            float multiplier = Mth.nextFloat(level.random, 0.4f, 1f);
            float timeMultiplier = Mth.nextFloat(level.random, 0.9f, 1.4f);
            Color color = new Color((int)(31*multiplier), (int)(19*multiplier), (int)(31*multiplier));
            boolean spinDirection = level.random.nextBoolean();
            WorldParticleBuilder.create(LodestoneParticleRegistry.WISP_PARTICLE)
                .setTransparencyData(GenericParticleData.create(0.4f, 1f, 0).build())
                .setLifetime((int) (45*timeMultiplier))
                .setSpinData(SpinParticleData.create(0.2f*(spinDirection ? 1 : -1)).build())
                .setScaleData(GenericParticleData.create(0.15f, 0.2f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                .setColorData(ColorParticleData.create(color, color).build())
                .enableNoClip()
                .setRandomOffset(0.1f, 0.1f)
                .setRandomMotion(0.01f, 0.02f)
                .addMotion(0, 0.01f, 0)
                .setRenderType(LodestoneWorldParticleRenderType.TRANSPARENT)
                .repeat(level, posX, posY, posZ, 2);

            WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setTransparencyData(GenericParticleData.create(0.35f, 0.55f, 0).build())
                .setLifetime((int) (50*timeMultiplier))
                .setSpinData(SpinParticleData.create(0.1f*(spinDirection ? 1 : -1)).build())
                .setScaleData(GenericParticleData.create(0.35f, 0.4f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                .setColorData(ColorParticleData.create(color, color).build())
                .setRandomOffset(0.2f, 0)
                .enableNoClip()
                .setRandomMotion(0.015f, 0.015f)
                .addMotion(0, 0.01f, 0)
                .setRenderType(LodestoneWorldParticleRenderType.TRANSPARENT)
                .repeat(level, posX, posY, posZ, 3);

            color = new Color((int)(80*multiplier), (int)(40*multiplier), (int)(80*multiplier));
            WorldParticleBuilder.create(LodestoneParticleRegistry.SMOKE_PARTICLE)
                .setTransparencyData(GenericParticleData.create(0.1f, 0.15f, 0).build())
                .setLifetime((int) (50*timeMultiplier))
                .setSpinData(SpinParticleData.create(0.1f*(spinDirection ? 1 : -1)).build())
                .setScaleData(GenericParticleData.create(0.35f, 0.4f, 0).setEasing(Easing.QUINTIC_OUT, Easing.SINE_IN).build())
                .setColorData(ColorParticleData.create(color, color).build())
                .setRandomOffset(0.2f, 0)
                .enableNoClip()
                .setRandomMotion(0.02f, 0.01f)
                .addMotion(0, 0.01f, 0)
                .repeat(level, posX, posY, posZ, 2);
        }
    }


    public static void register(SimpleChannel instance, int index) {
        instance.registerMessage(index, BlightTransformItemParticlePacket.class, BlightTransformItemParticlePacket::encode, BlightTransformItemParticlePacket::decode, BlightTransformItemParticlePacket::handle);
    }

    public static BlightTransformItemParticlePacket decode(FriendlyByteBuf buf) {
        return decode(BlightTransformItemParticlePacket::new, buf);
    }
}
