package com.github.technolution.technolution.objects.blocks;

import com.github.technolution.technolution.objects.container.EssenceFurnaceContainer;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fml.network.NetworkHooks;

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
        
        if(worldIn.isRemote) return ActionResultType.SUCCESS;

        EssenceFurnaceEntity ent = (EssenceFurnaceEntity) worldIn.getTileEntity(pos);
        
        INamedContainerProvider provider = new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.mytutorial.firsblock");
            }

            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new EssenceFurnaceContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
        NetworkHooks.openGui((ServerPlayerEntity) player, provider, ent.getPos());

        FluidUtil.interactWithFluidHandler(player, handIn, ent.getTank());
        player.sendMessage(new TranslationTextComponent("Tank: (" + ent.getTank().getFluidAmount() + "/" + ent.getTank().getCapacity() + ")"));
        return ActionResultType.CONSUME;
    }
    
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new EssenceFurnaceEntity();
    }
}
