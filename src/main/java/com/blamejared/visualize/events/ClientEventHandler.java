package com.blamejared.visualize.events;

import com.blamejared.visualize.client.gui.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by Jared on 8/28/2016.
 */
public class ClientEventHandler {
    
    @SubscribeEvent
    public void guiInit(GuiOpenEvent e) {
        if(e.getGui() instanceof GuiVideoSettings) {
            e.setGui(new GuiNewVideoSettings(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().gameSettings));
        } else if(e.getGui() instanceof GuiOptions) {
            e.setGui(new GuiNewOptions(((GuiOptions) e.getGui()).lastScreen, Minecraft.getMinecraft().gameSettings));
        }
    }
    
    
    
}
