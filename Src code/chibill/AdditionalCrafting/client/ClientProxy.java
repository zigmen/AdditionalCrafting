/*    */ package chibill.AdditionalCrafting.client;
/*    */ 
/*    */ import chibill.AdditionalCrafting.CommonProxy;
/*    */ import chibill.AdditionalCrafting.developercapesapi.DeveloperCapesAPI;
/*    */ 
/*    */ public class ClientProxy extends CommonProxy
/*    */ {
/*    */   public void registerRenderers()
/*    */   {
			capesInit();
/*    */   }
/*    */ 
/*    */   public void capesInit()
/*    */   {
/* 15 */     DeveloperCapesAPI.getInstance().init("https://www.github.com/chibill/AdditionalCrafting/master/Capes_For_Dev/Capes.txt");
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.client.ClientProxy
 * JD-Core Version:    0.6.2
 */