/*     */ package chibill.AdditionalCrafting.SpawnerStuff;
/*     */ 
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.client.renderer.texture.IconRegister;
/*     */ import net.minecraft.entity.player.EntityPlayer;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*     */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*     */ import net.minecraft.world.World;
/*     */ 
/*     */ public class MobSpawnerItem extends Item
/*     */ {
/*     */   String mob;
/*     */ 
/*     */   public MobSpawnerItem(int ID, int Mob)
/*     */   {
/*  16 */     super(ID + Mob);
/*  17 */     setMaxStackSize(64);
/*     */ 
/*  19 */     if (Mob == 0) {
/*  20 */       this.mob = "Creeper";
/*     */     }
/*  22 */     else if (Mob == 1) {
/*  23 */       this.mob = "Skeleton";
/*     */     }
/*  25 */     else if (Mob == 2) {
/*  26 */       this.mob = "Spider";
/*     */     }
/*  28 */     else if (Mob == 3) {
/*  29 */       this.mob = "Zombie";
/*     */     }
/*  31 */     else if (Mob == 4) {
/*  32 */       this.mob = "PigZombie";
/*     */     }
/*  34 */     else if (Mob == 5) {
/*  35 */       this.mob = "Enderman";
/*     */     }
/*  37 */     else if (Mob == 6)
/*  38 */       this.mob = "Blaze";
/*  39 */     else if (Mob == 7)
/*  40 */       this.mob = "Witch";
/*  41 */     else if (Mob == 8) {
/*  42 */       this.mob = "VillagerGolem";
/*     */     }
/*  44 */     setUnlocalizedName(this.mob + " Spawner");
/*     */   }
/*     */ 
/*     */   public boolean onItemUse(ItemStack ItemStack_Spawner, EntityPlayer Player, World the_World, int x, int y, int z, int par7, float par8, float par9, float par10)
/*     */   {
/*  50 */     if (par7 == 0)
/*     */     {
/*  52 */       y--;
/*     */     }
/*     */ 
/*  55 */     if (par7 == 1)
/*     */     {
/*  57 */       y++;
/*     */     }
/*     */ 
/*  60 */     if (par7 == 2)
/*     */     {
/*  62 */       z--;
/*     */     }
/*     */ 
/*  65 */     if (par7 == 3)
/*     */     {
/*  67 */       z++;
/*     */     }
/*     */ 
/*  70 */     if (par7 == 4)
/*     */     {
/*  72 */       x--;
/*     */     }
/*     */ 
/*  75 */     if (par7 == 5)
/*     */     {
/*  77 */       x++;
/*     */     }
/*     */ 
/*  82 */     if (!Player.canPlayerEdit(x, y, z, par7, ItemStack_Spawner))
/*     */     {
/*  84 */       return false;
/*     */     }
/*     */ 
/*  88 */     ItemStack_Spawner.stackSize -= 1;
/*  89 */     the_World.setBlock(x, y, z, Block.mobSpawner.blockID, 0, 2);
/*  90 */     TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)the_World.getBlockTileEntity(x, y, z);
/*     */ 
/*  92 */     if (tileentitymobspawner != null)
/*     */     {
/*  94 */       tileentitymobspawner.func_98049_a().setMobID(this.mob);
/*     */     }
/*     */ 
/*  98 */     return true;
/*     */   }
/*     */ 
/*     */   public void registerIcons(IconRegister iconRegister)
/*     */   {
/* 103 */     this.itemIcon = iconRegister.registerIcon("AC:Spawner");
/*     */   }
/*     */ 
/*     */   @SideOnly(Side.CLIENT)
/*     */   public boolean hasEffect(ItemStack par1ItemStack) {
/* 108 */     return true;
/*     */   }
/*     */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.SpawnerStuff.MobSpawnerItem
 * JD-Core Version:    0.6.2
 */