package chibill.AdditionalCrafting.stairs;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewDirtStairs extends BlockStairs {

  public NewDirtStairs (int id) {
   super(id, Block.dirt, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(2.0F);
   setUnlocalizedName("Dirt Stair");
   this.useNeighborBrightness[id] = true;
   
  }
  }