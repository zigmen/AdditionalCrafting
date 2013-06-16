/*    */ package chibill.AdditionalCrafting.stairs;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class NewIronstairs extends BlockStairs
/*    */ {
/*    */   public NewIronstairs(int id)
/*    */   {
/* 12 */     super(id, Block.blockIron, 0);
/* 13 */     setCreativeTab(CreativeTabs.tabBlock);
/* 14 */     setHardness(2.0F);
/* 15 */     setUnlocalizedName("Iron Stair");
/* 16 */     useNeighborBrightness[id] = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewIronstairs
 * JD-Core Version:    0.6.2
 */