package chibill.additionalcrafting.events;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Cape_Handler extends Event{
/**
	
	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public static void init(net.minecraftforge.client.event.RenderPlayerEvent.Specials.Post event) 
	{
		if(!playerHasCape(event.entityPlayer).isEmpty())
		{
			
			EntityPlayer abstractClientPlayer = event.entityPlayer;
			ThreadDownloadImageData imageDownload = downloadImage(new ResourceLocation("skins/" + StringUtils.stripControlCodes(playerHasCape(event.entityPlayer))), "http://www.minecraftcapes.com/userskins/Diamond_Cape_by_John.png", new ImageBufferDownload());
			boolean flag = imageDownload.func_110557_a();
	        boolean flag1 = !abstractClientPlayer.isInvisible();
	        boolean flag2 = !abstractClientPlayer.getHideCape();
	        float f6;
	        
	        if (flag && flag1 && flag2)
	        {
	            event.renderer.func_110776_a(new ResourceLocation("cloaks/" + StringUtils.stripControlCodes(playerHasCape(event.entityPlayer))));
	            GL11.glPushMatrix();
	            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
	            double d0 = abstractClientPlayer.field_71091_bM + (abstractClientPlayer.field_71094_bP - abstractClientPlayer.field_71091_bM) * (double)1 - (abstractClientPlayer.prevPosX + (abstractClientPlayer.posX - abstractClientPlayer.prevPosX) * (double)event.partialTicks);
	            double d1 = abstractClientPlayer.field_71096_bN + (abstractClientPlayer.field_71095_bQ - abstractClientPlayer.field_71096_bN) * (double)1 - (abstractClientPlayer.prevPosY + (abstractClientPlayer.posY - abstractClientPlayer.prevPosY) * (double)event.partialTicks);
	            double d2 = abstractClientPlayer.field_71097_bO + (abstractClientPlayer.field_71085_bR - abstractClientPlayer.field_71097_bO) * (double)1 - (abstractClientPlayer.prevPosZ + (abstractClientPlayer.posZ - abstractClientPlayer.prevPosZ) * (double)event.partialTicks);
	            f6 = abstractClientPlayer.prevRenderYawOffset + (abstractClientPlayer.renderYawOffset - abstractClientPlayer.prevRenderYawOffset) * event.partialTicks;
	            double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
	            double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
	            float f7 = (float)d1 * 10.0F;

	            if (f7 < -6.0F)
	            {
	                f7 = -6.0F;
	            }

	            if (f7 > 32.0F)
	            {
	                f7 = 32.0F;
	            }

	            float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
	            float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;

	            if (f8 < 0.0F)
	            {
	                f8 = 0.0F;
	            }

	            float f10 = abstractClientPlayer.prevCameraYaw + (abstractClientPlayer.cameraYaw - abstractClientPlayer.prevCameraYaw) * event.partialTicks;
	            f7 += MathHelper.sin((abstractClientPlayer.prevDistanceWalkedModified + (abstractClientPlayer.distanceWalkedModified - abstractClientPlayer.prevDistanceWalkedModified) * event.partialTicks) * 6.0F) * 32.0F * f10;

	            if (abstractClientPlayer.isSneaking())
	            {
	                f7 += 25.0F;
	            }

	            GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
	            GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
	            GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
	            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
	            event.renderer.modelBipedMain.renderCloak(0.0625F);
	            GL11.glPopMatrix();
	        }
		}
	}

	private static String playerHasCape(EntityPlayer entityPlayer) {
		if(entityPlayer.username.equalsIgnoreCase("chibill"))
		{
			return "chibill";
		}
		else if(entityPlayer.username.equalsIgnoreCase("sad"))
		{
			return "";
		}
		else if(entityPlayer.username.equalsIgnoreCase("dsada"))
		{
			return "";
		}
	
		return "";
	}
	
    private static ThreadDownloadImageData downloadImage(ResourceLocation par0ResourceLocation, String par1Str, IImageBuffer par3IImageBuffer)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().func_110434_K();
        Object object = texturemanager.func_110581_b(par0ResourceLocation);

        return (ThreadDownloadImageData)object;
    }
**/
}
