package com.github.technolution.technolution.objects.blocks;

import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.IFluidHandler;

public class EssenceFurnace extends Block {

    public EssenceFurnace() {
        super(Block.Properties.create(Material.ROCK));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);

        if(worldIn.isRemote()) {
            return ActionResultType.CONSUME;
        }

        if(stack.getItem() == Items.AIR) {
            return ActionResultType.CONSUME;
        }

        EssenceFurnaceEntity ent = (EssenceFurnaceEntity) worldIn.getTileEntity(pos);
        if(stack.getItem() instanceof BucketItem) {
            BucketItem bucket = (BucketItem) stack.getItem();
            if(stack.getItem() == Items.BUCKET) {
                if(ent.getTank().getFluidAmount() >= 1000) {
                    if(!player.isCreative()) {
                        if(stack.getCount() > 1) {
                            stack.shrink(1);
                            player.addItemStackToInventory(FluidUtil.getFilledBucket(ent.getTank().getFluid()));
                        } else {
                            player.setHeldItem(handIn, FluidUtil.getFilledBucket(ent.getTank().getFluid()));
                        }
                    }
                    ent.getTank().drain(1000, IFluidHandler.FluidAction.EXECUTE);
                    worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                    player.sendMessage(new TranslationTextComponent("Tank: " + ent.getTank().getFluidAmount() + " / " + ent.getTank().getCapacity()));
                    return ActionResultType.SUCCESS;
                }
            } else {
                if(bucket.getFluid() == ent.getTank().getFluid().getFluid() || ent.getTank().isEmpty()) {
                    if(ent.getTank().getTankCapacity(0) - ent.getTank().getFluidAmount() >= 1000) {
                        ent.getTank().fill(new FluidStack(bucket.getFluid(), 1000), IFluidHandler.FluidAction.EXECUTE);
                        if(!player.isCreative()) {
                            player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
                        }
                        worldIn.playSound((PlayerEntity) null, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
                        player.sendMessage(new TranslationTextComponent("Tank: " + ent.getTank().getFluidAmount() + " / " + ent.getTank().getCapacity()));
                        return ActionResultType.SUCCESS;
                    }
                }
            }
        }
        player.sendMessage(new TranslationTextComponent("Tank: " + ent.getTank().getFluidAmount() + " / " + ent.getTank().getCapacity()));
        return ActionResultType.CONSUME;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EssenceFurnaceEntity();
    }
}
