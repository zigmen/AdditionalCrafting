/*     */ package chibill.AdditionalCrafting;
/*     */ 
/*     */ 
import chibill.AdditionalCrafting.SpawnerStuff.Custom_PickAxe;
/*     */ import chibill.AdditionalCrafting.SpawnerStuff.MobSpawnerItem;
/*     */ import chibill.AdditionalCrafting.networking.PacketHandler;
/*     */ import chibill.AdditionalCrafting.networking.Server_Loging_Handler;
/*     */ import chibill.AdditionalCrafting.stairs.NewDiamondStairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewDirtStairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewGlassstairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewGoldstairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewIronstairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewLapisstairs;
/*     */ import chibill.AdditionalCrafting.stairs.NewStonestairs;
/*     */ import cpw.mods.fml.common.Mod;
/*     */ import cpw.mods.fml.common.Mod.Init;
/*     */ import cpw.mods.fml.common.Mod.Instance;
/*     */ import cpw.mods.fml.common.Mod.PostInit;
/*     */ import cpw.mods.fml.common.Mod.PreInit;
/*     */ import cpw.mods.fml.common.SidedProxy;
/*     */ import cpw.mods.fml.common.event.FMLInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPostInitializationEvent;
/*     */ import cpw.mods.fml.common.event.FMLPreInitializationEvent;
/*     */ import cpw.mods.fml.common.network.NetworkMod;
/*     */ import cpw.mods.fml.common.network.NetworkRegistry;
/*     */ import cpw.mods.fml.common.registry.GameRegistry;
/*     */ import cpw.mods.fml.common.registry.LanguageRegistry;
/*     */ import java.io.BufferedReader;
		  import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.creativetab.CreativeTabs;
