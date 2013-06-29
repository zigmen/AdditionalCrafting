/*    */ package chibill.AdditionalCrafting.stairs;
/*    */ 
/*    */ import java.util.Random;

import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;
/*    */ 
/*    */ public class NewDiamondStairs extends BlockStairs
/*    */ {
/*    */   public NewDiamondStairs(int id)
/*    */   {
/* 12 */     super(id, Block.blockDiamond, 0);
/* 13 */     setCreativeTab(CreativeTabs.tabBlock);
/* 14 */     setHardness(2.0F);
/* 15 */     setUnlocalizedName("Diamond Stair");
/* 16 */     useNeighborBrightness[id] = true;
/*    */   }
/*    */ 
}
	
/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewDiamondStairs
 * JD-Core Version:    0.6.2
 */