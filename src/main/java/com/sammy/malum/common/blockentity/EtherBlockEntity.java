package com.sammy.malum.common.blockentity;

import com.sammy.malum.common.block.ether.*;
import com.sammy.malum.common.item.ether.AbstractEtherItem;
import com.sammy.malum.common.item.ether.EtherItem;
import com.sammy.malum.core.setup.client.ParticleRegistry;
import com.sammy.malum.core.setup.content.block.BlockEntityRegistry;
import com.sammy.ortus.helpers.BlockHelper;
import com.sammy.ortus.helpers.ColorHelper;
import com.sammy.ortus.helpers.NBTHelper;
import com.sammy.ortus.setup.OrtusParticleRegistry;
import com.sammy.ortus.systems.blockentity.OrtusBlockEntity;
import com.sammy.ortus.systems.easing.Easing;
import com.sammy.ortus.systems.rendering.particle.ParticleBuilders;
import com.sammy.ortus.systems.rendering.particle.SimpleParticleOptions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.network.protocol.game.ClientboundLevelChunkPacketData;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.WallTorchBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import org.jline.reader.LineReader;

import java.awt.*;
import java.util.function.Function;

public class EtherBlockEntity extends OrtusBlockEntity {
    public int firstColorRGB;
    public Color firstColor;
    public int secondColorRGB;
    public Color secondColor;

