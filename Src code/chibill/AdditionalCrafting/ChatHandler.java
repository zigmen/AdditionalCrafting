package chibill.AdditionalCrafting;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.NetLoginHandler;
import net.minecraft.network.packet.NetHandler;
import net.minecraft.network.packet.Packet1Login;
import net.minecraft.server.MinecraftServer;
import cpw.mods.fml.common.network.IConnectionHandler;
import cpw.mods.fml.common.network.Player;


public class ChatHandler implements IConnectionHandler {


    public static boolean chibill_Login;


	@Override
    public void clientLoggedIn(NetHandler clientHandler,
            INetworkManager manager, Packet1Login login) {
    }


    @Override
    public void connectionClosed(INetworkManager manager) {
    	
    }


    @Override
    public void connectionOpened(NetHandler netClientHandler,
            MinecraftServer server, INetworkManager manager) {
    }


    @Override
    public void connectionOpened(NetHandler netClientHandler, String server,
            int port, INetworkManager manager) {
    }


    @Override
    public String connectionReceived(NetLoginHandler netHandler,
            INetworkManager manager) {
        return null;
    }


    @Override
    public void playerLoggedIn(Player player, NetHandler netHandler,INetworkManager manager) {
    	Base.Update();
    	if(((EntityPlayerMP) player).username == "chibill"){
    		
    	}
    	if(Base.No_Internet){
    		((EntityPlayerMP) player).sendChatToPlayer("§c[AdditionalCrafting] "+ ((EntityPlayerMP) player).username + " AdditionalCrafting could not check if it was out of date because there was no internet connection found!");
    	}else{
    		if(Base.Up_to_Date){
    		System.out.println("[AdditionalCrafting] Additional Crafting up to date!");
            ((EntityPlayerMP) player).sendChatToPlayer("[AdditionalCrafting] "+ ((EntityPlayerMP) player).username + " Your AdditionalCrafting is up to date for this version of Minecraft!");
        }else
        {
        	System.out.println("§c[AdditionalCrafting] Additional Crafting is out of date for this verison of Minecraft!");
        	 ((EntityPlayerMP) player).sendChatToPlayer("§c[AdditionalCrafting] "+ ((EntityPlayerMP) player).username + " Your AdditionalCrafting is out of date for this version of Minecraft!");
        }
    }
    }

}
    


