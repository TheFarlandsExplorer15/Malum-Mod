package com.kittykitcatcat.malum.blocks.souljar;

import com.kittykitcatcat.malum.MalumHelper;
import com.kittykitcatcat.malum.MalumMod;
import com.kittykitcatcat.malum.SpiritData;
import com.kittykitcatcat.malum.init.ModBlocks;
import com.kittykitcatcat.malum.init.ModSounds;
import com.kittykitcatcat.malum.init.ModTileEntities;
import com.kittykitcatcat.malum.items.SpiritwoodStaveItem;
import com.kittykitcatcat.malum.network.packets.SoulJarFillPacket;
import com.kittykitcatcat.malum.network.packets.SpiritWhisperPacket;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.datafix.fixes.SpawnEggNames;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;

import java.util.Optional;
import java.util.Random;

import static com.kittykitcatcat.malum.MalumHelper.updateState;
import static com.kittykitcatcat.malum.network.NetworkManager.INSTANCE;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SoulJarBlock extends Block
{
    @SubscribeEvent
    public static void setRenderLayer(FMLClientSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.soul_jar, RenderType.getTranslucent());
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.soul_jar_tile_entity, SoulJarRenderer::new);
    }

    @SubscribeEvent
    public static void setColor(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register((state, world, pos, tintIndex) ->
        {
            return 0xabcdef;
        }, ModBlocks.soul_jar);
    }

    @Override
    public SoundType getSoundType(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity)
    {
        return new SoundType(1.0F, MathHelper.nextFloat(new Random(), 0.8f, 1.2f), ModSounds.soul_jar_break, SoundEvents.BLOCK_GLASS_STEP, ModSounds.soul_jar_place, ModSounds.soul_jar_breaking, SoundEvents.BLOCK_GLASS_FALL);
    }

    public static final IntegerProperty RENDER = IntegerProperty.create("render", 0, 2);
    public SoulJarBlock(Properties properties)
    {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(RENDER, 0));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(RENDER);
        super.fillStateContainer(builder);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
    {
        player.world.notifyBlockUpdate(pos, state, state, 3);
        if (!worldIn.isRemote())
        {
            if (handIn != Hand.OFF_HAND)
            {
                if (worldIn.getTileEntity(pos) instanceof SoulJarTileEntity)
                {
                    SoulJarTileEntity tileEntity = (SoulJarTileEntity) worldIn.getTileEntity(pos);
                    ItemStack heldItem = player.getHeldItem(handIn);
                    if (heldItem.getItem() instanceof SpiritwoodStaveItem)
                    {
                        if (SpiritData.hasSpiritDataInNBT(heldItem.getOrCreateTag()))
                        {
                            SpiritData data = SpiritData.readSpiritDataFromNBT(heldItem.getOrCreateTag());
                            if (tileEntity.data == null)
                            {
                                tileEntity.data = data;
                                heldItem.getOrCreateTag().remove("purity");
                                heldItem.getOrCreateTag().remove("entityRegistryName");
                            }
                            else
                            {
                                tileEntity.data.mergeData(data, 5);
                            }
                            INSTANCE.send(
                                    PacketDistributor.TRACKING_CHUNK.with(() -> worldIn.getChunkAt(pos)),
                                    new SoulJarFillPacket(pos.getX(), pos.getY(), pos.getZ()));
                        }
                    }
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
    @Override
    public boolean isNormalCube(BlockState state, IBlockReader worldIn, BlockPos pos)
    {
        return false;
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
        return true;
    }
    @Override
    public TileEntity createTileEntity(final BlockState state, final IBlockReader world)
    {
        return new SoulJarTileEntity();
    }
}