    public EtherBlockEntity(BlockEntityType<? extends EtherBlockEntity> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public EtherBlockEntity(BlockPos pos, BlockState state) {
        this(BlockEntityRegistry.ETHER.get(), pos, state);
    }

    public void setFirstColor(int rgb) {
        firstColorRGB = rgb;
        firstColor = new Color(rgb);
    }

    public void setSecondColor(int rgb) {
        secondColorRGB = rgb;
        secondColor = new Color(rgb);
    }

    @Override
    public void load(CompoundTag compound) {
        if (compound.contains("firstColor")) {
            setFirstColor(compound.getInt("firstColor"));
        } else {
            setFirstColor(EtherItem.defaultFirstColor);
        }
        if (getBlockState().getBlock().asItem() instanceof AbstractEtherItem etherItem && etherItem.iridescent) {
            if (compound.contains("secondColor")) {
                setSecondColor(compound.getInt("secondColor"));
            } else {
                setSecondColor(EtherItem.defaultSecondColor);
            }
        }

        super.load(compound);
    }

    @Override
    protected void saveAdditional(CompoundTag compound) {
        compound.putByte("godDammit", (byte) 0); //TODO: figure out what to do about this. For reference, the client won't be told of our block entity data and load() won't run if there is nothing in the compound
        //this could be fixed by just removing the optimization below, but I like said optimization.
        if (firstColor != null && firstColorRGB != EtherItem.defaultFirstColor) {
            compound.putInt("firstColor", firstColorRGB);
        }
        if (getBlockState().getBlock().asItem() instanceof AbstractEtherItem etherItem && etherItem.iridescent) {
            if (secondColor != null && secondColorRGB != EtherItem.defaultSecondColor) {
                compound.putInt("secondColor", secondColorRGB);
            }
        }
        super.saveAdditional(compound);
    }

    @Override
    public void onPlace(LivingEntity placer, ItemStack stack) {
        AbstractEtherItem item = (AbstractEtherItem) stack.getItem();
        setFirstColor(item.getFirstColor(stack));
        if (item.iridescent) {
            setSecondColor(item.getSecondColor(stack));
        }
    }

    @Override
    public ItemStack onClone(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
        ItemStack stack = state.getBlock().asItem().getDefaultInstance();
        AbstractEtherItem etherItem = (AbstractEtherItem) stack.getItem();
        if (firstColor != null) {
            etherItem.setFirstColor(stack, firstColorRGB);
        }
        if (secondColor != null) {
            etherItem.setSecondColor(stack, secondColorRGB);
        }
        return super.onClone(state, target, level, pos, player);
    }

    @Override
    public void init() {
        if (!level.isClientSide) {
            BlockHelper.updateState(level, worldPosition);
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) {
            if (firstColor == null) {
                return;
            }
            Color firstColor = ColorHelper.darker(this.firstColor, 1);
            Color secondColor = this.secondColor == null ? firstColor : ColorHelper.brighter(this.secondColor, 1);
            double x = worldPosition.getX() + 0.5;
            double y = worldPosition.getY() + 0.6;
            double z = worldPosition.getZ() + 0.5;
            int lifeTime = 14 + level.random.nextInt(4);
            float scale = 0.17f + level.random.nextFloat() * 0.03f;
            float velocity = 0.04f + level.random.nextFloat() * 0.02f;
            if (getBlockState().getBlock() instanceof EtherWallTorchBlock) {
                Direction direction = getBlockState().getValue(WallTorchBlock.FACING);
                x += direction.getNormal().getX() * -0.28f;
                y += 0.2f;
                z += direction.getNormal().getZ() * -0.28f;
                lifeTime -= 6;
            }

            if (getBlockState().getBlock() instanceof EtherTorchBlock || getBlockState().getBlock() instanceof EtherWallTorchBlock) {
                lifeTime -= 4;
            }

            if (getBlockState().getBlock() instanceof EtherSconceBlock || getBlockState().getBlock() instanceof EtherWallSconceBlock) {
                y+=0.05f;
            }
            if (getBlockState().getBlock() instanceof EtherBrazierBlock) {
                y -= 0.2f;
                lifeTime -= 2;
                scale *= 1.25f;
            }
            ParticleBuilders.create(OrtusParticleRegistry.WISP_PARTICLE)
                    .setScale(scale, 0)
                    .setAlpha(0.75f, 0.25f)
                    .setLifetime(lifeTime)
                    .setColor(firstColor, secondColor)
                    .setColorCoefficient(1.4f)
                    .setColorEasing(Easing.BOUNCE_IN_OUT)
                    .setSpinOffset((level.getGameTime() * 0.2f) % 6.28f)
                    .setSpin(0.2f, 0.4f)
                    .setSpinEasing(Easing.QUARTIC_IN)
                    .addMotion(0, velocity, 0)
                    .enableNoClip()
                    .spawn(level, x, y, z);

            ParticleBuilders.create(OrtusParticleRegistry.TWINKLE_PARTICLE)
                    .setScale(scale * 2, scale * 0.1f)
                    .setLifetime(lifeTime)
                    .setAlpha(0.25f, 0)
                    .setColor(firstColor, secondColor)
                    .setColorEasing(Easing.SINE_IN)
                    .setColorCoefficient(2.25f)
                    .setSpin(0, 2)
                    .setSpinEasing(Easing.QUARTIC_IN)
                    .enableNoClip()
                    .spawn(level, x, y, z);
            if (level.getGameTime() % 2L == 0) {
                y += 0.15f;
                if (level.random.nextFloat() < 0.5f) {
                    ParticleBuilders.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
                            .setScale(0.5f, 0.75f, 0)
                            .setColor(firstColor, secondColor)
                            .setColorEasing(Easing.CIRC_IN_OUT)
                            .setColorCoefficient(2.5f)
                            .setAlpha(0.2f, 1f, 0)
                            .setAlphaEasing(Easing.SINE_IN, Easing.QUAD_IN)
                            .setAlphaCoefficient(3.5f)
                            .randomOffset(0.15f, 0.2f)
                            .addMotion(0, 0.0035f, 0)
                            .randomMotion(0.001f, 0.005f)
                            .setMotionCoefficient(0.985f-level.random.nextFloat() * 0.04f)
                            .enableNoClip()
                            .overwriteRemovalProtocol(SimpleParticleOptions.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                            .spawn(level, x, y, z);
                }
                if (level.random.nextFloat() < 0.25f) {
                    ParticleBuilders.create(ParticleRegistry.SPIRIT_FLAME_PARTICLE)
                            .setScale(0.3f, 0.5f, 0)
                            .setColor(firstColor, secondColor)
                            .setColorEasing(Easing.CIRC_IN_OUT)
                            .setColorCoefficient(3.5f)
                            .setAlpha(0.2f, 1f, 0)
                            .setAlphaEasing(Easing.SINE_IN, Easing.CIRC_IN_OUT)
                            .setAlphaCoefficient(3.5f)
                            .randomOffset(0.1f, 0.225f)
                            .addMotion(0, velocity / 2f, 0)
                            .randomMotion(0, 0.015f)
                            .setMotionCoefficient(0.97f-level.random.nextFloat() * 0.025f)
                            .enableNoClip()
                            .overwriteRemovalProtocol(SimpleParticleOptions.SpecialRemovalProtocol.ENDING_CURVE_INVISIBLE)
                            .spawn(level, x, y, z);
                }
            }
        }
    }
}