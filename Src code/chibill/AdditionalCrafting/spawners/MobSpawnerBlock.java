/*    */ package chibill.additionalcrafting.spawners;
/*    */ 
import java.util.List;
import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
/*    */ 
/*    */ public class MobSpawnerBlock extends Block
/*    */ {
	 	String mob;
public MobSpawnerBlock(int id)
/*    */   {
/* 12 */     super(id,Material.ground);
/* 13 */     setCreativeTab(CreativeTabs.tabBlock);
/* 14 */     setHardness(2.0F);
			this.setUnlocalizedName("Spawner");
/*     */   }
@SideOnly(Side.CLIENT)
public void getSubBlocks(int par1, CreativeTabs tab, List subItems) {
	for (int ix = 0; ix < 9; ix++) {
		subItems.add(new ItemStack(this, 1, ix));
	}
}


@Override
public void onBlockAdded(World par1World, int par2, int par3, int par4)
{
	int Mob = par1World.getBlockMetadata(par2, par3, par4);
	 if (Mob == 0) {
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
		 /*  44 */     
	
	 par1World.setBlock(par2, par3, par4, Block.mobSpawner.blockID, 0, 2);
    TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)par1World.getBlockTileEntity(par2, par3, par4);

   if (tileentitymobspawner != null)
    {
    tileentitymobspawner.getSpawnerLogic().setMobID(this.mob);
    }

}

@Override
public void registerIcons(IconRegister iconRegister) {
	this.blockIcon = iconRegister.registerIcon("ac:spawner");
}
}
	
