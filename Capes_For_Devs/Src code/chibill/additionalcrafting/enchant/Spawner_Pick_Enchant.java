package chibill.additionalcrafting.enchant;


import com.google.common.collect.ObjectArrays;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;


public class Spawner_Pick_Enchant extends Enchantment
{
    
    public Spawner_Pick_Enchant(int par1, int par2)
    {
      super(par1, par2, EnumEnchantmentType.digger);
      this.setName("Spawner_Pick_Enchant");
    }

    public int getMinEnchantability(int par1)
    {
      return 15 + (par1 - 1) * 9;
    }

    public int getMaxEnchantability(int par1)
    {
      return super.getMinEnchantability(par1) + 50;
    }

    public int getMaxLevel()
    {
      return 4;
    }

    public boolean canApply(ItemStack is)
    {
      
      return true;
    }
    
    public boolean canApplyAtEnchantingTable(ItemStack stack)
    {
        return this.type.canEnchantItem(stack.getItem());
    }
    
    public String getTranslatedName(int par1)
    {
        String s = StatCollector.translateToLocal("Sawner Pick!");
        return s + " " + StatCollector.translateToLocal("enchantment.level." + par1);
    }

}