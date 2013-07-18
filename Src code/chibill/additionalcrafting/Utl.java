package chibill.additionalcrafting;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class Utl {
		static String mob;


	public static void spawnerDrop(World World,int X,int Y,int Z){
		if(World.getBlockId(X, Y, Z)==Block.mobSpawner.blockID){
		TileEntityMobSpawner tileentitymobspawner =	(TileEntityMobSpawner)World.getBlockTileEntity(X, Y, Z);
		if (tileentitymobspawner != null) {
			mob = tileentitymobspawner.getSpawnerLogic().getEntityNameToSpawn();
		}
		ItemStack itemstack = null;
		if (mob == "Creeper") {
			itemstack = new ItemStack(Base.Spawner,1,0);
		}
		else if (mob == "Skeleton") {
			itemstack = new ItemStack(Base.Spawner,1,1);
		}
		else if (mob == "Spider") {
			itemstack = new ItemStack(Base.Spawner,1,2);
		}
		else if (mob == "Zombie") {
			itemstack = new ItemStack(Base.Spawner,1,3);
		}
		else if (mob == "PigZombie") {
			itemstack = new ItemStack(Base.Spawner,1,3);
		}
		else if (mob == "Enderman") {
			itemstack = new ItemStack(Base.Spawner,1,4);
		}
		else if (mob == "Blaze")
			itemstack = new ItemStack(Base.Spawner,1,5);
		else if (mob == "Witch")
			itemstack = new ItemStack(Base.Spawner,1,6);
		else if (mob == "VillagerGolem")
		{
			itemstack = new ItemStack(Base.Spawner,1,7);
		}
		if (itemstack != null)
		{
			float f = 0.7F;
			double d0 = World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d1 = World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			double d2 = World.rand.nextFloat() * f + (1.0F - f) * 0.5D;
			EntityItem entityitem = new EntityItem(World,X + d0, Y + d1, Z + d2, itemstack);
			entityitem.delayBeforeCanPickup = 10;
			World.spawnEntityInWorld(entityitem);
		}
		
		World.removeBlockTileEntity(X, Y, Z);
		World.setBlock(X,Y,Z, 0);
	}
		
}
}