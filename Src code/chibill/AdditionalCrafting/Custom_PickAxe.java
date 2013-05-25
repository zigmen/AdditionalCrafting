package chibill.AdditionalCrafting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import chibill.AdditionalCrafting.Base;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;


public class Custom_PickAxe extends ItemPickaxe { 
	String mob;
	String Name;
	EntityPlayer player2;
	
	public Custom_PickAxe(int par1, EnumToolMaterial par2EnumToolMaterial, String Name ) {
		super(par1, par2EnumToolMaterial);
		setCreativeTab(CreativeTabs.tabTools);
		this.Name = Name;
		setUnlocalizedName(Name);
	}
	public void registerIcons(IconRegister iconRegister)
	{
	         itemIcon = iconRegister.registerIcon("AC:" + Name);
	}
	public boolean onBlockStartBreak(ItemStack itemstack, int X, int Y, int Z, EntityPlayer player)
    {
		if(player.worldObj.getBlockId(X, Y, Z) == Block.mobSpawner.blockID ){
		TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)player.worldObj.getBlockTileEntity(X, Y, Z);
		if (tileentitymobspawner != null) {
		mob = tileentitymobspawner.func_98049_a().getEntityNameToSpawn();
		player2 = player;
		}
		}
		return false;
    }
	
		
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World world, int id, int x, int y, int z, EntityLiving par7EntityLiving)
	{
		par1ItemStack.damageItem(6, par7EntityLiving);
		if (id == Block.mobSpawner.blockID) {

			
			ItemStack itemstack = null;
			if( mob == "Creeper") {
				itemstack = new ItemStack(Base.CreeperSpawner);
			}
			else if(mob == "Skeleton") {
				itemstack = new ItemStack(Base.SkeletonSpawner);
			}
			else if(mob == "Spider") {
				itemstack = new ItemStack(Base.SpiderSpawner);
			}
			else if(mob == "Zombie") {
				itemstack = new ItemStack(Base.ZombieSpawner);
			}
			else if(mob == "PigZombie") {
				itemstack = new ItemStack(Base.PigZombieSpawner);
			}
			else if(mob == "Enderman") {
				itemstack = new ItemStack(Base.EndermanSpawner);
			}
			else if(mob == "Blaze") {
				itemstack = new ItemStack(Base.BlazeSpawner);
			}else if(mob =="Witch"){
				itemstack = new ItemStack(Base.WitchSpawner);
			}else if(mob == "VillagerGolem"){
				
				itemstack = new ItemStack(Base.IronGolemSpawner);
			}
		 if (itemstack != null)
         {
             dropBlockAsItem_do(world, x, y, z, itemstack);
            
		 }
		}
		
		return true;
	    }

	public void dropBlockAsItem_do(World par1World, int par2, int par3, int par4, ItemStack par5ItemStack)
    {
        if (!par1World.isRemote && par1World.getGameRules().getGameRuleBooleanValue("doTileDrops"))
        {
        	
            float f = 0.7F;
            double d0 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d1 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            double d2 = (double)(par1World.rand.nextFloat() * f) + (double)(1.0F - f) * 0.5D;
            EntityItem entityitem = new EntityItem(par1World, (double)par2 + d0, (double)par3 + d1, (double)par4 + d2, par5ItemStack);
            entityitem.delayBeforeCanPickup = 10;
            par1World.spawnEntityInWorld(entityitem);
        }
    }
}
