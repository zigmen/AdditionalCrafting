/*    */ package chibill.AdditionalCrafting.stairs;
/*    */ 
/*    */ import java.util.Random;
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.BlockStairs;
/*    */ import net.minecraft.creativetab.CreativeTabs;
/*    */ 
/*    */ public class NewGlassstairs extends BlockStairs
/*    */ {
/*    */   public NewGlassstairs(int id)
/*    */   {
/* 14 */     super(id, Block.glass, 0);
/* 15 */     setCreativeTab(CreativeTabs.tabBlock);
/* 16 */     setHardness(0.5F);
/* 17 */     setLightOpacity(0);
/* 18 */     setUnlocalizedName("Glass Stair");
/* 19 */     setStepSound(soundGlassFootstep);
/* 20 */     useNeighborBrightness[id] = true;
/*    */   }
/*    */ 
/*    */   public int quantityDropped(Random par1Random)
/*    */   {
/* 25 */     return 0;
/*    */   }
/*    */ 
/*    */   protected boolean canSilkHarvest()
/*    */   {
/* 30 */     return true;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.stairs.NewGlassstairs
 * JD-Core Version:    0.6.2
 */