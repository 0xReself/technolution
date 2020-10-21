package com.github.technolution.init;

import com.github.technolution.technolution.Technolution;
import com.github.technolution.technolution.objects.items.ExampleItem;

import net.minecraft.item.Item;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(Technolution.ModID)
public class ModItems {
    public static final Item Example_Item = new ExampleItem(new Item.Properties());
}
