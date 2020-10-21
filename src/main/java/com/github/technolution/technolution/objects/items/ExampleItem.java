package com.github.technolution.technolution.objects.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ExampleItem extends Item{

    public ExampleItem(Properties properties) {
        super(properties);
    }
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.setPosition(-100, -100, -100);
        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
