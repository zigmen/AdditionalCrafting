package chibill.additionalcrafting;



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
import java.net.URLClassLoader;
import java.util.logging.Logger;

import chibill.additionalcrafting.enchant.*;
import chibill.additionalcrafting.events.*;
import chibill.additionalcrafting.networking.*;
import chibill.additionalcrafting.spawners.*;
import chibill.additionalcrafting.stairs.NewStairs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
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

@Mod(modid="additionalcrafting", name="Additional Crafting", version="1.1.0")
@NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"AC_Chibill", "AC_Himehowareu", "AC_Loggin"}, packetHandler=PacketHandler.class)
public class Base
{
	public static final ItemStack NetherStone = new ItemStack(87, 1, 0);
	ItemStack Obsidian = new ItemStack(49, 1, 0);
	public int MobSpawner_ID;
	private int Spawner_Pick_ID;
	private int IronStairs;
	private int GlassStairs;
	private int GoldStairs;
	private int LapisStairs;
	private int DiamondStairs;
	private int DirtStairs;
	private int StoneStairs;
	public static int Enchant_ID;
	public static boolean No_Internet;
	public static boolean Up_to_Date;
	public static Block Spawner;
	public static Block DirtStair;
	public static Block GlassStair;
	public static Block LapisStair;
	public static Block GoldStair;
	public static Block DiamondStair;
	public static Block StoneStair;
	public static Block IronStair;
	public static boolean Check;
	public static Item Spawner_Pick;
	public static Configuration config;

	ItemStack CreeperEgg = new ItemStack(383, 1, 50);

	ItemStack SkeletonEgg = new ItemStack(383, 1, 51);

	ItemStack SpiderEgg = new ItemStack(383, 1, 52);

	ItemStack ZombieEgg = new ItemStack(383, 1, 54);

	ItemStack ZombiePigManEgg = new ItemStack(383, 1, 57);

	ItemStack EnderMenEgg = new ItemStack(383, 1, 58);

	ItemStack WitchEgg = new ItemStack(383, 1, 66);

	ItemStack BlazeEgg = new ItemStack(383, 1, 61);

	ItemStack IronGolemEgg = new ItemStack(383, 1, 99);

	ItemStack[] Stairs = new ItemStack[7];
	ItemStack[] Eggs = {CreeperEgg,SkeletonEgg,SpiderEgg,ZombieEgg,ZombiePigManEgg,EnderMenEgg,WitchEgg,BlazeEgg,IronGolemEgg};


	private static final String[] multiBlockNames = { "Creeper Spawner","Skeleton Spawner","Spider Spawner","Zombie Spawner","PigZombie Spawner","Enderman Spawner","Blaze Spawner","Witch Spawner","VillagerGolem Spawner"};
	public static Logger ACLog = Logger.getLogger("Additional Crafting");
	ItemStack[] StairMat = {new ItemStack(Block.dirt),new ItemStack(Item.diamond),new ItemStack(Block.stone),new ItemStack(Item.ingotIron),new ItemStack(Block.glass),new ItemStack(Item.dyePowder,1,4),new ItemStack(Item.ingotGold)};

	@Mod.Instance("additionalcrafting")
	public static Base instance;

	@SidedProxy(clientSide="chibill.additionalcrafting.client.ClientProxy", serverSide="chibill.additionalcrafting.CommonProxy")
	public static CommonProxy proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		ACLog.setParent(FMLLog.getLogger());
		ACLog.info("Starting AditionalCrafting 1.1.0");
		ACLog.info("Copyright (c) Chibill, 2013");


