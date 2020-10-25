package com.github.technolution.technolution.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

import java.util.List;

import com.github.technolution.technolution.init.ModItemGroup;


public class CrystalItem extends Item {
    private int burnTime = 200;
    private int multipliere = 1;
    public CrystalItem(int tier){
        super(new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP));
        burnTime *= multipliere;
    }
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> info, ITooltipFlag flagIn) {
        //info.add("Burntime: ")
        super.addInformation(stack, worldIn, info, flagIn);
    }
}
