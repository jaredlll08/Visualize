package com.blamejared.visualize.events;

import com.blamejared.visualize.Visualize;
import com.blamejared.visualize.client.gui.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.io.IOException;
import java.util.*;

/**
 * Created by Jared on 8/28/2016.
 */
public class ClientEventHandler {
    
    @SubscribeEvent
    public void guiInit(GuiOpenEvent e) {
        if(e.getGui() instanceof GuiVideoSettings) {
            e.setGui(new GuiNewVideoSettings(Minecraft.getMinecraft().currentScreen, Minecraft.getMinecraft().gameSettings));
        }
    }
    
    @SubscribeEvent
    public void actionPerformed(GuiScreenEvent.ActionPerformedEvent e){
        if(e.getGui() instanceof GuiOptions){List<Integer> buttonIDs = new ArrayList<>();
            buttonIDs.add(110);
            buttonIDs.add(100);
            buttonIDs.add(101);
            buttonIDs.add(102);
            buttonIDs.add(103);
            buttonIDs.add(104);
            buttonIDs.add(105);
            buttonIDs.add(106);
            buttonIDs.add(200);
            if(buttonIDs.contains(e.getButton().id)){
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        
    }
    
    
}
