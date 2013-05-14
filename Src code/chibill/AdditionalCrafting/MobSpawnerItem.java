package chibill.AdditionalCrafting;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;

public class MobSpawnerItem extends Item {
	String mob;
	public  MobSpawnerItem(int ID, int Mob ) {
		super(ID);
		setMaxStackSize(64);
		
		if( Mob == 0) {
			mob = "Creeper";
		}
		else if(Mob == 1) {
			mob = "Skeleton";
		}
		else if(Mob == 2) {
			mob = "Spider";
		}
		else if(Mob == 3) {
			mob = "Zombie";
		}
		else if(Mob == 4) {
			mob = "Ghast";
		}
		else if(Mob == 5) {
			mob = "PigZombie";
		}
		else if(Mob == 6) {
			mob = "Enderman";
		}
		setUnlocalizedName(mob);
	}
	@Override
	public boolean onItemUse(ItemStack ItemStack_Spawner, EntityPlayer Player, World the_World, int x, int y, int z, int par7 ,float par8, float par9, float par10)
    {
            if (par7 == 0)
            {
                --y;
            }

            if (par7 == 1)
            {
                ++y;
            }

            if (par7 == 2)
            {
                --z;
            }

            if (par7 == 3)
            {
                ++z;
            }

            if (par7 == 4)
            {
                --x;
            }

            if (par7 == 5)
            {
                ++x;
            }

         

        if (!Player.canPlayerEdit(x, y, z, par7, ItemStack_Spawner))
        {
            return false;
        }
        else
        {
                --ItemStack_Spawner.stackSize;
                the_World.setBlock(x, y, z, Block.mobSpawner.blockID, 0, 2);
                TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)the_World.getBlockTileEntity(	x, y, z);

                if (tileentitymobspawner != null)
                {
                    tileentitymobspawner.func_98049_a().setMobID(mob);
                }
            }

            return true;
        }
	
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
    }





