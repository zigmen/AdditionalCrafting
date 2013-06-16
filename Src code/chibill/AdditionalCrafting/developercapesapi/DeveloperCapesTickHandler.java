/*    */ package chibill.AdditionalCrafting.developercapesapi;
/*    */ 
/*    */ import cpw.mods.fml.common.ITickHandler;
/*    */ import cpw.mods.fml.common.TickType;
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.util.EnumSet;
/*    */ import java.util.List;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.multiplayer.WorldClient;
/*    */ import net.minecraft.entity.player.EntityPlayer;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class DeveloperCapesTickHandler
/*    */   implements ITickHandler
/*    */ {
/* 22 */   private static final Minecraft mc = Minecraft.getMinecraft();
/* 23 */   private static final DeveloperCapesAPI instance = DeveloperCapesAPI.getInstance();
/*    */ 
/*    */   public void tickStart(EnumSet<TickType> type, Object[] tickData)
/*    */   {
/* 31 */     if ((mc.theWorld != null) && (mc.theWorld.playerEntities.size() > 0))
/*    */     {
/* 35 */       List players = mc.theWorld.playerEntities;
/*    */ 
/* 38 */       for (int counter = 0; counter < players.size(); counter++)
/*    */       {
/* 41 */         if (players.get(counter) != null)
/*    */         {
/* 44 */           EntityPlayer player = (EntityPlayer)players.get(counter);
/*    */ 
/* 46 */           if (player.cloakUrl.startsWith("http://skins.minecraft.net/MinecraftCloaks/"))
/*    */           {
/* 51 */             String lowerUsername = player.username.toLowerCase();
/*    */ 
/* 53 */             if (instance.getUserGroup(lowerUsername) != null)
/*    */             {
/* 59 */               String userGroup = instance.getUserGroup(lowerUsername);
/* 60 */               String groupUrl = instance.getGroupUrl(userGroup);
/*    */ 
/* 63 */               player.cloakUrl = groupUrl;
/*    */             }
/*    */           }
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 	
/*    */   public void tickEnd(EnumSet<TickType> type, Object[] tickData)
/*    */   {
/*    */   }
/*    */ 
/*    */   public EnumSet<TickType> ticks()
/*    */   {
/* 79 */     return EnumSet.of(TickType.CLIENT);
/*    */   }
/*    */ 
/*    */   public String getLabel()
/*    */   {
/* 84 */     return "DeveloperCapesTickHandler";
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.developercapesapi.DeveloperCapesTickHandler
 * JD-Core Version:    0.6.2
 */