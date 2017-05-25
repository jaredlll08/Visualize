package com.blamejared.visualize;

import com.blamejared.visualize.proxy.CommonProxy;
import net.darkhax.bookshelf.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import static com.blamejared.visualize.reference.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class Visualize {
    
    @SidedProxy(clientSide = "com.blamejared.visualize.proxy.ClientProxy", serverSide = "com.blamejared.visualize.proxy.CommonProxy")
    public static CommonProxy PROXY;
    
    public Visualize() {
        System.out.println("<<<>>>");
    }
    
    @Mod.EventHandler
    public void construct(FMLConstructionEvent ev){
        BookshelfHooks.onPrePreInit();
        System.out.println(">>><<<");
    }
    
    
    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent ev) {
        Minecraft.getMinecraft().gameSettings.setOptionValue(GameSettings.Options.GRAPHICS, 0);
        Minecraft.getMinecraft().gameSettings.loadOptions();
        PROXY.registerEvents();
    }
    
    
}
