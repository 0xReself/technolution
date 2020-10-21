package com.github.technolution.technolution;

import com.github.technolution.init.ModBlocks;
import com.github.technolution.init.ModItemGroup;
import com.github.technolution.init.ModItems;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = Technolution.ModID, bus = EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    
    //Get called when forge can register Items
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
            setup(ModItems.Example_Item, "example_item")
        );
        event.getRegistry().register(new BlockItem(ModBlocks.Example_ore, new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)).setRegistryName("example_ore"));
        event.getRegistry().register(new BlockItem(ModBlocks.EssenceFurnace, new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)).setRegistryName("essence_furnace"));
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
            setup(ModBlocks.Example_ore, "example_ore"),
            setup(ModBlocks.EssenceFurnace, "essence_furnace")
        );
    }

    @SubscribeEvent
    public static void onRegisterTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().register(TileEntityType.Builder.create(EssenceFurnaceEntity::new, ModBlocks.EssenceFurnace).build(null).setRegistryName("essence_furnace"));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final String name) {
        return setup(entry, new ResourceLocation(Technolution.ModID, name));
    }

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry, final ResourceLocation registryName) {
        entry.setRegistryName(registryName);
        return entry;
    }
}
