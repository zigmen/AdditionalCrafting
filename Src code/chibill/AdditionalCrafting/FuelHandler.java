/*    */ package chibill.AdditionalCrafting;
/*    */ 
/*    */ import cpw.mods.fml.common.IFuelHandler;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ 
/*    */ public class FuelHandler
/*    */   implements IFuelHandler
/*    */ {
/*    */   public int getBurnTime(ItemStack fuel)
/*    */   {
/* 11 */     int var1 = fuel.itemID;
/*    */ 
/* 13 */     if (var1 == Item.book.itemID)
/* 14 */       return 300;
/* 15 */     if (var1 == Base.NetherStone.itemID) {
/* 16 */       return 40000;
/*    */     }
/* 18 */     return 0;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.FuelHandler
 * JD-Core Version:    0.6.2
 */