package chibill.AdditionalCrafting.stairs;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class NewLapisstairs extends BlockStairs {

  public NewLapisstairs (int id) {
   super(id,Block.blockLapis, 0);
   setCreativeTab(CreativeTabs.tabBlock);
   setHardness(0.5F);
   setUnlocalizedName("lapis Stair");
  
   this.useNeighborBrightness[id] = true;
  }
  }