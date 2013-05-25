package chibill.AdditionalCrafting.stairs;


import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewDiamondStairs extends BlockStairs {

  public NewDiamondStairs (int id) {
   super(id, Block.blockDiamond, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(2.0F);
   setUnlocalizedName("Diamond Stair");
   this.useNeighborBrightness[id] = true;
   
  }
  }