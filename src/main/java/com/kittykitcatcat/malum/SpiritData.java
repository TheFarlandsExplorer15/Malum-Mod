package com.kittykitcatcat.malum;

import com.kittykitcatcat.malum.blocks.souljar.SoulJarTileEntity;
import com.kittykitcatcat.malum.init.ModBlocks;
import com.kittykitcatcat.malum.recipes.SpiritInfusionRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kittykitcatcat.malum.MalumHelper.makeTooltip;

public class SpiritData
{
    public static final IItemPropertyGetter spiritProperty = (stack, world, entity) -> stack.getTag() != null && stack.getTag().contains("entityRegistryName") ? 1 : 0;
    public String entityRegistryName;
    public float purity;

    public SpiritData(LivingEntity entity, float purity)
    {
        if (entity.getType().getRegistryName() != null)
        {
            entityRegistryName = entity.getType().getRegistryName().toString();
            this.purity = purity;
        }
    }

    public SpiritData(String entityRegistryName, float purity)
    {
        this.entityRegistryName = entityRegistryName;
        this.purity = purity;
    }

    public Optional<EntityType<?>> getType()
    {
        return EntityType.byKey(entityRegistryName);
    }

    public void writeSpiritDataIntoNBT(CompoundNBT nbt)
    {
        if (entityRegistryName != null)
        {
            nbt.putString("entityRegistryName", entityRegistryName);
        }
        if (purity != 0)
        {
            nbt.putFloat("purity", purity);
        }
    }

    public static boolean hasSpiritDataInNBT(CompoundNBT nbt)
    {
        return nbt.contains("entityRegistryName") && nbt.contains("purity");
    }
    public static SpiritData readSpiritDataFromNBT(CompoundNBT nbt)
    {
       return new SpiritData(nbt.getString("entityRegistryName"), nbt.getFloat("purity"));
    }
    public boolean isPureEnough(float amount)
    {
        return purity >= amount;
    }
    public boolean isEqualEntity(SpiritData data)
    {
        return entityRegistryName.equals(data.entityRegistryName);
    }
    public void mergeData(SpiritData data, float capacity)
    {
        float cachedPurity = data.purity;
        for (float f = 0; f <= cachedPurity; f+= 0.01f)
        {
            data.reducePurity(0.01f);
            purity+= 0.01f;
            if (purity >= capacity)
            {
                return;
            }
        }
    }
    public void reducePurity(float amount)
    {
        purity -= amount;
        if (purity <= 0)
        {
            entityRegistryName = null;
        }
    }
    public static void decreaseSpiritPurity(PlayerEntity playerEntity, CompoundNBT nbt, float amount)
    {
        nbt.putFloat("purity", nbt.getFloat("purity") - amount);
        if (nbt.getFloat("purity") <= 0.0001)
        {
            nbt.remove("entityRegistryName");
            nbt.remove("purity");
        }
    }

    public static SpiritData findSpiritData(int radius, SpiritInfusionRecipe recipe, BlockPos pos, World worldIn)
    {
        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                int posX = pos.getX() + x;
                int posZ = pos.getZ() + z;
                BlockPos blockPos = new BlockPos(posX, pos.getY(), posZ);
                BlockState state1 = worldIn.getBlockState(blockPos);
                if (state1.getBlock().equals(ModBlocks.soul_jar))
                {
                    if (worldIn.getTileEntity(blockPos) instanceof SoulJarTileEntity)
                    {
                        SpiritData data = recipe.getData();
                        SoulJarTileEntity tileEntity = (SoulJarTileEntity) worldIn.getTileEntity(blockPos);
                        if (data.entityRegistryName.equals(tileEntity.data.entityRegistryName))
                        {
                            if (tileEntity.data.purity >= data.purity)
                            {
                                return tileEntity.data;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public static BlockPos findDataBlock(int radius, SpiritInfusionRecipe recipe, BlockPos pos, World worldIn)
    {
        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                int posX = pos.getX() + x;
                int posZ = pos.getZ() + z;
                BlockPos blockPos = new BlockPos(posX, pos.getY(), posZ);
                BlockState state1 = worldIn.getBlockState(blockPos);
                if (state1.getBlock().equals(ModBlocks.soul_jar))
                {
                    if (worldIn.getTileEntity(blockPos) instanceof SoulJarTileEntity)
                    {
                        SpiritData data = recipe.getData();
                        SoulJarTileEntity tileEntity = (SoulJarTileEntity) worldIn.getTileEntity(blockPos);
                        if (data.entityRegistryName.equals(tileEntity.data.entityRegistryName))
                        {
                            if (tileEntity.data.purity >= data.purity)
                            {
                                return blockPos;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    @OnlyIn(Dist.CLIENT)
    public static void makeDefaultTooltip(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn, ArrayList<ITextComponent> extraComponents)
    {
        stack.getOrCreateTag();
        ArrayList<ITextComponent> arrayList = new ArrayList<>(extraComponents);
        if (stack.getOrCreateTag().contains("entityRegistryName") && stack.getTag().contains("purity"))
        {
            CompoundNBT nbt = stack.getTag();
            SpiritData data = new SpiritData(nbt.getString("entityRegistryName"), nbt.getFloat("purity"));
            arrayList.add(new TranslationTextComponent("malum.tooltip.name.desc").applyTextStyle(TextFormatting.GRAY)
                    .appendSibling(new StringTextComponent(" " + data.getType().get().getName().getString()).applyTextStyle(TextFormatting.DARK_PURPLE)));
            arrayList.add(new TranslationTextComponent("malum.tooltip.purity.desc").applyTextStyle(TextFormatting.GRAY)
                    .appendSibling(new StringTextComponent(" " + String.format("%.3f", data.purity)).applyTextStyle(TextFormatting.DARK_PURPLE)));
        }
        makeTooltip(stack, worldIn, tooltip, flagIn, arrayList);
    }

    @OnlyIn(Dist.CLIENT)
    public static void makeDefaultTooltip(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        if (stack.getOrCreateTag().contains("entityRegistryName") && stack.getOrCreateTag().contains("purity"))
        {
            CompoundNBT nbt = stack.getTag();
            SpiritData data = new SpiritData(nbt.getString("entityRegistryName"), nbt.getFloat("purity"));
            ArrayList<ITextComponent> arrayList = new ArrayList<>();
            if (data.getType().isPresent())
            {
                arrayList.add(new TranslationTextComponent("malum.tooltip.name.desc").applyTextStyle(TextFormatting.GRAY)
                        .appendSibling(new StringTextComponent(" " + data.getType().get().getName().getString()).applyTextStyle(TextFormatting.DARK_PURPLE)));
            }
            else
            {
                arrayList.add(new TranslationTextComponent("malum.tooltip.name.desc").applyTextStyle(TextFormatting.GRAY)
                        .appendSibling(new StringTextComponent(" bruhmoment").applyTextStyle(TextFormatting.DARK_PURPLE).applyTextStyle(TextFormatting.OBFUSCATED)));
            }
            arrayList.add(new TranslationTextComponent("malum.tooltip.purity.desc").applyTextStyle(TextFormatting.GRAY)
                    .appendSibling(new StringTextComponent(" " + data.purity).applyTextStyle(TextFormatting.DARK_PURPLE)));
            makeTooltip(stack, worldIn, tooltip, flagIn, arrayList);
        }
    }
}
