package chibill.AdditionalCrafting.stairs;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewIronstairs extends BlockStairs {

  public NewIronstairs (int id) {
   super(id, Block.blockIron, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(2.0F);
   setUnlocalizedName("Iron Stair");
   this.useNeighborBrightness[id] = true;
   
  }
  }