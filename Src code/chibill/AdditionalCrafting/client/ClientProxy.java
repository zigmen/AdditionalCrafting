package chibill.AdditionalCrafting.client;

import net.minecraftforge.client.MinecraftForgeClient;
import chibill.AdditionalCrafting.CommonProxy;
import chibill.AdditionalCrafting.developercapesapi.DeveloperCapesAPI;

public class ClientProxy extends CommonProxy {
	
	@Override
	public void registerRenderers() {
	
		
	}
	public void  capesInit(){
		DeveloperCapesAPI.getInstance().init("https://www.github.com/chibill/AdditionalCrafting/master/Capes_For_Dev/Capes.txt");
	}
	
}