package com.github.technolution.technolution.init;

import com.github.technolution.technolution.Technolution;
import com.github.technolution.technolution.objects.blocks.EssenceFurnace;
import com.github.technolution.technolution.objects.blocks.ExampleOre;
import com.github.technolution.technolution.objects.items.ExampleItem;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Register {
    //Get called when forge can register Items
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Technolution.ModID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Technolution.ModID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, Technolution.ModID);

    //Blocks
    public static final RegistryObject<Block> EXAMPLE_ORE = BLOCKS.register("example_ore", () -> new ExampleOre());
    public static final RegistryObject<Block> ESSENCE_FURNACE = BLOCKS.register("essence_furnace", () -> new EssenceFurnace());

    //Items
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new ExampleItem(new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    
    //Block Items
    public static final RegistryObject<Item> EXAMPLE_ORE_ITEM = ITEMS.register("example_ore", () -> new BlockItem(EXAMPLE_ORE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ESSENCE_FURNACE_ITEM = ITEMS.register("essence_furnace", () -> new BlockItem(ESSENCE_FURNACE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));

    //Entities
    public static final RegistryObject<TileEntityType<EssenceFurnaceEntity>> ESSENCE_FURNACE_ENTITY = TILE_ENTITIES.register("essence_furnace_entity", () -> TileEntityType.Builder.create(EssenceFurnaceEntity::new, ESSENCE_FURNACE.get()).build(null));

    public static void init(final IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
		TILE_ENTITIES.register(eventBus);
	}
}