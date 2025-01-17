package com.sammy.malum.common.packets;

import com.sammy.malum.common.capability.MalumLivingEntityDataCapability;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import team.lodestar.lodestone.systems.network.LodestoneClientPacket;

import java.util.function.Supplier;

public class VoidRejectionPacket extends LodestoneClientPacket {
    private final int entityId;

    public VoidRejectionPacket(int entityId) {
        this.entityId = entityId;
    }

    public void encode(FriendlyByteBuf buf) {
        buf.writeInt(entityId);
    }

    @OnlyIn(Dist.CLIENT)
    public void execute(Supplier<NetworkEvent.Context> context) {
        Entity entity = Minecraft.getInstance().level.getEntity(entityId);
        if (entity instanceof LivingEntity livingEntity) {
            MalumLivingEntityDataCapability.getCapabilityOptional(livingEntity).ifPresent(c -> c.touchOfDarknessHandler.reject(livingEntity));
        }
    }

    public static void register(SimpleChannel instance, int index) {
        instance.registerMessage(index, VoidRejectionPacket.class, VoidRejectionPacket::encode, VoidRejectionPacket::decode, VoidRejectionPacket::handle);
    }

    public static VoidRejectionPacket decode(FriendlyByteBuf buf) {
        return new VoidRejectionPacket(buf.readInt());
    }
}