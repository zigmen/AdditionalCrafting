package chibill.AdditionalCrafting;


import chibill.AdditionalCrafting.SpawnerStuff.Custom_PickAxe;
import chibill.AdditionalCrafting.SpawnerStuff.MobSpawnerBlock;
import chibill.AdditionalCrafting.developercapesapi.DeveloperCapesAPI;
import chibill.AdditionalCrafting.networking.PacketHandler;
import chibill.AdditionalCrafting.networking.Server_Loging_Handler;
import chibill.AdditionalCrafting.stairs.NewDiamondStairs;
import chibill.AdditionalCrafting.stairs.NewDirtStairs;
import chibill.AdditionalCrafting.stairs.NewGlassstairs;
import chibill.AdditionalCrafting.stairs.NewGoldstairs;
import chibill.AdditionalCrafting.stairs.NewIronstairs;
import chibill.AdditionalCrafting.stairs.NewLapisstairs;
import chibill.AdditionalCrafting.stairs.NewStonestairs;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Property;
import net.minecraftforge.event.EventBus;

@Mod(modid="AdditionalCrafting", name="Additional Crafting", version="1.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"AC_Chibill", "AC_Himehowareu", "AC_Loggin"}, packetHandler=PacketHandler.class)
public class Base
{
	public static final ItemStack NetherStone = new ItemStack(87, 1, 0);
	public int Block_Start_ID;
	public int Item_Start_ID;
	public int MobSpawner_ID;
	public static int Pick_ID;
	public int IronStairs;
	public int GlassStairs;
	public int GoldStairs;
	public int LapisStairs;
	public int DiamondStairs;
	public int StoneStairs;
	public int DirtStairs;
	public static boolean No_Internet;
	public static boolean Up_to_Date;
	public static Block CreeperSpawner;
	public static Block SkeletonSpawner;
	public static Block SpiderSpawner;
	public static Block ZombieSpawner;
	public static Block PigZombieSpawner;
	public static Block EndermanSpawner;
	public static Block BlazeSpawner;
	public static Block WitchSpawner;
	public static Block IronGolemSpawner;
	public static boolean Check;
	public static Configuration config;
	public static Logger ACLog = Logger.getLogger("Additional Crafting");
	@Mod.Instance("AdditionalCrafting")
	public static Base instance;

	@SidedProxy(clientSide="chibill.AdditionalCrafting.client.ClientProxy", serverSide="chibill.AdditionalCrafting.CommonProxy")
	public static CommonProxy proxy;

	@Mod.PreInit
	public void preInit(FMLPreInitializationEvent event)
	{
		ACLog.setParent(FMLLog.getLogger());
		ACLog.info("Starting AditionalCrafting 1.0.0");
		ACLog.info("Copyright (c) Chibill, 2013");


		ACLog.info("Starting confifuration of Additional Crafting!");
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		this.Block_Start_ID = config.get("block", 
				"Starting ID for all Blocks", 3050).getInt();
		this.Item_Start_ID = 
				(config.get("block", 
						"Starting ID for all Items", 5000).getInt() - 256);

		Check = config.get("general", "Enables the Update Checker", true).getBoolean(true);

		if (Check) {
			Update();
		}
		config.save();

		ACLog.info("Finish reading and prossesing the config for Additional Crafting!");
	}

	@Mod.Init
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		NetworkRegistry.instance().registerConnectionHandler(new Server_Loging_Handler());

		this.MobSpawner_ID = this.Block_Start_ID+7;
		this.Pick_ID = this.Item_Start_ID;
		this.IronStairs = this.Block_Start_ID;
		this.GlassStairs = (this.Block_Start_ID + 1);
		this.GoldStairs = (this.Block_Start_ID + 2);
		this.LapisStairs = (this.Block_Start_ID + 3);
		this.DiamondStairs = (this.Block_Start_ID + 4);
		this.DirtStairs = (this.Block_Start_ID + 5);
		this.StoneStairs = (this.Block_Start_ID + 6);

		Block DirtStair = new NewDirtStairs(this.DirtStairs);
		Block DiamondStair = new NewDiamondStairs(this.DiamondStairs);
		Block StoneStair = new NewStonestairs(this.StoneStairs);
		Block IronStair = new NewIronstairs(this.IronStairs);
		Block GlassStair = new NewGlassstairs(this.GlassStairs);
		Block LapisStair = new NewLapisstairs(this.LapisStairs);
		Block GoldStair = new NewGoldstairs(this.GoldStairs);

		MinecraftForge.setBlockHarvestLevel(DiamondStair, "pickaxe", 2);
		GameRegistry.registerBlock(DiamondStair, "DiamondStairs");
		LanguageRegistry.addName(DiamondStair, "Diamond Stairs");

		MinecraftForge.setBlockHarvestLevel(StoneStair, "pickaxe", 1);
		GameRegistry.registerBlock(StoneStair, "StoneStairs");
		LanguageRegistry.addName(StoneStair, "Smooth Stone Stairs");

		GameRegistry.registerBlock(DirtStair, "DirtStairs");
		LanguageRegistry.addName(DirtStair, "Dirt Stairs");

		MinecraftForge.setBlockHarvestLevel(IronStair, "pickaxe", 2);
		GameRegistry.registerBlock(IronStair, "IronStairs");
		LanguageRegistry.addName(IronStair, "Iron Stairs");
		GameRegistry.registerBlock(GlassStair, "GlassStairs");
		LanguageRegistry.addName(GlassStair, "Glass Stairs");

		MinecraftForge.setBlockHarvestLevel(LapisStair, "pickaxe", 2);
		GameRegistry.registerBlock(LapisStair, "LapisStairs");
		LanguageRegistry.addName(LapisStair, "Lapis Stairs");

		MinecraftForge.setBlockHarvestLevel(GoldStair, "pickaxe", 2);
		GameRegistry.registerBlock(GoldStair, "GoldStairs");
		LanguageRegistry.addName(GoldStair, "Gold Stairs");

		Item Spawner_Pick = new Custom_PickAxe(this.Pick_ID, EnumToolMaterial.WOOD, "Spawner_Pick");
		GameRegistry.registerItem(Spawner_Pick, "Spawner Pick");
		LanguageRegistry.addName(Spawner_Pick, "Spawner Collection Pick");

		CreeperSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 0).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(CreeperSpawner, "Creeper Spawner");
		GameRegistry.registerBlock(CreeperSpawner, "CreeperSpawner");
		SkeletonSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 1).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(SkeletonSpawner, "Skeleton Spawner");
		GameRegistry.registerBlock(SkeletonSpawner, "SkeletonSpawner");
		SpiderSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 2).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(SpiderSpawner, "Spider Spawner");
		GameRegistry.registerBlock(SpiderSpawner, "SpiderSpawneer");
		ZombieSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 3).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(ZombieSpawner, "Zombie Spawner");
		GameRegistry.registerBlock(ZombieSpawner ,"ZombieSpawner");
		PigZombieSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 4).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(PigZombieSpawner, "Zombie Pigman Spawner");
		GameRegistry.registerBlock(PigZombieSpawner, "PigZombieSpawners");
		EndermanSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 5).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(EndermanSpawner, "Enderman Spawner");
		GameRegistry.registerBlock(EndermanSpawner, "EndermanSpawner");
		BlazeSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 6).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(BlazeSpawner, "Blaze Spawner");
		GameRegistry.registerBlock(BlazeSpawner, "BlazeSpawner");
		WitchSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 7).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(WitchSpawner, "Witch Spawner");
		GameRegistry.registerBlock(WitchSpawner,"WitchSpawner");
		IronGolemSpawner = new MobSpawnerBlock(this.MobSpawner_ID, 8).setCreativeTab(CreativeTabs.tabBlock);
		LanguageRegistry.addName(IronGolemSpawner, "Iron Golem Spawner");
		GameRegistry.registerBlock(IronGolemSpawner, "IronGolemSpawner");
		ItemStack OakPlank = new ItemStack(5, 1, 0);
		ItemStack OakSlab = new ItemStack(126, 1, 0);
		ItemStack Oak = new ItemStack(17, 1, 0);

		ItemStack SprucePlank = new ItemStack(5, 1, 1);
		ItemStack SpruceSlab = new ItemStack(126, 1, 1);
		ItemStack Spruce = new ItemStack(17, 1, 1);

		ItemStack BirchPlank = new ItemStack(5, 1, 2);
		ItemStack BirchSlab = new ItemStack(126, 1, 2);
		ItemStack Birch = new ItemStack(17, 1, 2);

		ItemStack JunglePlank = new ItemStack(5, 1, 3);
		ItemStack JungleSlab = new ItemStack(126, 1, 3);
		ItemStack Jungle = new ItemStack(17, 1, 3);

		ItemStack Stone = new ItemStack(1, 1, 0);
		ItemStack StoneSlab = new ItemStack(44, 1, 0);

		ItemStack CobbleStone = new ItemStack(4, 1, 0);
		ItemStack CobbleStoneSlab = new ItemStack(44, 1, 3);

		ItemStack StoneBrick = new ItemStack(98, 1, 0);
		ItemStack StoneBrickSlab = new ItemStack(44, 1, 5);

		ItemStack SandStone = new ItemStack(24, 1, 0);
		ItemStack SandStoneSlab = new ItemStack(44, 1, 1);

		ItemStack Brick = new ItemStack(45, 1, 0);
		ItemStack BrickSlab = new ItemStack(44, 1, 4);

		ItemStack NetherBrick = new ItemStack(43, 1, 6);
		ItemStack NetherBrickSlabs = new ItemStack(44, 1, 6);

		ItemStack Obsidian = new ItemStack(49, 1, 0);
		ItemStack GunPowder = new ItemStack(289, 1, 0);

		ItemStack Egg = new ItemStack(344, 1, 0);

		ItemStack Bone = new ItemStack(352, 1, 0);

		ItemStack SpiderEye = new ItemStack(375, 1, 0);

		ItemStack RottenFlesh = new ItemStack(367, 1, 0);

		ItemStack GoldNugget = new ItemStack(371, 1, 0);

		ItemStack EnderPerl = new ItemStack(368, 1, 0);

		ItemStack FermentedeSpiderEye = new ItemStack(Item.fermentedSpiderEye);

		ItemStack BlazeRod = new ItemStack(369, 1, 0);

		ItemStack MagmaCream = new ItemStack(376, 1, 0);

		ItemStack SlimeBall = new ItemStack(42, 1, 0);

		ItemStack water = new ItemStack(326, 1, 0);

		ItemStack NetherStone = new ItemStack(87, 1, 0);

		ItemStack Iron = new ItemStack(265, 1, 0);

		ItemStack Glass = new ItemStack(20, 1, 0);

		ItemStack Lapis = new ItemStack(351, 1, 4);

		ItemStack Gold = new ItemStack(266, 1, 0);

		ItemStack CreeperEgg = new ItemStack(383, 1, 50);

		ItemStack SkeletonEgg = new ItemStack(383, 1, 51);

		ItemStack SpiderEgg = new ItemStack(383, 1, 52);

		ItemStack ZombieEgg = new ItemStack(383, 1, 54);

		ItemStack ZombiePigManEgg = new ItemStack(383, 1, 57);

		ItemStack EnderMenEgg = new ItemStack(383, 1, 58);

		ItemStack WitchEgg = new ItemStack(383, 1, 66);

		ItemStack BlazeEgg = new ItemStack(383, 1, 61);

		ItemStack IronGolemEgg = new ItemStack(383, 1, 99);

		GameRegistry.addRecipe(NetherBrick, new Object[] { "x ", "x ", 
				Character.valueOf('x'), NetherBrickSlabs });

		GameRegistry.addRecipe(OakPlank, new Object[] { "x ", "x ", 
				Character.valueOf('x'), OakSlab });

		GameRegistry.addRecipe(Oak, new Object[] { "x x", "x x", 
				Character.valueOf('x'), OakPlank });

		GameRegistry.addRecipe(SprucePlank, new Object[] { "x ", "x ", 
				Character.valueOf('x'), SpruceSlab });

		GameRegistry.addRecipe(Spruce, new Object[] { "x x", "x x", 
				Character.valueOf('x'), SprucePlank });

		GameRegistry.addRecipe(BirchPlank, new Object[] { "x ", "x ", 
				Character.valueOf('x'), BirchSlab });

		GameRegistry.addRecipe(Birch, new Object[] { "x x", "x x", 
				Character.valueOf('x'), BirchPlank });

		GameRegistry.addRecipe(JunglePlank, new Object[] { "x ", "x ", 
				Character.valueOf('x'), JungleSlab });

		GameRegistry.addRecipe(Jungle, new Object[] { "x x", "x x", 
				Character.valueOf('x'), JunglePlank });

		GameRegistry.addRecipe(Stone, new Object[] { "x ", "x ", 
				Character.valueOf('x'), StoneSlab });

		GameRegistry.addRecipe(StoneBrick, new Object[] { "x ", "x ", 
				Character.valueOf('x'), StoneBrickSlab });

		GameRegistry.addRecipe(Brick, new Object[] { "x ", "x ", 
				Character.valueOf('x'), BrickSlab });

		GameRegistry.addRecipe(CobbleStone, new Object[] { "x ", "x ", 
				Character.valueOf('x'), CobbleStoneSlab });

		GameRegistry.registerFuelHandler(new FuelHandler());

		GameRegistry.addRecipe(CreeperEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), GunPowder, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(SkeletonEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), Bone, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(SpiderEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), SpiderEye, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(ZombieEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), RottenFlesh, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(ZombiePigManEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), GoldNugget, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(EnderMenEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), EnderPerl, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(WitchEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), FermentedeSpiderEye, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(BlazeEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), BlazeRod, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(IronGolemEgg, new Object[] { "xxx", "yzy", "xxx", 
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), Iron, Character.valueOf('z'), Egg });

		GameRegistry.addRecipe(new ItemStack(CreeperSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), CreeperEgg });

		GameRegistry.addRecipe(new ItemStack(SkeletonSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), SkeletonEgg });

		GameRegistry.addRecipe(new ItemStack(SpiderSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), SpiderEgg });

		GameRegistry.addRecipe(new ItemStack(ZombieSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), ZombieEgg });

		GameRegistry.addRecipe(new ItemStack(PigZombieSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), ZombiePigManEgg });

		GameRegistry.addRecipe(new ItemStack(EndermanSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), EnderMenEgg });

		GameRegistry.addRecipe(new ItemStack(BlazeSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), BlazeEgg });

		GameRegistry.addRecipe(new ItemStack(WitchSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), WitchEgg });
		GameRegistry.addRecipe(new ItemStack(IronGolemSpawner), new Object[] { "xxx", "xyx", "xxx", 
			Character.valueOf('x'), Obsidian, Character.valueOf('y'), IronGolemEgg });

		GameRegistry.addRecipe(new ItemStack(IronStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), Iron });

		GameRegistry.addRecipe(new ItemStack(GlassStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), Glass });

		GameRegistry.addRecipe(new ItemStack(LapisStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), Lapis });

		GameRegistry.addRecipe(new ItemStack(GoldStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), Gold });

		GameRegistry.addRecipe(new ItemStack(DirtStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), new ItemStack(3, 1, 0) });

		GameRegistry.addRecipe(new ItemStack(StoneStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), Stone });

		GameRegistry.addRecipe(new ItemStack(DiamondStair, 4), new Object[] { "  x", " xx", "xxx", 
			Character.valueOf('x'), new ItemStack(Item.diamond) });

		GameRegistry.addRecipe(new ItemStack(Spawner_Pick), new Object[] { "xyx", " z ", " z ", 
			Character.valueOf('x'), new ItemStack(Item.rottenFlesh), Character.valueOf('y'), Iron, Character.valueOf('z'), new ItemStack(Item.stick) });


	}

	@Mod.PostInit
	public void postInit(FMLPostInitializationEvent event)
	{ 		
		DeveloperCapesAPI.getInstance().init("https://www.github.com/chibill/AdditionalCrafting/master/Capes_For_Devs/Capes.txt");
		ACLog.info("Additional Crafting has finished loading!!");
	}
	public static void Update()
	{
		try {
			URL url = new URL("https://raw.github.com/chibill/AdditionalCrafting/master/Version_Control/1.5.txt");

			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

			int str = in.read();
			if (in != null) {
				No_Internet = false;
				if (str==110) {
					Up_to_Date = true;
					ACLog.info("Additional Crafting up to date!");
				}
				else {
					Up_to_Date = false;
					ACLog.info("Additional Crafting is out of date for this verison of Minecraft!");
				}
			}
			in.close();
		} catch (MalformedURLException localMalformedURLException) {
		} catch (IOException e) {
			No_Internet = true;
			ACLog.info("Additional Crafting has no internet conection!");
		}
	}


}

