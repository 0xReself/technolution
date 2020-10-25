package com.github.technolution.technolution.objects.blocks;

import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;

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
        
        if(!worldIn.isRemote()) {
            EssenceFurnaceEntity ent = (EssenceFurnaceEntity) worldIn.getTileEntity(pos);
            FluidUtil.interactWithFluidHandler(player, handIn, ent.getTank());
            player.sendMessage(new TranslationTextComponent("Tank: (" + ent.getTank().getFluidAmount() + "/" + ent.getTank().getCapacity() + ")"));
        }
        return ActionResultType.CONSUME;
    }
    
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EssenceFurnaceEntity();
    }
}
