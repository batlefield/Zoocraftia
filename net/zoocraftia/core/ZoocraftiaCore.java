package net.zoocraftia.core;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.world.World;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.zoocraftia.api.Items;
import net.zoocraftia.api.Zoocraftia;
import net.zoocraftia.api.ZoocraftiaException;
import net.zoocraftia.core.listeners.BonemealEventListener;
import net.zoocraftia.core.listeners.EntitySpawnListener;
import net.zoocraftia.core.listeners.FillBucketListener;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ZoocraftiaCore {

	static Configuration config = new Configuration(new File(ZoocraftiaMain.proxy.getLocation(), "/Zoocraftia/Core/IDConfig.cfg"));
	
	public static int saltwaterRenderID;
	public static int plexiglassRenderID;
	
	public static int getOrCreateBlockID(String block, int id)
	{
		config.load();
		Property prop = config.getBlock(block, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Id for " + block + "is invalid!");
		}
		return prop.getInt();
	}
	
	public static int getOrCreatItemID(String item, int id)
	{
		config.load();
		Property prop = config.getItem(item, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Id for " + item + "is invalid!");
		}
		return prop.getInt();
	}
	
	public static int getOrCreatEntityID(String entity, int id)
	{
		config.load();
		Property prop = config.get("entity", entity, id);
		config.save();
		if(!prop.isIntValue())
		{
			throw new ZoocraftiaException("Id for " + entity + "is invalid!");
		}
		return prop.getInt();
	}
	
	public void addEventListener(Object o)
	{
		MinecraftForge.EVENT_BUS.register(o);
	}
	
	public void registerBlock(Block block, String name)
	{
		GameRegistry.registerBlock(block);
		Zoocraftia.addName(block, name);
	}
	
	
	public void initialize()
	{	
		//Entity registration
		EntityRegistry.registerGlobalEntityID(DartEntity.class, "Dart", EntityRegistry.findGlobalUniqueEntityId());
		EntityRegistry.registerModEntity(DartEntity.class, "Dart", 0, ZoocraftiaMain.INSTANCE, 128, 1, true);
		
		//Event listeners
		addEventListener(new EntitySpawnListener());
		addEventListener(new FillBucketListener());
		addEventListener(new BonemealEventListener());
		
		//Block initialization
		ZoocraftiaBlocks.saltwaterStill = (new ZoocraftiaSaltwaterStill(getOrCreateBlockID("Still saltwater", 3001), Material.water).setHardness(100F).setBlockName("StillSaltwater").setLightOpacity(2));
		ZoocraftiaBlocks.saltwaterMoving = (new ZoocraftiaSaltwaterFlowing(getOrCreateBlockID("Moving saltwater", 3000), Material.water).setHardness(100F).setBlockName("MovingSaltwater").setLightOpacity(2));
		ZoocraftiaBlocks.brownstone = (new DefaultBlock(getOrCreateBlockID("Brownstone", 3002), 6, Material.rock).setBlockName("brownstone").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep));
		ZoocraftiaBlocks.leaves = (new BlockLeaves(getOrCreateBlockID("Leaves", 3003), 0).setBlockName("leaves")).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundGrassFootstep).setBlockName("leaves");
		ZoocraftiaBlocks.caniferousGround = (new GroundBlock(getOrCreateBlockID("Caniferous", 3004), Material.grass)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("caniferous");
		ZoocraftiaBlocks.deciduousGround = (new GroundBlock(getOrCreateBlockID("Deciduous", 3005), Material.grass)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("deciduous");
		ZoocraftiaBlocks.savannahGround = (new GroundBlock(getOrCreateBlockID("Savannah", 3006), Material.grass)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("savannah");
		ZoocraftiaBlocks.tropicGround = (new GroundBlock(getOrCreateBlockID("Tropic", 3007), Material.grass)).setHardness(0.6F).setStepSound(Block.soundGrassFootstep).setBlockName("tropic");
		ZoocraftiaBlocks.mesa = (new GroundBlock(getOrCreateBlockID("Mesa", 3008), Material.grass)).setHardness(0.6F).setStepSound(Block.soundStoneFootstep).setBlockName("mesa");
		ZoocraftiaBlocks.sapling = (new BlockSapling(getOrCreateBlockID("Sapling", 3009)).setHardness(0.0F).setStepSound(Block.soundGrassFootstep).setBlockName("sapling"));
		ZoocraftiaBlocks.acorns = (new BlockAcorns(getOrCreateBlockID("Acorns", 3010), 14).setBlockName("acorns").setStepSound(Block.soundWoodFootstep).setHardness(0.0F).setCreativeTab(CreativeTabs.tabDecorations));
		ZoocraftiaBlocks.asphalt = (new DefaultBlock(getOrCreateBlockID("Asphalt", 3011), 7, Material.rock).setBlockName("asphalt").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep));
		ZoocraftiaBlocks.concrete = (new DefaultBlock(getOrCreateBlockID("Concrete", 3012), 8, Material.rock).setBlockName("concrete").setCreativeTab(CreativeTabs.tabBlock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep));
		ZoocraftiaBlocks.quicksand = (new BlockQuicksand(getOrCreateBlockID("Quicksand", 3013), 11)).setHardness(0.5F).setStepSound(Block.soundSandFootstep).setBlockName("quicksand").setCreativeTab(CreativeTabs.tabBlock);
		ZoocraftiaBlocks.plexiglass = (new BlockPlexiGlass(getOrCreateBlockID("Plexiglass", 3014), 12).setHardness(1.0F).setResistance(2000.0F).setStepSound(Block.soundGlassFootstep).setBlockName("plexiglass")).setCreativeTab(CreativeTabs.tabBlock);
		ZoocraftiaBlocks.plexiglassPane = (new BlockPlexiPane(getOrCreateBlockID("Plexipane", 3015), 12, 13, Material.glass).setHardness(1.0F).setResistance(2000.0F).setStepSound(Block.soundGlassFootstep).setBlockName("plexiglasspane"));
		ZoocraftiaBlocks.stoneFence = (new BlockFence(getOrCreateBlockID("StoneFence", 3016), 1, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("stoneFence"));
		ZoocraftiaBlocks.brownstoneFence = (new BlockFence(getOrCreateBlockID("BrownFence", 3017), 6, Material.rock).setHardness(1.5F).setResistance(10.0F).setStepSound(Block.soundStoneFootstep).setBlockName("brownstoneFence"));
		ZoocraftiaBlocks.goldFence = (new BlockFence(getOrCreateBlockID("GoldFence", 3018), 23, Material.iron).setHardness(3.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockGoldFence"));
		ZoocraftiaBlocks.obsidianFence = (new BlockFence(getOrCreateBlockID("ObsidianFence", 3019), 37, Material.rock).setHardness(50.0F).setResistance(2000.0F).setStepSound(Block.soundStoneFootstep).setBlockName("obsidianFence"));
		
		//Saltwater id check
		if(ZoocraftiaBlocks.saltwaterStill.blockID - 1 != ZoocraftiaBlocks.saltwaterMoving.blockID)
		{
			throw new ZoocraftiaException("Still saltwater ID must be moving saltwater ID + 1");
		}
		
		//Block registration
		registerBlock(ZoocraftiaBlocks.brownstone, "Brownstone");
		registerBlock(ZoocraftiaBlocks.saltwaterMoving, "Moving saltwater");
		registerBlock(ZoocraftiaBlocks.saltwaterStill, "Still saltwater");
		registerBlock(ZoocraftiaBlocks.caniferousGround, "Caniferous Dirt");
		registerBlock(ZoocraftiaBlocks.deciduousGround, "Deciduous Dirt");
		registerBlock(ZoocraftiaBlocks.savannahGround, "Savannah Dirt");
		registerBlock(ZoocraftiaBlocks.tropicGround, "Tropic Dirt");
		registerBlock(ZoocraftiaBlocks.mesa, "Mesa");
		registerBlock(ZoocraftiaBlocks.acorns, "Acorns");
		registerBlock(ZoocraftiaBlocks.asphalt, "Asphalt");
		registerBlock(ZoocraftiaBlocks.concrete, "Concrete");
		registerBlock(ZoocraftiaBlocks.quicksand, "Quicksand");
		registerBlock(ZoocraftiaBlocks.plexiglass, "Plexi glass");
		registerBlock(ZoocraftiaBlocks.plexiglassPane, "Plexi glass pane");
		registerBlock(ZoocraftiaBlocks.stoneFence, "Stone Fence");
		registerBlock(ZoocraftiaBlocks.brownstoneFence, "Browntone Fence");
		registerBlock(ZoocraftiaBlocks.goldFence, "Gold Fence");
		registerBlock(ZoocraftiaBlocks.obsidianFence, "Obsidian Fence");
		
		//Block with metadata registration
		GameRegistry.registerBlock(ZoocraftiaBlocks.leaves, net.zoocraftia.core.ItemLeaves.class);
		GameRegistry.registerBlock(ZoocraftiaBlocks.sapling, net.zoocraftia.core.ItemSapling.class);
		
		//Item initialization
		ZoocraftiaItems.meat = new ItemMeat(getOrCreatItemID("Meat", 12000)).setItemName("meat");
		ZoocraftiaItems.dart = new DefaultItem(getOrCreatItemID("Dart", 12001), false).setItemName("Dart").setIconIndex(18).setCreativeTab(CreativeTabs.tabCombat);
		ZoocraftiaItems.tagGun = new ItemTagGun(getOrCreatItemID("TagGun", 12002)).setItemName("TagGun").setIconIndex(7).setCreativeTab(CreativeTabs.tabCombat);
		ZoocraftiaItems.saltwaterBucket = new ZoocraftiaBucketSaltwater(getOrCreatItemID("SaltwaterBucket", 12003), ZoocraftiaBlocks.saltwaterMoving.blockID).setItemName("SaltwaterBucket").setIconIndex(1).setCreativeTab(CreativeTabs.tabMisc);
		ZoocraftiaItems.coin = new ZoocraftiaCoin(getOrCreatItemID("Coin", 12004)).setItemName("Coin").setCreativeTab(CreativeTabs.tabMisc);
		
		//Item naming
		Zoocraftia.addName(ZoocraftiaItems.saltwaterBucket, "Saltwater Bucket");
		Zoocraftia.addName(ZoocraftiaItems.dart, "Dart");
		Zoocraftia.addName(ZoocraftiaItems.tagGun, "Tag Gun");
		
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 0), "Raw herbivore meat");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 1), "Cooked herbivore meat");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 2), "Raw carnivore meat");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 3), "Cooked carnivore meat");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 4), "Raw omnivore meat");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.meat, 1, 5), "Cooked omnivore meat");
		
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.leaves, 1, 0), "Blue pine leaves");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.leaves, 1, 1), "Deciduous leaves");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.leaves, 1, 2), "Deciduous leaves");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.leaves, 1, 3), "Deciduous leaves");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.leaves, 1, 4), "Acacia leaves");
		
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.sapling, 1, 0), "Acacia sapling");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.sapling, 1, 1), "Blue pine sapling");
		Zoocraftia.addName(new ItemStack(ZoocraftiaBlocks.sapling, 1, 2), "Deciduous sapling");
		
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.coin, 1, 0), "Golden coin");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.coin, 1, 1), "Silver coin");
		Zoocraftia.addName(new ItemStack(ZoocraftiaItems.coin, 1, 2), "Bronze coin");
		
		//other registrations, initializations, etc.
		BlockDispenser.dispenseBehaviorRegistry.putObject(ZoocraftiaItems.dart, new BehaviorDartDispense(MinecraftServer.getServer()));
		LiquidContainerRegistry.registerLiquid(new LiquidContainerData(new LiquidStack(ZoocraftiaBlocks.saltwaterStill, LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(ZoocraftiaItems.saltwaterBucket), new ItemStack(Item.bucketEmpty)));
		
		//Normal recipes
		new ZoocraftiaRecipes();
		
		//Smelting
		FurnaceRecipes.smelting().addSmelting(ZoocraftiaItems.meat.shiftedIndex, 0, new ItemStack(ZoocraftiaItems.meat, 1, 1), 0.35F);
		FurnaceRecipes.smelting().addSmelting(ZoocraftiaItems.meat.shiftedIndex, 2, new ItemStack(ZoocraftiaItems.meat, 1, 3), 0.35F);
		FurnaceRecipes.smelting().addSmelting(ZoocraftiaItems.meat.shiftedIndex, 4, new ItemStack(ZoocraftiaItems.meat, 1, 5), 0.35F);
		
		//Test recipes
		if(World.class.getSimpleName().equalsIgnoreCase("world"))
		{
			ModLoader.addRecipe(new ItemStack(Items.getItem("meat")), new Object[]{
				"X", Character.valueOf('X'), Block.dirt
			});
		}
	}
}
