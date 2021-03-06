package com.github.technolution.technolution.init;

import com.github.technolution.technolution.Technolution;
import com.github.technolution.technolution.objects.blocks.CableBlock;
import com.github.technolution.technolution.objects.blocks.CrystalOreBlock;
import com.github.technolution.technolution.objects.blocks.EnergyAbsorberBlock;
import com.github.technolution.technolution.objects.blocks.EssenceFurnace;
import com.github.technolution.technolution.objects.blocks.ExampleOre;
import com.github.technolution.technolution.objects.blocks.MagicalDirtBlock;
import com.github.technolution.technolution.objects.container.EnergyAbsorberContainer;
import com.github.technolution.technolution.objects.items.CrystalItem;
import com.github.technolution.technolution.objects.items.ExampleItem;
import com.github.technolution.technolution.objects.tileentity.CableEntity;
import com.github.technolution.technolution.objects.tileentity.EssenceFurnaceEntity;
import com.github.technolution.technolution.objects.tileentity.basic.EnergyAbsorberEntityBasic;
import com.github.technolution.technolution.objects.tileentity.eta.EnergyAbsorberEntityEta;
import com.github.technolution.technolution.objects.tileentity.theta.EnergyAbsorberEntityTheta;
import com.github.technolution.technolution.objects.tileentity.zeta.EnergyAbsorberEntityZeta;
import com.github.technolution.technolution.objects.world.structurs.MagicFieldPieces;
import com.github.technolution.technolution.objects.world.structurs.MagicFieldStructure;

