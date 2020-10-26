package com.github.technolution.technolution.objects.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import java.util.List;

import com.github.technolution.technolution.init.ModItemGroup;

public class CrystalItem extends Item {
    private int burnTime = 180;
    public int energyPerSec = 100;

    public CrystalItem(int tier){
        super(new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP));
        energyPerSec = burnTime * (int)Math.pow(tier, 2);
        burnTime /= tier;
    }

    @Override
    public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> info, ITooltipFlag flagIn) {
        TranslationTextComponent text = new TranslationTextComponent("crystal_item_info", String.valueOf(burnTime));
        info.add(text);
        super.addInformation(stack, worldIn, info, flagIn);
    }
}
