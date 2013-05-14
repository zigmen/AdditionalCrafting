package chibill.AdditionalCrafting.stairs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewGoldstairs extends BlockStairs {

  public NewGoldstairs (int id) {
   super(id,Block.blockGold, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(0.5F);
   setUnlocalizedName("Gold Stair");
  
   this.useNeighborBrightness[id] = true;
  }
  }