import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
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

    //Energy_ABSORBER
    public static final RegistryObject<Block> ENERGY_ABSORBER_BLOCK_BASIC = BLOCKS.register("energy_absorber_block_basic", () -> new EnergyAbsorberBlock(1));
    public static final RegistryObject<Block> ENERGY_ABSORBER_BLOCK_THETA = BLOCKS.register("energy_absorber_block_theta", () -> new EnergyAbsorberBlock(2));
    public static final RegistryObject<Block> ENERGY_ABSORBER_BLOCK_ETA = BLOCKS.register("energy_absorber_block_eta", () -> new EnergyAbsorberBlock(3));
    public static final RegistryObject<Block> ENERGY_ABSORBER_BLOCK_ZETA = BLOCKS.register("energy_absorber_block_zeta", () -> new EnergyAbsorberBlock(4));

    public static final RegistryObject<Item> ENERGY_ABSORBER_ITEM_BASIC = ITEMS.register("energy_absorber_block_basic", () -> new BlockItem(ENERGY_ABSORBER_BLOCK_BASIC.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ENERGY_ABSORBER_ITEM_THETA = ITEMS.register("energy_absorber_block_theta", () -> new BlockItem(ENERGY_ABSORBER_BLOCK_THETA.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ENERGY_ABSORBER_ITEM_ETA = ITEMS.register("energy_absorber_block_eta", () -> new BlockItem(ENERGY_ABSORBER_BLOCK_ETA.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ENERGY_ABSORBER_ITEM_ZETA = ITEMS.register("energy_absorber_block_zeta", () -> new BlockItem(ENERGY_ABSORBER_BLOCK_ZETA.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));

    public static final RegistryObject<TileEntityType<EnergyAbsorberEntityBasic>> ENERGY_ABSORBER_ENTITY_BASIC = TILE_ENTITIES.register("energy_absorber_entity_basic", () -> TileEntityType.Builder.create(EnergyAbsorberEntityBasic::new, ENERGY_ABSORBER_BLOCK_BASIC.get()).build(null));
    public static final RegistryObject<TileEntityType<EnergyAbsorberEntityTheta>> ENERGY_ABSORBER_ENTITY_THETA = TILE_ENTITIES.register("energy_absorber_entity_theta", () -> TileEntityType.Builder.create(EnergyAbsorberEntityTheta::new, ENERGY_ABSORBER_BLOCK_THETA.get()).build(null));
    public static final RegistryObject<TileEntityType<EnergyAbsorberEntityEta>> ENERGY_ABSORBER_ENTITY_ETA = TILE_ENTITIES.register("energy_absorber_entity_eta", () -> TileEntityType.Builder.create(EnergyAbsorberEntityEta::new, ENERGY_ABSORBER_BLOCK_ETA.get()).build(null));
    public static final RegistryObject<TileEntityType<EnergyAbsorberEntityZeta>> ENERGY_ABSORBER_ENTITY_ZETA = TILE_ENTITIES.register("energy_absorber_entity_zeta", () -> TileEntityType.Builder.create(EnergyAbsorberEntityZeta::new, ENERGY_ABSORBER_BLOCK_ZETA.get()).build(null));

    //Crystals
    public static final RegistryObject<Block> THETA_ORE_BLOCK = BLOCKS.register("theta_ore_block", () -> new CrystalOreBlock(1));
    public static final RegistryObject<Block> ETA_ORE_BLOCK = BLOCKS.register("eta_ore_block", () -> new CrystalOreBlock(2));
    public static final RegistryObject<Block> ZETA_ORE_BLOCK = BLOCKS.register("zeta_ore_block", () -> new CrystalOreBlock(3));
    public static final RegistryObject<Block> CABLE_BLOCK = BLOCKS.register("cable_block", () -> new CableBlock());

    public static final RegistryObject<Item> THETA_ORE_ITEM = ITEMS.register("theta_ore_block", () -> new BlockItem(THETA_ORE_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ETA_ORE_ITEM = ITEMS.register("eta_ore_block", () -> new BlockItem(ETA_ORE_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ZETA_ORE_ITEM = ITEMS.register("zeta_ore_block", () -> new BlockItem(ZETA_ORE_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));

    //Items
    public static final RegistryObject<Item> EXAMPLE_ITEM = ITEMS.register("example_item", () -> new ExampleItem(new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> THETA_CRYSTAL_ITEM = ITEMS.register("theta_crystal_item", () -> new CrystalItem(1));
    public static final RegistryObject<Item> ETA_CRYSTAL_ITEM = ITEMS.register("eta_crystal_item", () -> new CrystalItem(2));
    public static final RegistryObject<Item> ZETA_CRYSTAL_ITEM = ITEMS.register("zeta_crystal_item", () -> new CrystalItem(3));

    public static final RegistryObject<Item> CABLE_ITEM = ITEMS.register("cable_block", () -> new BlockItem(CABLE_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    
    //Blocks
    public static final RegistryObject<Block> EXAMPLE_ORE = BLOCKS.register("example_ore", () -> new ExampleOre()); 
    public static final RegistryObject<Block> ESSENCE_FURNACE = BLOCKS.register("essence_furnace", () -> new EssenceFurnace());
    public static final RegistryObject<Block> MAGICAL_DIRT_BLOCK = BLOCKS.register("magical_dirt_block", () -> new MagicalDirtBlock(false));
    public static final RegistryObject<Block> MAGICAL_GRASS_BLOCK = BLOCKS.register("magical_grass_block", () -> new MagicalDirtBlock(true));
     

    //Entities
    public static final RegistryObject<TileEntityType<EssenceFurnaceEntity>> ESSENCE_FURNACE_ENTITY = TILE_ENTITIES.register("essence_furnace_entity", () -> TileEntityType.Builder.create(EssenceFurnaceEntity::new, ESSENCE_FURNACE.get()).build(null));
    public static final RegistryObject<TileEntityType<CableEntity>> CABLE_ENTITY = TILE_ENTITIES.register("cable_entity", () -> TileEntityType.Builder.create(CableEntity::new, CABLE_BLOCK.get()).build(null));

    //Block Items
    public static final RegistryObject<Item> EXAMPLE_ORE_ITEM = ITEMS.register("example_ore", () -> new BlockItem(EXAMPLE_ORE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> ESSENCE_FURNACE_ITEM = ITEMS.register("essence_furnace", () -> new BlockItem(ESSENCE_FURNACE.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));

    public static final RegistryObject<Item> MAGICAL_DIRT_ITEM = ITEMS.register("magical_dirt_block", () -> new BlockItem(MAGICAL_DIRT_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    public static final RegistryObject<Item> MAGICAL_GRASS_ITEM = ITEMS.register("magical_grass_block", () -> new BlockItem(MAGICAL_GRASS_BLOCK.get(), new Item.Properties().group(ModItemGroup.MOD_ITEM_GROUP)));
    
    //Structures
    public static final DeferredRegister<Feature<?>> FEATURES = new DeferredRegister<Feature<?>>(
			ForgeRegistries.FEATURES, Technolution.ModID);
	
	public static IStructurePieceType MAGIC_FIELD_PIECE = MagicFieldPieces.Piece::new;

	public static final RegistryObject<MagicFieldStructure> MAGIC_FIELD_STRUCTURE = FEATURES.register("magic_field_structure",
			() -> new MagicFieldStructure(NoFeatureConfig::deserialize));

    public static void init(final IEventBus eventBus) {
		BLOCKS.register(eventBus);
		ITEMS.register(eventBus);
        TILE_ENTITIES.register(eventBus);
        CONTAINERS.register(eventBus);
        FEATURES.register(eventBus);
	}
}