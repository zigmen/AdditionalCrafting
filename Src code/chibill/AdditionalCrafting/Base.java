package chibill.AdditionalCrafting;

import java.io.File;

import chibill.AdditionalCrafting.stairs.NewGlassstairs;
import chibill.AdditionalCrafting.stairs.NewGoldstairs;
import chibill.AdditionalCrafting.stairs.NewIronstairs;
import chibill.AdditionalCrafting.stairs.NewLapisstairs;
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
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;



@Mod(modid="AdditionalCrafting", name="Additional Crafting", version="1.0.0")


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
	public static Configuration config;
	

	// The instance of your mod that Forge uses.
	@Instance("AdditionalCrafting")
	public static Base instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="chibill.AdditionalCrafting.client.ClientProxy", serverSide="chibill.AdditionalCrafting.CommonProxy")
	public static CommonProxy proxy;
	public static Item CreeperSpawner;
	public static Item SkeletonSpawner;
	public static Item SpiderSpawner;
	

	

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
			System.out.println("Starting confifuration of Additional Crafting!");
			Configuration config = new Configuration(event.getSuggestedConfigurationFile());
				
		        config.load();
		        boolean wasRead = true;
		        Block_Start_ID = config.get(Configuration.CATEGORY_BLOCK,
		                "Starting ID for all Blocks", 3050).getInt();
		        Item_Start_ID = config.get(Configuration.CATEGORY_BLOCK,
		                "Starting ID for all Items", 5000).getInt() - 256;
		   
		        if (!wasRead)
		        {
		          config.save();
		        }
		     

	    System.out.println("Finish reading and prossesing the config for Additional Crafting!");
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
		// Stairs
			MobSpawner_ID = Item_Start_ID;
			Pick_ID = Item_Start_ID + 10;
		   IronStairs = Block_Start_ID;
		    GlassStairs = Block_Start_ID + 1;
		    GoldStairs = Block_Start_ID + 2;
		    LapisStairs = Block_Start_ID + 3;
		    
		Block IronStair = new NewIronstairs(IronStairs);
		Block GlassStair = new NewGlassstairs(GlassStairs);
		Block LapisStair = new NewLapisstairs(LapisStairs);
		Block GoldStair = new NewGoldstairs(GoldStairs);
		
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
		 
		 CreeperSpawner = new MobSpawnerItem(MobSpawner_ID,0).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(CreeperSpawner,"Creeper Spawner");
		 
		 SkeletonSpawner = new MobSpawnerItem(MobSpawner_ID,1).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(SkeletonSpawner,"Skeleton Spawner");
		 
		 SpiderSpawner = new MobSpawnerItem(MobSpawner_ID,2).setCreativeTab(CreativeTabs.tabBlock);
		 LanguageRegistry.addName(SpiderSpawner,"Spider Spawner");
		 
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
		
		 ItemStack CaveSpiderEgg = new ItemStack(383, 1, 55);
		
		 ItemStack BlazeEgg = new ItemStack(383, 1, 61);
		
		 ItemStack MagamaCubeEgg = new ItemStack(383, 1, 62);
			
		 ItemStack SlimeEgg = new ItemStack(383, 1, 54);
		 
		 
				 
				
		
		
		
		 ItemStack ZombieSpawner = new ItemStack(52, 1, 54);
		
		 ItemStack ZombiePigManSpawner = new ItemStack(52, 1, 57);
		
		 ItemStack EnderMenSpawner = new ItemStack(52, 1, 58);
		
		 ItemStack CaveSpiderSpawner = new ItemStack(52, 1, 59);
		
		 ItemStack BlazeSpawner = new ItemStack(52, 1, 61);
		
		 ItemStack MagamaCubeSpawner = new ItemStack(52, 1, 62);
			
		 ItemStack SlimeSpawner = new ItemStack(52, 1, 5);
		
		 
		 
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
			GameRegistry.addRecipe(CaveSpiderEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',FermentedeSpiderEye,'z',Egg);
			
			//Blaze
			GameRegistry.addRecipe(BlazeEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',BlazeRod,'z',Egg);
			
			//MagmaCube
			GameRegistry.addRecipe(MagamaCubeEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',MagmaCream,'z',Egg);
			
			//Slime
			GameRegistry.addRecipe(SlimeEgg , "xxx", "yzy","xxx", 
					'x',Obsidian,'y',SlimeBall,'z',Egg);
		
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
			GameRegistry.addRecipe( ZombieSpawner, "xxx", "xyx","xxx", 
				'x',Obsidian,'y',ZombieEgg);
			//Zombie Pig man
			GameRegistry.addRecipe( ZombiePigManSpawner, "xxx", "xyx","xxx", 
					'x',Obsidian,'y',ZombiePigManEgg);
			//EnderMan
			GameRegistry.addRecipe( EnderMenSpawner , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',EnderMenEgg);
			
			//CaveSpider
			GameRegistry.addRecipe(CaveSpiderSpawner , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',CaveSpiderEgg);
			
			//Blaze
			GameRegistry.addRecipe(BlazeEgg , "xxx", "xyx","xxx", 
					'x',Obsidian,'y',BlazeRod);
		
			
			//Stiars Iron
			GameRegistry.addRecipe(new ItemStack(IronStair,4),"  x", " xx","xxx", 
					'x',Iron);
			
			//Stiars Glass
			GameRegistry.addRecipe(new ItemStack(GlassStair,4),"  x", " xx","xxx", 
					'x',Glass);
			
			//Stiars Lapis
			GameRegistry.addRecipe(new ItemStack(LapisStair,4),"  x", " xx","xxx", 
					'x',Lapis);
			
			//Stiars Iron
			GameRegistry.addRecipe(new ItemStack(GoldStair,4),"  x", " xx","xxx", 
					'x',Gold);
	}
  


	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		 
		
	System.out.println("Additional Crafting has finished loading!!");
	}
}