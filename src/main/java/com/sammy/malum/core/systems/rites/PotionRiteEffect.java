package com.sammy.malum.core.systems.rites;

import com.sammy.malum.common.blockentity.totem.TotemBaseBlockEntity;
import com.sammy.malum.common.packets.particle.entity.MajorEntityEffectParticlePacket;
import com.sammy.malum.core.systems.spirit.MalumSpiritType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.PacketDistributor;

import java.util.function.Supplier;

import static com.sammy.malum.core.setup.server.PacketRegistry.MALUM_CHANNEL;

public class PotionRiteEffect extends EntityAffectingRiteEffect {

    public final Class<? extends LivingEntity> targetClass;
    public final Supplier<MobEffect> effect;
    public final MalumSpiritType spirit;

    public PotionRiteEffect(Class<? extends LivingEntity> targetClass, Supplier<MobEffect> effect, MalumSpiritType spirit) {
        super();
        this.targetClass = targetClass;
        this.effect = effect;
        this.spirit = spirit;
    }

    @Override
    public void riteEffect(TotemBaseBlockEntity totemBase) {
        getNearbyEntities(totemBase, getEntityClass()).forEach(e -> {
            if (!e.hasEffect(effect.get())) {
                MALUM_CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> e), new MajorEntityEffectParticlePacket(spirit.getColor(), e.getX(), e.getY()+ e.getBbHeight() / 2f, e.getZ()));
            }
            e.addEffect(new MobEffectInstance(effect.get(), getEffectDuration(), getEffectAmplifier()));
        });
    }

    public Class<? extends LivingEntity> getEntityClass() {
        return targetClass;
    }

    public int getEffectDuration() {
        return 300;
    }

    public int getEffectAmplifier() {
        return 1;
    }
}