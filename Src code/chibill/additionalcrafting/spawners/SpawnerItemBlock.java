package chibill.additionalcrafting.spawners;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class SpawnerItemBlock extends ItemBlock {
	
	public SpawnerItemBlock(int id) {
		
		super(id);
		setHasSubtypes(true);
	}
	private final static String[] subNames = {
		"Creeper","Skeleton","Spider","Zombie","PigZombie","Enderman","Blaze","Witch","VillagerGolem"
	};


	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack itemstack) {
		return getUnlocalizedName() + "." + subNames[itemstack.getItemDamage()];
	}

}