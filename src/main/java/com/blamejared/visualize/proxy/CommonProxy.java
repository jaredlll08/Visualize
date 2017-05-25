package com.blamejared.visualize.proxy;

import com.blamejared.visualize.events.CommonEventHandler;
import net.minecraftforge.common.MinecraftForge;

public class CommonProxy {
    
    
    public void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new CommonEventHandler());
    }
    
}
