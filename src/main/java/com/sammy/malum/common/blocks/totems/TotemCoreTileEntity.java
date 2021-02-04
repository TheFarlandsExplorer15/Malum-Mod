package com.sammy.malum.common.blocks.totems;

import com.sammy.malum.MalumHelper;
import com.sammy.malum.core.init.MalumSounds;
import com.sammy.malum.core.init.blocks.MalumBlocks;
import com.sammy.malum.core.init.blocks.MalumTileEntities;
import com.sammy.malum.core.init.particles.MalumParticles;
import com.sammy.malum.core.modcontent.MalumRites;
import com.sammy.malum.core.modcontent.MalumRites.MalumRite;
import com.sammy.malum.core.systems.particles.ParticleManager;
import com.sammy.malum.core.systems.spirits.SpiritHelper;
import com.sammy.malum.core.systems.spirits.MalumSpiritType;
import com.sammy.malum.core.systems.tileentities.SimpleTileEntity;
import com.sammy.malum.core.systems.totems.rites.IPoppetBlessing;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;

import java.awt.*;
import java.util.ArrayList;

public class TotemCoreTileEntity extends SimpleTileEntity implements ITickableTileEntity
{
    public TotemCoreTileEntity()
    {
        super(MalumTileEntities.TOTEM_CORE_TILE_ENTITY.get());
    }
    public boolean active;
    public int progress;
    public int height;
    public MalumRite rite;
    public ArrayList<MalumSpiritType> spirits = new ArrayList<>();
    
    @Override
    public void remove()
    {
        for (int i = 1; i < height; i++)
        {
            if ((world.getBlockState(pos.up(i)).getBlock() instanceof TotemPoleBlock))
            {
                resetBlock(pos.up(i), true);
            }
        }
        super.remove();
    }
    
    @Override
    public void tick()
    {
        if (MalumHelper.areWeOnServer(world))
        {
            if (rite != null)
            {
                if (rite.cooldown() == 0 || world.getGameTime() % rite.cooldown() == 0)
                {
                    rite.effect(pos, world);
                }
                return;
            }
            if (active)
            {
                if (progress == 0)
                {
                    BlockPos previousPolePos = pos.up(height);
                    height++;
                    BlockPos polePos = pos.up(height);
    
                    if (world.getTileEntity(polePos) instanceof TotemPoleTileEntity)
                    {
                        TotemPoleTileEntity totemPoleTileEntity = (TotemPoleTileEntity) world.getTileEntity(polePos);
                        if (world.getTileEntity(previousPolePos) instanceof TotemPoleTileEntity)
                        {
                            TotemPoleTileEntity previousTotemPoleTileEntity = (TotemPoleTileEntity) world.getTileEntity(previousPolePos);
                            if (!previousTotemPoleTileEntity.direction.equals(totemPoleTileEntity.direction))
                            {
                                fail();
                                return;
                            }
                        }
                        if (totemPoleTileEntity.type != null)
                        {
                            totemPoleTileEntity.start(pos);
                            MalumHelper.updateState(world, polePos);
                            addRune(polePos, totemPoleTileEntity.type);
                        }
                    }
                    else
                    {
                        tryRite();
                    }
                }
                else
                {
                    progress--;
                }
            }
        }
    }
    
    public void addRune(BlockPos pos, MalumSpiritType rune)
    {
        world.playSound(null, pos, MalumSounds.TOTEM_CHARGE, SoundCategory.BLOCKS, 1, 0.75f + height * 0.25f);
        spirits.add(rune);
        progress = 20;
    }
    
    public void tryRite()
    {
        MalumRite rite = MalumRites.getRite(spirits);
        if (rite != null)
        {
            world.playSound(null, pos, MalumSounds.TOTEM_CHARGE, SoundCategory.BLOCKS, 1, 0.5f + world.rand.nextFloat() * 0.25f);
            progress = 0;
            for (int i = 0; i < height; i++)
            {
                BlockPos currentPolePos = getPos().up(i);
                if (world.getTileEntity(currentPolePos) instanceof TotemPoleTileEntity)
                {
                    ((TotemPoleTileEntity) world.getTileEntity(currentPolePos)).activate();
                }
            }
            if (rite instanceof IPoppetBlessing)
            {
                ((IPoppetBlessing) rite).blessPoppet(pos, world, rite);
            }
            if (rite.isInstant)
            {
                rite.effect(pos, world);
                reset(false);
                
                return;
            }
            this.rite = rite;
        }
        else
        {
            fail();
        }
        MalumHelper.updateState(world, pos);
    }
    
    public void fail()
    {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, SoundCategory.BLOCKS, 1, 0.5f);
        reset(true);
    }
    
    public void resetBlock(BlockPos pos, boolean isFailure)
    {
        if (world.getBlockState(pos).getBlock() instanceof TotemPoleBlock)
        {
            world.setBlockState(pos, MalumBlocks.RUNEWOOD_LOG.get().getDefaultState());
            if (isFailure)
            {
            
            }
        }
    }
    
    public void reset(boolean isFailure)
    {
        for (int i = 1; i < height; i++)
        {
            resetBlock(pos.up(i), isFailure);
        }
        active = false;
        progress = 0;
        height = 0;
        rite = null;
        spirits = new ArrayList<>();
    }
    
    @Override
    public CompoundNBT writeData(CompoundNBT compound)
    {
        compound.putBoolean("active", active);
        compound.putInt("progress", progress);
        compound.putInt("height", height);
        
        int i = 0;
        for (MalumSpiritType spiritType : spirits)
        {
            compound.putString("spiritType" + i, spiritType.identifier);
            i++;
        }
        compound.putInt("spiritCount", i);
        return super.writeData(compound);
    }
    
    @Override
    public void readData(CompoundNBT compound)
    {
        active = compound.getBoolean("active");
        progress = compound.getInt("progress");
        height = compound.getInt("height");
        spirits = new ArrayList<>();
        for (int i = 0; i < compound.getInt("spiritCount"); i++)
        {
            MalumSpiritType spiritType = SpiritHelper.figureOutType(compound.getString("spiritType" + i));
            spirits.add(spiritType);
        }
        rite = MalumRites.getRite(spirits);
        super.readData(compound);
    }
}