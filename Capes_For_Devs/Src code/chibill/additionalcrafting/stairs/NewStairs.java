/*    */ package chibill.additionalcrafting.stairs;
/*    */ 
/*    */ import java.util.Random;

import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class NewStairs extends BlockStairs
/*    */ {
			private boolean Glass;
/*    */   public NewStairs(int id,Block block)
/*    */   {	 super(id, block, 0);
/* 12 */   
/* 13 */     setCreativeTab(CreativeTabs.tabBlock);
/* 14 */     setHardness(2.0F);
/* 15 */     setUnlocalizedName(block.toString());
/* 16 */     useNeighborBrightness[id] = true;
			if(block == Block.glass){
				Glass = true;
			}else {
				Glass=false;
			}
/*    */   }
		public int quantityDropped(Random par1Random)
/*    */   {
			if(Glass){
/* 25 */     return 0;
			}
/*    */
			return 1;   }
/*    */ 
/*    */   protected boolean canSilkHarvest()
/*    */   {if(Glass){
/* 30 */     return true;
/*    */   }
			return true;
		}
		

/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewDirtStairs
 * JD-Core Version:    0.6.2
 */