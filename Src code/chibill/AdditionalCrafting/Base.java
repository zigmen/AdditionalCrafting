package chibill.AdditionalCrafting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import chibill.AdditionalCrafting.stairs.*;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStep;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.FMLCommonHandler;
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



@Mod(modid="AdditionalCrafting", name="AdditionalCrafting", version="1.0.0")


@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class Base {
	

	public static final ItemStack NetherStone = new ItemStack(87,1,0);
	public int Block_Start_ID;
	
	public int Item_Start_ID;
	
	public int MobSpawner_ID;
	
	public int Pick_ID;
	
	public int IronStairs;
	public int GlassStairs;
	public int GoldStairs;
	public int LapisStairs;
	public int DiamondStairs;
	public int StoneStairs;
	public int DirtStairs;
	public static boolean No_Internet;
	public static boolean Up_to_Date;
	
	public static Item CreeperSpawner;
	public static Item SkeletonSpawner;
	public static Item SpiderSpawner;
	public static Item ZombieSpawner;
	public static Item PigZombieSpawner;
	public static Item EndermanSpawner;
	public static Item BlazeSpawner;
	public static Item WitchSpawner;
	public static Item IronGolemSpawner;
	
	public static boolean Check; 
	
	public static Configuration config;
	

	// The instance of your mod that Forge uses.
	@Instance("AdditionalCrafting")
	public static Base instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="chibill.AdditionalCrafting.client.ClientProxy", serverSide="chibill.AdditionalCrafting.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
			System.out.println("[AdditionalCrafting] Starting confifuration of Additional Crafting!");
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
				
		        config.load();
		        boolean wasRead = true;
		        Block_Start_ID = config.get(Configuration.CATEGORY_BLOCK,
		                "Starting ID for all Blocks", 3050).getInt();
		        Item_Start_ID = config.get(Configuration.CATEGORY_BLOCK,
		                "Starting ID for all Items", 5000).getInt() - 256;
		       
		        Check = config.get(Configuration.CATEGORY_GENERAL, "Enables the Update Checker", true).getBoolean(true);
		        
		        if(Check){
		        Update();
		        }
		        if (!wasRead)
		        {
		          config.save();
		        }
		     

	    System.out.println("[AdditionalCrafting] Finish reading and prossesing the config for Additional Crafting!");
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		NetworkRegistry.instance().registerConnectionHandler(new ChatHandler());
		// Stairs
			MobSpawner_ID = Item_Start_ID;
			Pick_ID = Item_Start_ID + 10;
			
		   IronStairs = Block_Start_ID;
		    GlassStairs = Block_Start_ID + 1;
		    GoldStairs = Block_Start_ID + 2;
		    LapisStairs = Block_Start_ID + 3;
		    DiamondStairs = Block_Start_ID + 4;
		    DirtStairs = Block_Start_ID + 5;
		    StoneStairs = Block_Start_ID + 6;
		
		Block DirtStair = new NewDirtStairs(DirtStairs);
		Block DiamondStair = new NewDiamondStairs(DiamondStairs);
		Block StoneStair = new NewStonestairs(StoneStairs);
		Block IronStair = new NewIronstairs(IronStairs);
		Block GlassStair = new NewGlassstairs(GlassStairs);
		Block LapisStair = new NewLapisstairs(LapisStairs);
		Block GoldStair = new NewGoldstairs(GoldStairs);
		
		
		MinecraftForge.setBlockHarvestLevel(DiamondStair, "pickaxe", 2);
		GameRegistry.registerBlock(DiamondStair,"DiamondStairs");
		LanguageRegistry.addName(DiamondStair, "Diamond Stairs");
		
		MinecraftForge.setBlockHarvestLevel(StoneStair, "pickaxe", 1);
		GameRegistry.registerBlock(StoneStair,"StoneStairs");
		LanguageRegistry.addName(StoneStair, "Smooth Stone Stairs");
		
		GameRegistry.registerBlock(DirtStair,"DirtStairs");
		LanguageRegistry.addName(DirtStair, "Dirt Stairs");
		
		MinecraftForge.setBlockHarvestLevel(IronStair, "pickaxe", 2);
		GameRegistry.registerBlock(IronStair,"IronStairs");
		LanguageRegistry.addName(IronStair, "Iron Stairs");
		   
		   
		GameRegistry.registerBlock(GlassStair,"GlassStairs");
		LanguageRegistry.addName(GlassStair, "Glass Stairs");
	
		   
		MinecraftForge.setBlockHarvestLevel(LapisStair, "pickaxe", 2);
		GameRegistry.registerBlock(LapisStair,"LapisStairs");
		LanguageRegistry.addName(LapisStair, "Lapis Stairs");
		  
		MinecraftForge.setBlockHarvestLevel(GoldStair, "pickaxe", 2);
		GameRegistry.registerBlock(GoldStair,"GoldStairs");
		LanguageRegistry.addName(GoldStair, "Gold Stairs");
		 
		Item  Spawner_Pick = (new Custom_PickAxe(Pick_ID, EnumToolMaterial.WOOD, "Spawner_Pick"));
		GameRegistry.registerItem(Spawner_Pick,"Spawner Pick");
		LanguageRegistry.addName(Spawner_Pick, "Spawner Collection Pick");
		 //New Spawners
		 CreeperSpawner = new MobSpawnerItem(MobSpawner_ID,0).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(CreeperSpawner,"Creeper Spawner");
		 
		 SkeletonSpawner = new MobSpawnerItem(MobSpawner_ID,1).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(SkeletonSpawner,"Skeleton Spawner");
		 
		 SpiderSpawner = new MobSpawnerItem(MobSpawner_ID,2).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(SpiderSpawner,"Spider Spawner");
		 
		 ZombieSpawner = new MobSpawnerItem(MobSpawner_ID,3).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(ZombieSpawner,"Zombie Spawner");
		 
		 PigZombieSpawner = new MobSpawnerItem(MobSpawner_ID,4).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(PigZombieSpawner,"Zombie Pigman Spawner");
		 
		 EndermanSpawner = new MobSpawnerItem(MobSpawner_ID,5).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(EndermanSpawner,"Enderman Spawner");
		
		 BlazeSpawner = new MobSpawnerItem(MobSpawner_ID,6).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(BlazeSpawner,"Blaze Spawner");
		 
		 WitchSpawner = new MobSpawnerItem(MobSpawner_ID,7).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(WitchSpawner,"Witch Spawner");
		 
		 IronGolemSpawner = new MobSpawnerItem(MobSpawner_ID,8).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(IronGolemSpawner,"Iron Golem Spawner");
		 
		 
		 //Woods
		//Oak
		 ItemStack OakPlank = new ItemStack(5, 1, 0);	
		 ItemStack OakSlab = new ItemStack(126, 1, 0);
		 ItemStack Oak = new ItemStack(17, 1, 0);
		
		//Spruce
		 ItemStack SprucePlank = new ItemStack(5, 1, 1);	
		 ItemStack SpruceSlab = new ItemStack(126, 1, 1);
		 ItemStack Spruce = new ItemStack(17, 1, 1);
		
		//Birch
		 ItemStack BirchPlank = new ItemStack(5, 1, 2);	
		 ItemStack BirchSlab = new ItemStack(126, 1, 2);	
		 ItemStack Birch = new ItemStack(17, 1, 2);
		
		//Jungle
		 ItemStack JunglePlank = new ItemStack(5, 1, 3);	
		 ItemStack JungleSlab = new ItemStack(126, 1, 3);	
		 ItemStack Jungle = new ItemStack(17, 1, 3);
		
		//Stone
		 ItemStack Stone = new ItemStack(1, 1, 0);	
		 ItemStack StoneSlab = new ItemStack(44, 1, 0);
		
		//Cobble
		 ItemStack CobbleStone = new ItemStack(4, 1, 0);	
		 ItemStack CobbleStoneSlab = new ItemStack(44, 1, 3);
		
		//StoneBrick
		 ItemStack StoneBrick = new ItemStack(98, 1, 0);	
		 ItemStack StoneBrickSlab = new ItemStack(44, 1, 5);
		
		//SandStone
		 ItemStack SandStone= new ItemStack(24, 1, 0);	
		 ItemStack SandStoneSlab = new ItemStack(44, 1, 1);
		
		//Bricks
		 ItemStack Brick = new ItemStack(45, 1, 0);	
		 ItemStack BrickSlab = new ItemStack(44, 1, 4);
		 
		//NetherBrick
		 ItemStack NetherBrick = new ItemStack(43, 1, 6);	
		 ItemStack NetherBrickSlabs = new ItemStack(44, 1, 6);
		
		//Obsidian
		 ItemStack Obsidian = new ItemStack(49,1,0);
		
		//GunPowder
		 ItemStack GunPowder = new ItemStack(289,1,0);
		
		//Egg
		 ItemStack Egg = new ItemStack(344,1,0);
		
		//Bone
		 ItemStack Bone = new ItemStack(352,1,0);
		
		//spidereye
		 ItemStack SpiderEye = new ItemStack(375,1,0);
		
		//RottenFlesh
		 ItemStack RottenFlesh = new ItemStack(367,1,0);
		 
		 //GlodTooth
		 ItemStack GoldNugget = new ItemStack(371,1,0);
		 
		 //EnderPerl
		 ItemStack EnderPerl = new ItemStack(368,1,0);
		 
		//Fermentedspidereye
		 ItemStack FermentedeSpiderEye = new ItemStack(341,1,0);
		 
		 //BlazeRod
		 ItemStack BlazeRod = new ItemStack(369,1,0);
		 
		 //Magmacream
		 ItemStack MagmaCream = new ItemStack(376,1,0);

		 //SlimeBall
		 ItemStack SlimeBall = new ItemStack(42,1,0);
		 
		//Water
		 ItemStack water = new ItemStack(326,1,0);
		 
		 
		 //NetherStone
		 ItemStack NetherStone = new ItemStack(87,1,0);
		 
		//Iron
		 ItemStack Iron = new ItemStack(265,1,0);
		 
		//Glass
		 ItemStack Glass = new ItemStack(20,1,0);
		 
		//Lapis
		 ItemStack Lapis = new ItemStack(351,1,4);
		 
		 
		//Gold
		 ItemStack Gold = new ItemStack(266,1,0);
		  
		//SpawnEggs
		 ItemStack CreeperEgg = new ItemStack(383, 1, 50);	
		
		 ItemStack SkeletonEgg = new ItemStack(383, 1, 51);
		
		 ItemStack SpiderEgg = new ItemStack(383, 1, 52);
		
		 ItemStack ZombieEgg = new ItemStack(383, 1, 54);
		
		 ItemStack ZombiePigManEgg = new ItemStack(383, 1, 57);
		
		 ItemStack EnderMenEgg = new ItemStack(383, 1, 58);
		
		 ItemStack WitchEgg = new ItemStack(383, 1, 66);
		
		 ItemStack BlazeEgg = new ItemStack(383, 1, 61);
		
		 ItemStack IronGolemEgg = new ItemStack(383, 1, 99);
			
	
		 
				 
				
		
		
		
		 
		 
		//Crafting
		 

		//Oak slab to plank
		GameRegistry.addRecipe(NetherBrick, "x ", "x ", 
			        'x', NetherBrickSlabs);
		
		//Oak slab to plank
		GameRegistry.addRecipe(OakPlank, "x ", "x ", 
		        'x', OakSlab);
		
		//Oak Plank to Oak
		GameRegistry.addRecipe(Oak, "x x", "x x", 
		        'x',OakPlank);
		
		//Spruce slab to plank
		GameRegistry.addRecipe(SprucePlank, "x ", "x ", 
		       'x', SpruceSlab);
		
		//Spruce Plank to Spruce
		GameRegistry.addRecipe(Spruce, "x x", "x x", 
		        'x',SprucePlank);
		
		//Birch slab to plank
		GameRegistry.addRecipe(BirchPlank, "x ", "x ", 
				'x', BirchSlab);
		
		//Birch Plank to Birch
		GameRegistry.addRecipe(Birch, "x x", "x x", 
		        'x',BirchPlank);
		
		//Jungle slab to plank
		GameRegistry.addRecipe(JunglePlank, "x ", "x ", 
		       'x', JungleSlab);
		
		//Jungle Plank to Jungle
		GameRegistry.addRecipe(Jungle, "x x", "x x", 
				'x',JunglePlank);
		
		//Stone slab to Stone
		GameRegistry.addRecipe(Stone, "x ", "x ",
				'x', StoneSlab);
	
		//StoneBrick slab to StoneBrick
		GameRegistry.addRecipe(StoneBrick, "x ", "x ", 
		       'x', StoneBrickSlab);
		
		//Brick slab to Brick
		GameRegistry.addRecipe(Brick, "x ", "x ", 
		       'x', BrickSlab);
		
		//CobbleStoneSlab to  CobbleStone
		GameRegistry.addRecipe(CobbleStone, "x ", "x ", 
			'x',CobbleStoneSlab);
		
		
		//Burn able
		GameRegistry.registerFuelHandler(new FuelHandler());

		
		//SpawnEggs
		
			//Creeper
			GameRegistry.addRecipe(CreeperEgg, "xxx", "yzy","xxx", 
				'x',Obsidian,'y',GunPowder,'z',Egg);
			//Skeleton
			GameRegistry.addRecipe(SkeletonEgg, "xxx", "yzy","xxx", 
				'x',Obsidian,'y',Bone,'z',Egg);
			//Spider
			GameRegistry.addRecipe(SpiderEgg, "xxx", "yzy","xxx", 
				'x',Obsidian,'y',SpiderEye,'z',Egg);
			//Zombie
			GameRegistry.addRecipe( ZombieEgg, "xxx", "yzy","xxx", 
				'x',Obsidian,'y',RottenFlesh,'z',Egg);
			//Zombie Pig man
			GameRegistry.addRecipe( ZombiePigManEgg, "xxx", "yzy","xxx", 
					'x',Obsidian,'y',GoldNugget,'z',Egg);
			//EnderMan
			GameRegistry.addRecipe( EnderMenEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',EnderPerl,'z',Egg);
			
			//CaveSpider
			GameRegistry.addRecipe(WitchEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',FermentedeSpiderEye,'z',Egg);
			
			//Blaze
			GameRegistry.addRecipe(BlazeEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',BlazeRod,'z',Egg);
			
			
			//Slime
			GameRegistry.addRecipe(IronGolemEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',Iron,'z',Egg);
		
			//Spawners
			
			//Creeper
			GameRegistry.addRecipe(new ItemStack(CreeperSpawner), "xxx", "xyx","xxx", 
				'x',Obsidian,'y',CreeperEgg);
			//Skeleton
			GameRegistry.addRecipe(new ItemStack(SkeletonSpawner), "xxx", "xyx","xxx", 
				'x',Obsidian,'y',SkeletonEgg);
			//Spider
			GameRegistry.addRecipe(new ItemStack(SpiderSpawner), "xxx", "xyx","xxx", 
				'x',Obsidian,'y',SpiderEgg);
			//Zombie
			GameRegistry.addRecipe(new ItemStack(ZombieSpawner), "xxx", "xyx","xxx", 
				'x',Obsidian,'y',ZombieEgg);
			//Zombie Pig man
			GameRegistry.addRecipe( new ItemStack(PigZombieSpawner), "xxx", "xyx","xxx", 
					'x',Obsidian,'y',ZombiePigManEgg);
			//EnderMan
			GameRegistry.addRecipe( new ItemStack(EndermanSpawner) , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',EnderMenEgg);
			
			//CaveSpider
			GameRegistry.addRecipe(new ItemStack(BlazeSpawner) , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',BlazeEgg);
			
			//Blaze
			GameRegistry.addRecipe(new ItemStack(WitchSpawner) , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',WitchEgg);
			GameRegistry.addRecipe(new ItemStack(IronGolemSpawner) , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',IronGolemEgg);
		
			
			//Stiars Iron
			GameRegistry.addRecipe(new ItemStack(IronStair,4),"  x", " xx","xxx", 
					'x',Iron);
			
			//Stiars Glass
			GameRegistry.addRecipe(new ItemStack(GlassStair,4),"  x", " xx","xxx", 
					'x',Glass);
			
			//Stiars Lapis
			GameRegistry.addRecipe(new ItemStack(LapisStair,4),"  x", " xx","xxx", 
					'x',Lapis);
			
			//Stiars Gold
			GameRegistry.addRecipe(new ItemStack(GoldStair,4),"  x", " xx","xxx", 
					'x',Gold);
	
			//Stiars Dirt
			GameRegistry.addRecipe(new ItemStack(DirtStair,4),"  x", " xx","xxx", 
					'x',new ItemStack(3,1,0));
			
			//Stiars Stone
			GameRegistry.addRecipe(new ItemStack(StoneStair,4),"  x", " xx","xxx", 
					'x',Stone);
			
			//Stiars Diamond
			GameRegistry.addRecipe(new ItemStack(DiamondStair,4),"  x", " xx","xxx", 
					'x',new ItemStack(Item.diamond));
			
			GameRegistry.addRecipe(new ItemStack(Spawner_Pick),"xyx", " z "," z ", 
					'x',new ItemStack(Item.rottenFlesh),'y',Iron,'z',new ItemStack(Item.stick));
	}
  


	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		 
		
	System.out.println("[AdditionalCrafting] Additional Crafting has finished loading!!");
	}
	public static void Update(){
		  {
			try {
		        URL url = new URL("https://raw.github.com/chibill/AdditionalCrafting/master/Version_Control/1.5.txt");

		        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		        int str = in.read();
		        int str1 = in.read();
		        int str2 = in.read();
		        str1 = in.read();
		        int str3 = in.read();
		        if (in != null) {
		          No_Internet = false;
		          if ((str == 49) && (str2 == 48) && (str3 == 48)) {
		           Up_to_Date = true;
		            System.out.println("[AdditionalCrafting] Additional Crafting up to date!");
		          }
		          else {
		            System.out.println("[AdditionalCrafting] Additional Crafting is out of date for this verison of Minecraft!");
		          }
		        }
		        in.close();
		      } catch (MalformedURLException e) {
		      } catch (IOException e) {
		        No_Internet = true;
		        System.out.println("[AdditionalCrafting] Additional Crafting has no internet conection!");
		      }
		  }
	}
}
	
