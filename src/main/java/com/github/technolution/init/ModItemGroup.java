package com.github.technolution.init;

import java.util.function.Supplier;

import com.github.technolution.technolution.Technolution;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup extends ItemGroup {

    public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(Technolution.ModID, () -> new ItemStack(ModItems.Example_Item));

    private final Supplier<ItemStack> iconSupplier;
    
    public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
        super(name);
        this.iconSupplier = iconSupplier;
    }

    @Override
    public ItemStack createIcon() {
        return iconSupplier.get();
    }
}
