package com.blamejared.visualize.proxy;

import com.blamejared.visualize.events.ClientEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    
    @Override
    public void registerEvents() {
        super.registerEvents();
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
    }
}
