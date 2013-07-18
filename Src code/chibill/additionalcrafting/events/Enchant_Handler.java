package chibill.additionalcrafting.events;

import chibill.additionalcrafting.Base;
import chibill.additionalcrafting.Utl;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.Event;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

public class Enchant_Handler extends Event {

	@SideOnly(Side.CLIENT)
	@ForgeSubscribe
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.entityPlayer.getCurrentEquippedItem() != null && event.entityPlayer.getCurrentEquippedItem().canHarvestBlock(Block.stone))  {
			if (EnchantmentHelper.getEnchantmentLevel(Base.Enchant_ID,event.entityPlayer.getCurrentEquippedItem()) > 0) {
				Utl.spawnerDrop(event.entity.worldObj, event.x, event.y, event.z);
				event.entityPlayer.getCurrentEquippedItem().damageItem(10, event.entityPlayer);
			}

		}
	}
}








