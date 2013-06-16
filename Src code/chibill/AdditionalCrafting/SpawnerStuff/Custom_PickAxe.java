/*    */ package chibill.AdditionalCrafting.SpawnerStuff;
/*    */ 
/*    */ import chibill.AdditionalCrafting.Base;
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.client.renderer.texture.IconRegister;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ import net.minecraft.entity.EntityLiving;
/*    */ import net.minecraft.entity.item.EntityItem;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ import net.minecraft.item.EnumToolMaterial;
/*    */ import net.minecraft.item.ItemPickaxe;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.tileentity.MobSpawnerBaseLogic;
/*    */ import net.minecraft.tileentity.TileEntityMobSpawner;
/*    */ import net.minecraft.world.GameRules;
/*    */ import net.minecraft.world.World;
/*    */ 
/*    */ public class Custom_PickAxe extends ItemPickaxe
/*    */ {
/*    */   String mob;
/*    */   String Name;
/*    */   EntityPlayer player2;
/*    */ 
/*    */   public Custom_PickAxe(int par1, EnumToolMaterial par2EnumToolMaterial, String Name)
/*    */   {
/* 28 */     super(par1, par2EnumToolMaterial);
/* 29 */     setCreativeTab(CreativeTabs.tabTools);
/* 30 */     this.Name = Name;
/* 31 */     setUnlocalizedName(Name);
/*    */   }
/*    */ 
/*    */   public void registerIcons(IconRegister iconRegister) {
/* 35 */     this.itemIcon = iconRegister.registerIcon("AC:" + this.Name);
/*    */   }
/*    */ 
/*    */   public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player) {
/* 39 */     if (player.worldObj.getBlockId(X, Y, Z) == Block.mobSpawner.blockID) {
/* 40 */       TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)player.worldObj.getBlockTileEntity(X, Y, Z);
/* 41 */       if (tileentitymobspawner != null) {
/* 42 */         this.mob = tileentitymobspawner.func_98049_a().getEntityNameToSpawn();
/* 43 */         this.player2 = player;
/*    */       }
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean onBlockDestroyed(ItemStack par1ItemStack, World world, int id, int x, int y, int z, EntityLiving par7EntityLiving)
/*    */   {
/* 52 */     par1ItemStack.damageItem(6, par7EntityLiving);
/* 53 */     if (id == Block.mobSpawner.blockID)
/*    */     {
/* 56 */       ItemStack itemstack = null;
/* 57 */       if (this.mob == "Creeper") {
/* 58 */         itemstack = new ItemStack(Base.CreeperSpawner);
/*    */       }
/* 60 */       else if (this.mob == "Skeleton") {
/* 61 */         itemstack = new ItemStack(Base.SkeletonSpawner);
/*    */       }
/* 63 */       else if (this.mob == "Spider") {
/* 64 */         itemstack = new ItemStack(Base.SpiderSpawner);
/*    */       }
/* 66 */       else if (this.mob == "Zombie") {
/* 67 */         itemstack = new ItemStack(Base.ZombieSpawner);
/*    */       }
/* 69 */       else if (this.mob == "PigZombie") {
/* 70 */         itemstack = new ItemStack(Base.PigZombieSpawner);
/*    */       }
/* 72 */       else if (this.mob == "Enderman") {
/* 73 */         itemstack = new ItemStack(Base.EndermanSpawner);
/*    */       }
/* 75 */       else if (this.mob == "Blaze")
/* 76 */         itemstack = new ItemStack(Base.BlazeSpawner);
/* 77 */       else if (this.mob == "Witch")
/* 78 */         itemstack = new ItemStack(Base.WitchSpawner);
/* 79 */       else if (this.mob == "VillagerGolem")
/*    */       {
/* 81 */         itemstack = new ItemStack(Base.IronGolemSpawner);
/*    */       }
/* 83 */       if (itemstack != null)
/*    */       {
/* 85 */         dropBlockAsItem_do(world, x, y, z, itemstack);
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 90 */     return true;
/*    */   }
/*    */ 
/*    */   public void dropBlockAsItem_do(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack)
/*    */   {
/* 95 */     if ((!par1World.isRemote) && (par1World.getGameRules().getGameRuleBooleanValue("doTileDrops")))
/*    */     {
/* 98 */       float f = 0.7F;
/* 99 */       double d0 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 100 */       double d1 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 101 */       double d2 = par1World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
/* 102 */       EntityItem entityitem = new EntityItem(par1World, par2 + d0, par3 + d1, par4 + d2, par5ItemStack);
/* 103 */       entityitem.delayBeforeCanPickup = 10;
/* 104 */       par1World.spawnEntityInWorld(entityitem);
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.SpawnerStuff.Custom_PickAxe
 * JD-Core Version:    0.6.2
 */