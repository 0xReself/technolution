package com.github.technolution.technolution.init;

import com.github.technolution.technolution.Technolution;
import com.github.technolution.technolution.objects.blocks.EnergyAbsorberBlock;
import com.github.technolution.technolution.objects.blocks.EssenceFurnace;
import com.github.technolution.technolution.objects.blocks.ExampleOre;
import com.github.technolution.technolution.objects.container.EnergyAbsorberContainer;
import com.github.technolution.technolution.objects.blocks.TurquoiseOreBlock;
import com.github.technolution.technolution.objects.items.ExampleItem;
import com.github.technolution.technolution.objects.items.TurquoiseCrystalItem;
import com.github.technolution.technolution.objects.tileentity.EnergyAbsorberEntity;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;

import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Register {
    //Get called when forge can register Items
    public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Technolution.ModID);
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Technolution.ModID);
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<TileEntityType<?>>(ForgeRegistries.TILE_ENTITIES, Technolution.ModID);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = new DeferredRegister<ContainerType<?>>(ForgeRegistries.CONTAINERS, Technolution.ModID);

    //Container
    public static final RegistryObject<ContainerType<EnergyAbsorberContainer>> ENERGY_ABSORBER_CONTAINER = CONTAINERS.register("energy_absorber_block", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new EnergyAbsorberContainer(windowId, world, pos, inv, inv.player);
    }));

    //Blocks
    public static final RegistryObject<Block> EXAMPLE_ORE = BLOCKS.register("example_ore", () -> new ExampleOre());
    public static final RegistryObject<Block> ESSENCE_FURNACE = BLOCKS.register("essence_furnace", () -> new EssenceFurnace());
    public static final RegistryObject<Block> ENERGY_ABSORBER_BLOCK = BLOCKS.register("energy_absorber_block", () -> new EnergyAbsorberBlock());
    public static final RegistryObject<Block> TURQUOISE_ORE_BLOCK = BLOCKS.register("turquoise_ore_block", () -> new TurquoiseOreBlock());

    //Items
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new ExampleItem(new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> TURQUOISE_CRYSTAL_ITEM = ITEMS.register("turquoise_crystal_item", () -> new TurquoiseCrystalItem());
    
    //Block Items
    public static final RegistryObject<Item> EXAMPLE_ORE_ITEM = ITEMS.register("example_ore", () -> new BlockItem(EXAMPLE_ORE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> TURQUOISE_ORE_ITEM = ITEMS.register("turquoise_ore_block", () -> new BlockItem(TURQUOISE_ORE_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ESSENCE_FURNACE_ITEM = ITEMS.register("essence_furnace", () -> new BlockItem(ESSENCE_FURNACE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ENERGY_ABSORBER_ITEM = ITEMS.register("energy_absorber_block", () -> new BlockItem(ENERGY_ABSORBER_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    //Entities
    public static final RegistryObject<TileEntityType<EssenceFurnaceEntity>> ESSENCE_FURNACE_ENTITY = TILE_ENTITIES.register("essence_furnace_entity", () -> TileEntityType.Builder.create(EssenceFurnaceEntity::new, ESSENCE_FURNACE.get()).build(null));
    public static final RegistryObject<TileEntityType<EnergyAbsorberEntity>> ENERGY_ABSORBER_ENTITY = TILE_ENTITIES.register("energy_absorber_entity", () -> TileEntityType.Builder.create(EnergyAbsorberEntity::new, ENERGY_ABSORBER_BLOCK.get()).build(null));
    
    public static void init(final IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
        CONTAINERS.register(eventBus);
	}
}