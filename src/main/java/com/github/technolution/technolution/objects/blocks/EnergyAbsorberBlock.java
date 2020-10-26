package com.github.technolution.technolution.objects.blocks;

import com.github.technolution.technolution.objects.container.EnergyAbsorberContainer;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;
import com.github.technolution.technolution.objects.tileentity.basic.EnergyAbsorberEntityBasic;
import com.github.technolution.technolution.objects.tileentity.eta.EnergyAbsorberEntityEta;
import com.github.technolution.technolution.objects.tileentity.theta.EnergyAbsorberEntityTheta;
import com.github.technolution.technolution.objects.tileentity.zeta.EnergyAbsorberEntityZeta;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class EnergyAbsorberBlock extends Block {

    private int blockTier = 1;

    public EnergyAbsorberBlock(int tier) {
        super(Properties.create(Material.IRON)
            .sound(SoundType.METAL)
            .hardnessAndResistance(2.0f)
        );
        this.blockTier = tier;
    }

    public static void register() {

    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {        
        if(blockTier == 4) {
            return new EnergyAbsorberEntityZeta();
        } else if(blockTier == 3) {
            return new EnergyAbsorberEntityEta();
        } else if(blockTier == 2) {
            return new EnergyAbsorberEntityTheta(); 
        }
        return new EnergyAbsorberEntityBasic();
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        for(int i = 0; i < 6; ++i) {
            if(context.getNearestLookingDirections()[i] != Direction.UP && context.getNearestLookingDirections()[i] != Direction.DOWN) {
                return getDefaultState().with(BlockStateProperties.FACING, context.getNearestLookingDirections()[i].getOpposite());
            }
        }
        return getDefaultState().with(BlockStateProperties.FACING, Direction.NORTH);     
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if(!world.isRemote) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof EnergyAbsorberEntity) {
                INamedContainerProvider provider = new INamedContainerProvider() {
                    @Override
                    public ITextComponent getDisplayName() {
                        return new TranslationTextComponent("screen.technolution.energy_absorber_block");
                    }

                    @Override
                    public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                        return new EnergyAbsorberContainer(i, world, pos, playerInventory, playerEntity);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) player, provider, tileEntity.getPos());
            } else {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING, BlockStateProperties.POWERED);
    }
}