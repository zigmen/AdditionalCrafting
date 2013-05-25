package chibill.AdditionalCrafting.stairs;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewStonestairs extends BlockStairs {

  public NewStonestairs (int id) {
   super(id, Block.stone, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(2.0F);
   setUnlocalizedName("Stone Stair");
   this.useNeighborBrightness[id] = true;
   
  }
  }