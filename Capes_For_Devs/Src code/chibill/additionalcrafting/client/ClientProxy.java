/*    */ package chibill.additionalcrafting.client;
import net.minecraftforge.common.MinecraftForge;
/*    */ 
/*    */ 
import chibill.additionalcrafting.CommonProxy;
import chibill.additionalcrafting.events.Cape_Event;
/*    */
/*    */ 
/*    */ public class ClientProxy extends CommonProxy
/*    */ {
			
/*    */   public void registerRenderers()
/*    */   {
	         MinecraftForge.EVENT_BUS.register(new Cape_Event());

/*    */  
/*    */}  
}
/*    */

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.client.ClientProxy
 * JD-Core Version:    0.6.2
 */