		ACLog.info("Starting confifuration of Additional Crafting!");
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());

		config.load();

		this.IronStairs  = config.get("block", 
				"Iron Stairs", 3051).getInt();
		this.GlassStairs  = config.get("block", 
				"Glass Stairs", 3052).getInt();
		this.GoldStairs   = config.get("block", 
				"Gold Stairs", 3053).getInt();
		this.LapisStairs = config.get("block", 
				"Lapis Stairs", 3054).getInt();
		this.DiamondStairs  = config.get("block", 
				"Diamond Stairs", 3055).getInt();
		this.DirtStairs  = config.get("block", 
				"Dirt Stairs", 3056).getInt();
		this.StoneStairs  = config.get("block", 
				"Stone Stairs", 3057).getInt();

		this.MobSpawner_ID = config.get("block", 
				"This and the next 9 Block Ids are for Spawners", 3058).getInt();





		Check = config.get("general", "Enables the Update Checker", true).getBoolean(true);

		this.Enchant_ID = (config.get("general","Enchantment ID",52).getInt());
		if(this.Enchant_ID <52){
			throw new IllegalArgumentException("Echantment ID for the Spawner Collection I in Additional Crafting is too low!");
		}


		if (Check) {
			Update();
		}
		config.save();

		ACLog.info("Finish reading and prossesing the config for Additional Crafting!");



		MinecraftForge.EVENT_BUS.register(new Enchant_Handler());
		//MinecraftForge.EVENT_BUS.register(new Cape_Handler());


		DirtStair = new NewStairs(this.DirtStairs,Block.dirt);
		DiamondStair = new NewStairs(this.DiamondStairs,Block.blockDiamond);
		StoneStair = new NewStairs(this.StoneStairs,Block.stone);
		IronStair = new NewStairs(this.IronStairs,Block.blockIron);
		GlassStair = new NewStairs(this.GlassStairs,Block.glass);
		LapisStair = new NewStairs(this.LapisStairs,Block.blockLapis);
		GoldStair = new NewStairs(this.GoldStairs,Block.blockGold);
		Stairs[0]=new ItemStack(DirtStair);
		Stairs[1]=new ItemStack(DiamondStair);
		Stairs[2]=new ItemStack(StoneStair);
		Stairs[3] = new ItemStack(IronStair);
		Stairs[4]=new ItemStack(GlassStair);
		Stairs[5]= new ItemStack(LapisStair);
		Stairs[6]= new ItemStack(GoldStair);

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

		Spawner = new MobSpawnerBlock(this.MobSpawner_ID);

		GameRegistry.registerBlock(Spawner, SpawnerItemBlock.class,"Spawner");

		for (int ix = 0; ix < 9; ix++) {
			ItemStack multiBlockStack = new ItemStack(Spawner, 1, ix);

			GameRegistry.addRecipe(multiBlockStack,"XXX" ,"XYX","XXX",'X', Obsidian, 'Y',Eggs[multiBlockStack.getItemDamage()]);
			LanguageRegistry.addName(multiBlockStack, multiBlockNames[multiBlockStack.getItemDamage()]);
		}

		for(int ix = 0;ix<Stairs.length-1;ix++)
		{
			GameRegistry.addRecipe(Stairs[ix],"  X"," XX","XXX",'X',StairMat[ix]);

		}
	}

	@Mod.EventHandler
	public void load(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		NetworkRegistry.instance().registerConnectionHandler(new Server_Loging_Handler());

		Enchantment Spawner_Enchant = new Spawner_Pick_Enchant(Enchant_ID, 1);
		Enchantment.addToBookList(Spawner_Enchant);

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
				Character.valueOf('x'), Obsidian, Character.valueOf('y'), new ItemStack(Block.blockIron), Character.valueOf('z'), Egg });


	}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{ 		

		ACLog.info("Additional Crafting has finished loading!!");
	}
	public static void Update()
	{
		try {
			URL url = new URL("https://raw.github.com/chibill/additionalcrafting/master/Version_Control/1.6.txt");
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
			String str = in.readLine();
			if (in != null) {
				No_Internet = false;
				System.out.println(str);
				if (str.equals("140")) {
					Up_to_Date = true;
					ACLog.finest("Additional Crafting up to date!");
				}
				else {
					Up_to_Date = false;
					ACLog.warning("Additional Crafting is out of date for this verison of Minecraft!");
				}
			}
			in.close();
		} catch (MalformedURLException localMalformedURLException) {
		} catch (IOException e) {
			No_Internet = true;
			ACLog.severe("Additional Crafting has no internet conection!");
		}
	}
}

