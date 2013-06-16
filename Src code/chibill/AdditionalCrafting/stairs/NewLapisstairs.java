/*    */ package chibill.AdditionalCrafting.stairs;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class NewLapisstairs extends BlockStairs
/*    */ {
/*    */   public NewLapisstairs(int id)
/*    */   {
/* 11 */     super(id, Block.blockLapis, 0);
/* 12 */     setCreativeTab(CreativeTabs.tabBlock);
/* 13 */     setHardness(0.5F);
/* 14 */     setUnlocalizedName("lapis Stair");
/*    */ 
/* 16 */     useNeighborBrightness[id] = true;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewLapisstairs
 * JD-Core Version:    0.6.2
 */