/*     */ import net.minecraft.item.EnumToolMaterial;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.server.MinecraftServer;
/*     */ import net.minecraft.server.management.ServerConfigurationManager;
/*     */ import net.minecraftforge.common.Configuration;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.common.Property;
/*     */ import net.minecraftforge.event.EventBus;
/*     */ 
/*     */ @Mod(modid="AdditionalCrafting", name="Additional Crafting", version="1.0.0")
/*     */ @NetworkMod(clientSideRequired=true, serverSideRequired=false, channels={"AC_Chibill", "AC_Himehowareu", "AC_Loggin"}, packetHandler=PacketHandler.class)
/*     */ public class Base
/*     */ {
/*  58 */   public static final ItemStack NetherStone = new ItemStack(87, 1, 0);
/*     */   public int Block_Start_ID;
/*     */   public int Item_Start_ID;
/*     */   public int MobSpawner_ID;
/*     */   public int Pick_ID;
/*     */   public int IronStairs;
/*     */   public int GlassStairs;
/*     */   public int GoldStairs;
/*     */   public int LapisStairs;
/*     */   public int DiamondStairs;
/*     */   public int StoneStairs;
/*     */   public int DirtStairs;
/*     */   public static boolean No_Internet;
/*     */   public static boolean Up_to_Date;
/*     */   public static Item CreeperSpawner;
/*     */   public static Item SkeletonSpawner;
/*     */   public static Item SpiderSpawner;
/*     */   public static Item ZombieSpawner;
/*     */   public static Item PigZombieSpawner;
/*     */   public static Item EndermanSpawner;
/*     */   public static Item BlazeSpawner;
/*     */   public static Item WitchSpawner;
/*     */   public static Item IronGolemSpawner;
/*     */   public static boolean Check;
/*     */   public static Configuration config;
/*     */ 
/*     */   @Mod.Instance("AdditionalCrafting")
/*     */   public static Base instance;
/*     */ 
/*     */   @SidedProxy(clientSide="chibill.AdditionalCrafting.client.ClientProxy", serverSide="chibill.AdditionalCrafting.CommonProxy")
/*     */   public static CommonProxy proxy;
/*     */ 
/*     */   @Mod.PreInit
/*     */   public void preInit(FMLPreInitializationEvent event)
/*     */   {
/* 104 */
/*     */ 
/* 106 */     System.out.println("[AdditionalCrafting] Starting confifuration of Additional Crafting!");
/* 107 */     Configuration config = new Configuration(event.getSuggestedConfigurationFile());
/*     */ 
/* 109 */     config.load();
/*     */ 
/* 111 */     this.Block_Start_ID = config.get("block", 
/* 112 */       "Starting ID for all Blocks", 3050).getInt();
/* 113 */     this.Item_Start_ID = 
/* 114 */       (config.get("block", 
/* 114 */       "Starting ID for all Items", 5000).getInt() - 256);
/*     */ 
/* 116 */     Check = config.get("general", "Enables the Update Checker", true).getBoolean(true);
/*     */ 
/* 118 */     if (Check) {
/* 119 */       Update();
/*     */     }
/* 121 */     config.save();
/*     */ 
/* 125 */     System.out.println("[AdditionalCrafting] Finish reading and prossesing the config for Additional Crafting!");
/*     */   }
/*     */ 
/*     */   @Mod.Init
/*     */   public void load(FMLInitializationEvent event)
/*     */   {
/* 131 */     NetworkRegistry.instance().registerConnectionHandler(new Server_Loging_Handler());
/*     */ 
/* 133 */     this.MobSpawner_ID = this.Item_Start_ID;
/* 134 */     this.Pick_ID = (this.Item_Start_ID + 10);
/*     */ 
/* 136 */     this.IronStairs = this.Block_Start_ID;
/* 137 */     this.GlassStairs = (this.Block_Start_ID + 1);
/* 138 */     this.GoldStairs = (this.Block_Start_ID + 2);
/* 139 */     this.LapisStairs = (this.Block_Start_ID + 3);
/* 140 */     this.DiamondStairs = (this.Block_Start_ID + 4);
/* 141 */     this.DirtStairs = (this.Block_Start_ID + 5);
/* 142 */     this.StoneStairs = (this.Block_Start_ID + 6);
/*     */ 
/* 144 */     Block DirtStair = new NewDirtStairs(this.DirtStairs);
/* 145 */     Block DiamondStair = new NewDiamondStairs(this.DiamondStairs);
/* 146 */     Block StoneStair = new NewStonestairs(this.StoneStairs);
/* 147 */     Block IronStair = new NewIronstairs(this.IronStairs);
/* 148 */     Block GlassStair = new NewGlassstairs(this.GlassStairs);
/* 149 */     Block LapisStair = new NewLapisstairs(this.LapisStairs);
/* 150 */     Block GoldStair = new NewGoldstairs(this.GoldStairs);
/*     */ 
/* 153 */     MinecraftForge.setBlockHarvestLevel(DiamondStair, "pickaxe", 2);
/* 154 */     GameRegistry.registerBlock(DiamondStair, "DiamondStairs");
/* 155 */     LanguageRegistry.addName(DiamondStair, "Diamond Stairs");
/*     */ 
/* 157 */     MinecraftForge.setBlockHarvestLevel(StoneStair, "pickaxe", 1);
/* 158 */     GameRegistry.registerBlock(StoneStair, "StoneStairs");
/* 159 */     LanguageRegistry.addName(StoneStair, "Smooth Stone Stairs");
/*     */ 
/* 161 */     GameRegistry.registerBlock(DirtStair, "DirtStairs");
/* 162 */     LanguageRegistry.addName(DirtStair, "Dirt Stairs");
/*     */ 
/* 164 */     MinecraftForge.setBlockHarvestLevel(IronStair, "pickaxe", 2);
/* 165 */     GameRegistry.registerBlock(IronStair, "IronStairs");
/* 166 */     LanguageRegistry.addName(IronStair, "Iron Stairs");
/*     */ 
/* 169 */     GameRegistry.registerBlock(GlassStair, "GlassStairs");
/* 170 */     LanguageRegistry.addName(GlassStair, "Glass Stairs");
/*     */ 
/* 173 */     MinecraftForge.setBlockHarvestLevel(LapisStair, "pickaxe", 2);
/* 174 */     GameRegistry.registerBlock(LapisStair, "LapisStairs");
/* 175 */     LanguageRegistry.addName(LapisStair, "Lapis Stairs");
/*     */ 
/* 177 */     MinecraftForge.setBlockHarvestLevel(GoldStair, "pickaxe", 2);
/* 178 */     GameRegistry.registerBlock(GoldStair, "GoldStairs");
/* 179 */     LanguageRegistry.addName(GoldStair, "Gold Stairs");
/*     */ 
/* 181 */     Item Spawner_Pick = new Custom_PickAxe(this.Pick_ID, EnumToolMaterial.WOOD, "Spawner_Pick");
/* 182 */     GameRegistry.registerItem(Spawner_Pick, "Spawner Pick");
/* 183 */     LanguageRegistry.addName(Spawner_Pick, "Spawner Collection Pick");
/*     */ 
/* 185 */     CreeperSpawner = new MobSpawnerItem(this.MobSpawner_ID, 0).setCreativeTab(CreativeTabs.tabBlock);
/* 186 */     LanguageRegistry.addName(CreeperSpawner, "Creeper Spawner");
/*     */ 
/* 188 */     SkeletonSpawner = new MobSpawnerItem(this.MobSpawner_ID, 1).setCreativeTab(CreativeTabs.tabBlock);
/* 189 */     LanguageRegistry.addName(SkeletonSpawner, "Skeleton Spawner");
/*     */ 
/* 191 */     SpiderSpawner = new MobSpawnerItem(this.MobSpawner_ID, 2).setCreativeTab(CreativeTabs.tabBlock);
/* 192 */     LanguageRegistry.addName(SpiderSpawner, "Spider Spawner");
/*     */ 
/* 194 */     ZombieSpawner = new MobSpawnerItem(this.MobSpawner_ID, 3).setCreativeTab(CreativeTabs.tabBlock);
/* 195 */     LanguageRegistry.addName(ZombieSpawner, "Zombie Spawner");
/*     */ 
/* 197 */     PigZombieSpawner = new MobSpawnerItem(this.MobSpawner_ID, 4).setCreativeTab(CreativeTabs.tabBlock);
/* 198 */     LanguageRegistry.addName(PigZombieSpawner, "Zombie Pigman Spawner");
/*     */ 
/* 200 */     EndermanSpawner = new MobSpawnerItem(this.MobSpawner_ID, 5).setCreativeTab(CreativeTabs.tabBlock);
/* 201 */     LanguageRegistry.addName(EndermanSpawner, "Enderman Spawner");
/*     */ 
/* 203 */     BlazeSpawner = new MobSpawnerItem(this.MobSpawner_ID, 6).setCreativeTab(CreativeTabs.tabBlock);
/* 204 */     LanguageRegistry.addName(BlazeSpawner, "Blaze Spawner");
/*     */ 
/* 206 */     WitchSpawner = new MobSpawnerItem(this.MobSpawner_ID, 7).setCreativeTab(CreativeTabs.tabBlock);
/* 207 */     LanguageRegistry.addName(WitchSpawner, "Witch Spawner");
/*     */ 
/* 209 */     IronGolemSpawner = new MobSpawnerItem(this.MobSpawner_ID, 8).setCreativeTab(CreativeTabs.tabBlock);
/* 210 */     LanguageRegistry.addName(IronGolemSpawner, "Iron Golem Spawner");
/*     */ 
/* 215 */     ItemStack OakPlank = new ItemStack(5, 1, 0);
/* 216 */     ItemStack OakSlab = new ItemStack(126, 1, 0);
/* 217 */     ItemStack Oak = new ItemStack(17, 1, 0);
/*     */ 
/* 220 */     ItemStack SprucePlank = new ItemStack(5, 1, 1);
/* 221 */     ItemStack SpruceSlab = new ItemStack(126, 1, 1);
/* 222 */     ItemStack Spruce = new ItemStack(17, 1, 1);
/*     */ 
/* 225 */     ItemStack BirchPlank = new ItemStack(5, 1, 2);
/* 226 */     ItemStack BirchSlab = new ItemStack(126, 1, 2);
/* 227 */     ItemStack Birch = new ItemStack(17, 1, 2);
/*     */ 
/* 230 */     ItemStack JunglePlank = new ItemStack(5, 1, 3);
/* 231 */     ItemStack JungleSlab = new ItemStack(126, 1, 3);
/* 232 */     ItemStack Jungle = new ItemStack(17, 1, 3);
/*     */ 
/* 235 */     ItemStack Stone = new ItemStack(1, 1, 0);
/* 236 */     ItemStack StoneSlab = new ItemStack(44, 1, 0);
/*     */ 
/* 239 */     ItemStack CobbleStone = new ItemStack(4, 1, 0);
/* 240 */     ItemStack CobbleStoneSlab = new ItemStack(44, 1, 3);
/*     */ 
/* 243 */     ItemStack StoneBrick = new ItemStack(98, 1, 0);
/* 244 */     ItemStack StoneBrickSlab = new ItemStack(44, 1, 5);
/*     */ 
/* 247 */     ItemStack SandStone = new ItemStack(24, 1, 0);
/* 248 */     ItemStack SandStoneSlab = new ItemStack(44, 1, 1);
/*     */ 
/* 251 */     ItemStack Brick = new ItemStack(45, 1, 0);
/* 252 */     ItemStack BrickSlab = new ItemStack(44, 1, 4);
/*     */ 
/* 255 */     ItemStack NetherBrick = new ItemStack(43, 1, 6);
/* 256 */     ItemStack NetherBrickSlabs = new ItemStack(44, 1, 6);
/*     */ 
/* 259 */     ItemStack Obsidian = new ItemStack(49, 1, 0);
/*     */ 
/* 262 */     ItemStack GunPowder = new ItemStack(289, 1, 0);
/*     */ 
/* 265 */     ItemStack Egg = new ItemStack(344, 1, 0);
/*     */ 
/* 268 */     ItemStack Bone = new ItemStack(352, 1, 0);
/*     */ 
/* 271 */     ItemStack SpiderEye = new ItemStack(375, 1, 0);
/*     */ 
/* 274 */     ItemStack RottenFlesh = new ItemStack(367, 1, 0);
/*     */ 
/* 277 */     ItemStack GoldNugget = new ItemStack(371, 1, 0);
/*     */ 
/* 280 */     ItemStack EnderPerl = new ItemStack(368, 1, 0);
/*     */ 
/* 283 */     ItemStack FermentedeSpiderEye = new ItemStack(Item.fermentedSpiderEye);
/*     */ 
/* 286 */     ItemStack BlazeRod = new ItemStack(369, 1, 0);
/*     */ 
/* 289 */     ItemStack MagmaCream = new ItemStack(376, 1, 0);
/*     */ 
/* 292 */     ItemStack SlimeBall = new ItemStack(42, 1, 0);
/*     */ 
/* 295 */     ItemStack water = new ItemStack(326, 1, 0);
/*     */ 
/* 299 */     ItemStack NetherStone = new ItemStack(87, 1, 0);
/*     */ 
/* 302 */     ItemStack Iron = new ItemStack(265, 1, 0);
/*     */ 
/* 305 */     ItemStack Glass = new ItemStack(20, 1, 0);
/*     */ 
/* 308 */     ItemStack Lapis = new ItemStack(351, 1, 4);
/*     */ 
/* 312 */     ItemStack Gold = new ItemStack(266, 1, 0);
/*     */ 
/* 315 */     ItemStack CreeperEgg = new ItemStack(383, 1, 50);
/*     */ 
/* 317 */     ItemStack SkeletonEgg = new ItemStack(383, 1, 51);
/*     */ 
/* 319 */     ItemStack SpiderEgg = new ItemStack(383, 1, 52);
/*     */ 
/* 321 */     ItemStack ZombieEgg = new ItemStack(383, 1, 54);
/*     */ 
/* 323 */     ItemStack ZombiePigManEgg = new ItemStack(383, 1, 57);
/*     */ 
/* 325 */     ItemStack EnderMenEgg = new ItemStack(383, 1, 58);
/*     */ 
/* 327 */     ItemStack WitchEgg = new ItemStack(383, 1, 66);
/*     */ 
/* 329 */     ItemStack BlazeEgg = new ItemStack(383, 1, 61);
/*     */ 
/* 331 */     ItemStack IronGolemEgg = new ItemStack(383, 1, 99);
/*     */ 
/* 346 */     GameRegistry.addRecipe(NetherBrick, new Object[] { "x ", "x ", 
/* 347 */       Character.valueOf('x'), NetherBrickSlabs });
/*     */ 
/* 350 */     GameRegistry.addRecipe(OakPlank, new Object[] { "x ", "x ", 
/* 351 */       Character.valueOf('x'), OakSlab });
/*     */ 
/* 354 */     GameRegistry.addRecipe(Oak, new Object[] { "x x", "x x", 
/* 355 */       Character.valueOf('x'), OakPlank });
/*     */ 
/* 358 */     GameRegistry.addRecipe(SprucePlank, new Object[] { "x ", "x ", 
/* 359 */       Character.valueOf('x'), SpruceSlab });
/*     */ 
/* 362 */     GameRegistry.addRecipe(Spruce, new Object[] { "x x", "x x", 
/* 363 */       Character.valueOf('x'), SprucePlank });
/*     */ 
/* 366 */     GameRegistry.addRecipe(BirchPlank, new Object[] { "x ", "x ", 
/* 367 */       Character.valueOf('x'), BirchSlab });
/*     */ 
/* 370 */     GameRegistry.addRecipe(Birch, new Object[] { "x x", "x x", 
/* 371 */       Character.valueOf('x'), BirchPlank });
/*     */ 
/* 374 */     GameRegistry.addRecipe(JunglePlank, new Object[] { "x ", "x ", 
/* 375 */       Character.valueOf('x'), JungleSlab });
/*     */ 
/* 378 */     GameRegistry.addRecipe(Jungle, new Object[] { "x x", "x x", 
/* 379 */       Character.valueOf('x'), JunglePlank });
/*     */ 
/* 382 */     GameRegistry.addRecipe(Stone, new Object[] { "x ", "x ", 
/* 383 */       Character.valueOf('x'), StoneSlab });
/*     */ 
/* 386 */     GameRegistry.addRecipe(StoneBrick, new Object[] { "x ", "x ", 
/* 387 */       Character.valueOf('x'), StoneBrickSlab });
/*     */ 
/* 390 */     GameRegistry.addRecipe(Brick, new Object[] { "x ", "x ", 
/* 391 */       Character.valueOf('x'), BrickSlab });
/*     */ 
/* 394 */     GameRegistry.addRecipe(CobbleStone, new Object[] { "x ", "x ", 
/* 395 */       Character.valueOf('x'), CobbleStoneSlab });
/*     */ 
/* 399 */     GameRegistry.registerFuelHandler(new FuelHandler());
/*     */ 
/* 405 */     GameRegistry.addRecipe(CreeperEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 406 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), GunPowder, Character.valueOf('z'), Egg });
/*     */ 
/* 408 */     GameRegistry.addRecipe(SkeletonEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 409 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), Bone, Character.valueOf('z'), Egg });
/*     */ 
/* 411 */     GameRegistry.addRecipe(SpiderEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 412 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), SpiderEye, Character.valueOf('z'), Egg });
/*     */ 
/* 414 */     GameRegistry.addRecipe(ZombieEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 415 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), RottenFlesh, Character.valueOf('z'), Egg });
/*     */ 
/* 417 */     GameRegistry.addRecipe(ZombiePigManEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 418 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), GoldNugget, Character.valueOf('z'), Egg });
/*     */ 
/* 420 */     GameRegistry.addRecipe(EnderMenEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 421 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), EnderPerl, Character.valueOf('z'), Egg });
/*     */ 
/* 424 */     GameRegistry.addRecipe(WitchEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 425 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), FermentedeSpiderEye, Character.valueOf('z'), Egg });
/*     */ 
/* 428 */     GameRegistry.addRecipe(BlazeEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 429 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), BlazeRod, Character.valueOf('z'), Egg });
/*     */ 
/* 433 */     GameRegistry.addRecipe(IronGolemEgg, new Object[] { "xxx", "yzy", "xxx", 
/* 434 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), Iron, Character.valueOf('z'), Egg });
/*     */ 
/* 439 */     GameRegistry.addRecipe(new ItemStack(CreeperSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 440 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), CreeperEgg });
/*     */ 
/* 442 */     GameRegistry.addRecipe(new ItemStack(SkeletonSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 443 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), SkeletonEgg });
/*     */ 
/* 445 */     GameRegistry.addRecipe(new ItemStack(SpiderSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 446 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), SpiderEgg });
/*     */ 
/* 448 */     GameRegistry.addRecipe(new ItemStack(ZombieSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 449 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), ZombieEgg });
/*     */ 
/* 451 */     GameRegistry.addRecipe(new ItemStack(PigZombieSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 452 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), ZombiePigManEgg });
/*     */ 
/* 454 */     GameRegistry.addRecipe(new ItemStack(EndermanSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 455 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), EnderMenEgg });
/*     */ 
/* 458 */     GameRegistry.addRecipe(new ItemStack(BlazeSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 459 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), BlazeEgg });
/*     */ 
/* 462 */     GameRegistry.addRecipe(new ItemStack(WitchSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 463 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), WitchEgg });
/* 464 */     GameRegistry.addRecipe(new ItemStack(IronGolemSpawner), new Object[] { "xxx", "xyx", "xxx", 
/* 465 */       Character.valueOf('x'), Obsidian, Character.valueOf('y'), IronGolemEgg });
/*     */ 
/* 469 */     GameRegistry.addRecipe(new ItemStack(IronStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 470 */       Character.valueOf('x'), Iron });
/*     */ 
/* 473 */     GameRegistry.addRecipe(new ItemStack(GlassStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 474 */       Character.valueOf('x'), Glass });
/*     */ 
/* 477 */     GameRegistry.addRecipe(new ItemStack(LapisStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 478 */       Character.valueOf('x'), Lapis });
/*     */ 
/* 481 */     GameRegistry.addRecipe(new ItemStack(GoldStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 482 */       Character.valueOf('x'), Gold });
/*     */ 
/* 485 */     GameRegistry.addRecipe(new ItemStack(DirtStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 486 */       Character.valueOf('x'), new ItemStack(3, 1, 0) });
/*     */ 
/* 489 */     GameRegistry.addRecipe(new ItemStack(StoneStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 490 */       Character.valueOf('x'), Stone });
/*     */ 
/* 493 */     GameRegistry.addRecipe(new ItemStack(DiamondStair, 4), new Object[] { "  x", " xx", "xxx", 
/* 494 */       Character.valueOf('x'), new ItemStack(Item.diamond) });
/*     */ 
/* 496 */     GameRegistry.addRecipe(new ItemStack(Spawner_Pick), new Object[] { "xyx", " z ", " z ", 
/* 497 */       Character.valueOf('x'), new ItemStack(Item.rottenFlesh), Character.valueOf('y'), Iron, Character.valueOf('z'), new ItemStack(Item.stick) });
/*     */   }
/*     */ 
/*     */   @Mod.PostInit
/*     */   public void postInit(FMLPostInitializationEvent event)
/*     */   {
/* 504 */ 
/*     */ 
/* 508 */     System.out.println("[AdditionalCrafting] Additional Crafting has finished loading!!");
/*     */   }
/*     */ 
/*     */   public static void Update()
/*     */   {
/*     */     try {
/* 514 */       URL url = new URL("https://raw.github.com/chibill/AdditionalCrafting/master/Version_Control/1.5.txt");
/*     */ 
/* 516 */       BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */ 
/* 518 */       String str = in.readLine();
/* 523 */       if (in != null) {
/* 524 */         No_Internet = false;
/* 525 */         if (str.equals("1.0.0")) {
/* 526 */           Up_to_Date = true;
/* 527 */           System.out.println("[AdditionalCrafting] Additional Crafting up to date!");
/*     */         }
/*     */         else {
					Up_to_Date = false;
/* 530 */           System.out.println("[AdditionalCrafting] Additional Crafting is out of date for this verison of Minecraft!");
/*     */         }
/*     */       }
/* 533 */       in.close();
/*     */     } catch (MalformedURLException localMalformedURLException) {
/*     */     } catch (IOException e) {
/* 536 */       No_Internet = true;
/* 537 */       System.out.println("[AdditionalCrafting] Additional Crafting has no internet conection!");
/*     */     }
/*     */   }
/*     */ 
/*     */  
/*     */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.Base
 * JD-Core Version:    0.6.2
 */