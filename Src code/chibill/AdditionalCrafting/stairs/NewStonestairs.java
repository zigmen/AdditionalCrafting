/*    */ package chibill.AdditionalCrafting.stairs;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class NewStonestairs extends BlockStairs
/*    */ {
/*    */   public NewStonestairs(int id)
/*    */   {
/* 12 */     super(id, Block.stone, 0);
/* 13 */     setCreativeTab(CreativeTabs.tabBlock);
/* 14 */     setHardness(2.0F);
/* 15 */     setUnlocalizedName("Stone Stair");
/* 16 */     useNeighborBrightness[id] = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewStonestairs
 * JD-Core Version:    0.6.2
 */