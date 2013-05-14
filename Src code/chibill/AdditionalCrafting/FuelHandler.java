package chibill.AdditionalCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.IFuelHandler;

public class FuelHandler implements IFuelHandler {
	@Override
	public int getBurnTime(ItemStack fuel) {
		
		
		int var1 = fuel.itemID;
		
		if(var1 == Item.book.itemID){
			return 300;
		}else if(var1 == Base.NetherStone.itemID){
			return 40000;
		}else
			return 0;
		}
	
}
