/*     */ package chibill.AdditionalCrafting.developercapesapi;
/*     */ 
/*     */ import cpw.mods.fml.common.registry.TickRegistry;
/*     */ import cpw.mods.fml.relauncher.Side;
/*     */ import cpw.mods.fml.relauncher.SideOnly;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.renderer.RenderEngine;
/*     */ 
/*     */ @SideOnly(Side.CLIENT)
/*     */ public final class DeveloperCapesAPI
/*     */ {
/*     */   private static DeveloperCapesAPI instance;
/*  22 */   private static final Minecraft mc = Minecraft.getMinecraft();
/*     */   private HashMap<String, String> users;
/*     */   private HashMap<String, String> groupUrls;
/*  27 */   private boolean tickSetUp = false;
/*     */ 
/*     */   private DeveloperCapesAPI()
/*     */   {
/*  33 */     this.users = new HashMap();
/*  34 */     this.groupUrls = new HashMap();
/*     */   }
/*     */ 
/*     */   public static DeveloperCapesAPI getInstance()
/*     */   {
/*  42 */     if (instance == null) {
/*  43 */       instance = new DeveloperCapesAPI();
/*     */     }
/*  45 */     return instance;
/*     */   }
/*     */ 
/*     */   public void init(String parTxtUrl)
/*     */   {
/*     */     try
/*     */     {
/*  58 */       URL url = new URL(parTxtUrl);
/*  59 */       BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
/*     */ 
/*  62 */       String username = "";
/*  63 */       String group = "";
/*  64 */       String capeUrl = "";
/*     */       String line;
/*  66 */       while ((line = reader.readLine()) != null)
/*     */       {
/*  69 */         if (!line.startsWith("#"))
/*     */         {
/*  71 */           for (int i = 0; i < line.length(); i++)
/*     */           {
/*  73 */             if (line.charAt(i) == '=') {
/*  74 */               group = line.substring(0, i);
/*  75 */               String subLine = line.substring(i + 1);
/*     */ 
/*  77 */               if (subLine.startsWith("http")) {
/*  78 */                 capeUrl = subLine;
/*  79 */                 getInstance().addGroupUrl(group, capeUrl);
/*  80 */                 mc.renderEngine.obtainImageData(capeUrl, new DeveloperCapesImageBufferDownload());
/*     */               }
/*     */               else {
/*  83 */                 username = subLine.toLowerCase();
/*  84 */                 getInstance().addUser(username, group);
/*     */               }
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     } catch (IOException e) {
/*  91 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  95 */     if (!instance.tickSetUp)
/*     */     {
/*  97 */       TickRegistry.registerTickHandler(new DeveloperCapesTickHandler(), Side.CLIENT);
/*  98 */       instance.tickSetUp = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addUser(String parUsername, String parGroup)
/*     */   {
/* 111 */     if (getUserGroup(parUsername) == null)
/* 112 */       this.users.put(parUsername, parGroup);
/*     */   }
/*     */ 
/*     */   public String getUserGroup(String parUsername)
/*     */   {
/* 125 */     return (String)this.users.get(parUsername.toLowerCase());
/*     */   }
/*     */ 
/*     */   public void addGroupUrl(String parGroup, String parCapeUrl)
/*     */   {
/* 137 */     if (getGroupUrl(parGroup) == null)
/* 138 */       this.groupUrls.put(parGroup, parCapeUrl);
/*     */   }
/*     */ 
/*     */   public String getGroupUrl(String group)
/*     */   {
/* 150 */     return (String)this.groupUrls.get(group);
/*     */   }
/*     */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.developercapesapi.DeveloperCapesAPI
 * JD-Core Version:    0.6.2
 */