/*    */ package chibill.additionalcrafting.networking;
/*    */ 
/*    */ import chibill.additionalcrafting.Base;
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.network.IPacketHandler;
/*    */ import cpw.mods.fml.common.network.Player;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.DataInputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.network.INetworkManager;
/*    */ import net.minecraft.network.packet.Packet250CustomPayload;
/*    */
/*    */ public class PacketHandler implements IPacketHandler
/*    */ {
			public static boolean chibill;
			public static boolean Himehowareu;
/* 23 */   private static final EntityPlayerMP playerEnitity = null;
/*    */ 
/*    */   public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player playerEntity)
/*    */   {
/* 27 */     Side side = FMLCommonHandler.instance().getEffectiveSide();
/* 28 */     if (side == Side.CLIENT)
/*    */     {
/* 30 */       if (packet.channel.equals("AC_Chibill")) {
/* 31 */         handleChibill(packet, playerEntity);
/*    */       }
/* 33 */       if (packet.channel.equals("AC_Himehowareu")) {
/* 34 */         handleHimehowareu(packet, playerEntity);
/*    */       }
/* 36 */       if (packet.channel.equals("AC_Loggin"))
/* 37 */         handleLoggin(packet, playerEntity);
/*    */     }
/*    */   }
/*    */ 
/*    */   private void handleChibill(Packet250CustomPayload packet, Player playerEntity)
/*    */   {
/* 47 */     DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
/*    */     try
/*    */     {
/* 53 */      chibill = inputStream.readBoolean();
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */       boolean chibill;
/* 55 */       e.printStackTrace();
/*    */       return;
/*    */     }
/*    */     
/* 59 */     if (chibill)
/* 60 */       ((EntityPlayerMP)playerEntity).addChatMessage("Chibill the Creator of AdditionalCrafting has joined the game!");
/*    */   }
/*    */ 
/*    */   private void handleHimehowareu(Packet250CustomPayload packet, Player playerEntity)
/*    */   {
/* 65 */     DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
/*    */     try
/*    */     {
/* 71 */       Himehowareu = inputStream.readBoolean();
/*    */     }
/*    */     catch (IOException e)
/*    */     {
/*    */
/* 73 */       e.printStackTrace();
/*    */       return;
/*    */     }
/*    */ 
/* 77 */     if (Himehowareu)
/* 78 */       ((EntityPlayerMP)playerEntity).addChatMessage("Himehowareu the Creator of AdditionalCrafting's Textures has joined the game!");
/*    */   }
/*    */ 
/*    */   private void handleLoggin(Packet250CustomPayload packet, Player playerEntity)
/*    */   {
/* 84 */     if (Base.No_Internet) {
/* 85 */       ((EntityPlayerMP)playerEntity).addChatMessage("§c[AdditionalCrafting] " + ((EntityPlayerMP)playerEntity).username + " AdditionalCrafting could not check if it was out of date because there was no internet connection found!");
/*    */     }
/* 87 */     else if (Base.Up_to_Date) {
/* 88 */           	Base.ACLog.info(" Additional Crafting up to date!");
/* 89 */       ((EntityPlayerMP)playerEntity).addChatMessage("[AdditionalCrafting] " + ((EntityPlayerMP)playerEntity).username + " Your AdditionalCrafting is up to date for this version of Minecraft!");
/*    */     }
/*    */     else
/*    */     {
/* 93 */           Base.ACLog.info("§c Additional Crafting is out of date for this verison of Minecraft!");
/* 94 */       ((EntityPlayerMP)playerEntity).addChatMessage("§c[AdditionalCrafting] " + ((EntityPlayerMP)playerEntity).username + " Your AdditionalCrafting is out of date for this version of Minecraft!");
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.networking.PacketHandler
 * JD-Core Version:    0.6.2
 */