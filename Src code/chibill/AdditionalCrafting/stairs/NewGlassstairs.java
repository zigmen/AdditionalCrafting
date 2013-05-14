package chibill.AdditionalCrafting.stairs;


import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewGlassstairs extends BlockStairs {

  public NewGlassstairs (int id) {
   super(id,Block.glass, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(0.5F);
   setLightOpacity(0);
   setUnlocalizedName("Glass Stair");
   setStepSound(soundGlassFootstep);
   this.useNeighborBrightness[id] = true;
   
  }
  public int quantityDropped(Random par1Random)
  {
      return 0;
  }

  protected boolean canSilkHarvest()
  {
      return true;
  }
  }