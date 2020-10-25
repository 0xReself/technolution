package com.github.technolution.technolution.objects.items;

import com.github.technolution.technolution.init.ModItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class TurquoiseCrystaItem extends Item{

    public TurquoiseCrystaItem(Properties properties) {
        super(properties.group(ModItemGroup.MOD_ITEM_GROUP));
    }
}
