/*    */ package chibill.AdditionalCrafting.developercapesapi;
/*    */ 
/*    */ import cpw.mods.fml.relauncher.Side;
/*    */ import cpw.mods.fml.relauncher.SideOnly;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.image.BufferedImage;
/*    */ import net.minecraft.client.renderer.IImageBuffer;
/*    */ 
/*    */ @SideOnly(Side.CLIENT)
/*    */ public class DeveloperCapesImageBufferDownload
/*    */   implements IImageBuffer
/*    */ {
/*    */   private int imageWidth;
/*    */   private int imageHeight;
/*    */ 
/*    */   public BufferedImage parseUserSkin(BufferedImage par1BufferedImage)
/*    */   {
/* 18 */     if (par1BufferedImage == null) {
/* 19 */       return null;
/*    */     }
/*    */ 
/* 22 */     this.imageWidth = (par1BufferedImage.getWidth(null) <= 64 ? 64 : par1BufferedImage.getWidth(null));
/* 23 */     this.imageHeight = (par1BufferedImage.getHeight(null) <= 32 ? 32 : par1BufferedImage.getHeight(null));
/*    */ 
/* 25 */     BufferedImage capeImage = new BufferedImage(this.imageWidth, this.imageHeight, 2);
/*    */ 
/* 27 */     Graphics graphics = capeImage.getGraphics();
/* 28 */     graphics.drawImage(par1BufferedImage, 0, 0, null);
/* 29 */     graphics.dispose();
/*    */ 
/* 31 */     return capeImage;
/*    */   }
/*    */ }

/* Location:           C:\Users\bill\Desktop\Minecraft\My Moding\forge\mcp\src\minecraft\
 * Qualified Name:     chibill.AdditionalCrafting.developercapesapi.DeveloperCapesImageBufferDownload
 * JD-Core Version:    0.6.2
 */