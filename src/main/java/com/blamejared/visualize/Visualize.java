package com.blamejared.visualize;

import com.blamejared.visualize.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static com.blamejared.visualize.reference.Reference.*;

@Mod(modid = MODID, name = NAME, version = VERSION)
public class Visualize {
    
    @SidedProxy(clientSide = "com.blamejared.visualize.proxy.ClientProxy", serverSide = "com.blamejared.visualize.proxy.CommonProxy")
    public static CommonProxy PROXY;
    
    public Visualize() {
    }
    
    public static Map<String, String> optionMap = new HashMap<>();
    
    @Mod.EventHandler
    public void construct(FMLConstructionEvent ev) throws IOException {
        boolean newRun = false;
    
        optionMap.put("invertYMouse", String.valueOf(Minecraft.getMinecraft().gameSettings.invertMouse));
        optionMap.put("mouseSensitivity", String.valueOf(Minecraft.getMinecraft().gameSettings.mouseSensitivity));
        optionMap.put("fov", String.valueOf(Minecraft.getMinecraft().gameSettings.fovSetting));
        optionMap.put("gamma", String.valueOf(Minecraft.getMinecraft().gameSettings.gammaSetting));
        optionMap.put("saturation", String.valueOf(Minecraft.getMinecraft().gameSettings.saturation));
        optionMap.put("renderDistance", String.valueOf(Minecraft.getMinecraft().gameSettings.renderDistanceChunks));
        optionMap.put("guiScale", String.valueOf(Minecraft.getMinecraft().gameSettings.guiScale));
        optionMap.put("particles", String.valueOf(Minecraft.getMinecraft().gameSettings.particleSetting));
        optionMap.put("bobView", String.valueOf(Minecraft.getMinecraft().gameSettings.viewBobbing));
        optionMap.put("anaglyph3d", String.valueOf(Minecraft.getMinecraft().gameSettings.anaglyph));
        optionMap.put("maxFps", String.valueOf(Minecraft.getMinecraft().gameSettings.limitFramerate));
        optionMap.put("fboEnable", String.valueOf(Minecraft.getMinecraft().gameSettings.fboEnable));
        optionMap.put("fancyGraphics", String.valueOf(Minecraft.getMinecraft().gameSettings.fancyGraphics));
        optionMap.put("ao", String.valueOf(Minecraft.getMinecraft().gameSettings.ambientOcclusion));
        switch(Minecraft.getMinecraft().gameSettings.clouds) {
            case 0:
                optionMap.put("renderClouds", "false");
                break;
            case 1:
                optionMap.put("renderClouds", "fast");
                break;
            case 2:
                optionMap.put("renderClouds", "true");
        }
        optionMap.put("lang", String.valueOf(Minecraft.getMinecraft().gameSettings.language));
        optionMap.put("chatVisibility", String.valueOf(Minecraft.getMinecraft().gameSettings.chatVisibility));
        optionMap.put("chatColors", String.valueOf(Minecraft.getMinecraft().gameSettings.chatColours));
        optionMap.put("chatLinks", String.valueOf(Minecraft.getMinecraft().gameSettings.chatLinks));
        optionMap.put("chatLinksPrompt", String.valueOf(Minecraft.getMinecraft().gameSettings.chatLinksPrompt));
        optionMap.put("chatOpacity", String.valueOf(Minecraft.getMinecraft().gameSettings.chatOpacity));
        optionMap.put("snooperEnabled", String.valueOf(Minecraft.getMinecraft().gameSettings.snooperEnabled));
        optionMap.put("fullscreen", String.valueOf(Minecraft.getMinecraft().gameSettings.fullScreen));
        optionMap.put("enableVsync", String.valueOf(Minecraft.getMinecraft().gameSettings.enableVsync));
        optionMap.put("useVbo", String.valueOf(Minecraft.getMinecraft().gameSettings.useVbo));
        optionMap.put("hideServerAddress", String.valueOf(Minecraft.getMinecraft().gameSettings.hideServerAddress));
        optionMap.put("touchscreen", String.valueOf(Minecraft.getMinecraft().gameSettings.touchscreen));
        optionMap.put("mipmapLevels", String.valueOf(Minecraft.getMinecraft().gameSettings.mipmapLevels));
        optionMap.put("forceUnicodeFont", String.valueOf(Minecraft.getMinecraft().gameSettings.forceUnicodeFont));
        optionMap.put("entityShadows", String.valueOf(Minecraft.getMinecraft().gameSettings.entityShadows));
        optionMap.put("mainHand", String.valueOf(Minecraft.getMinecraft().gameSettings.mainHand.name().toLowerCase()));
        optionMap.put("attackIndicator", String.valueOf(Minecraft.getMinecraft().gameSettings.attackIndicator));
        optionMap.put("showSubtitles", String.valueOf(Minecraft.getMinecraft().gameSettings.showSubtitles));
        optionMap.put("entityShadows", String.valueOf(Minecraft.getMinecraft().gameSettings.entityShadows));
        optionMap.put("autoJump", String.valueOf(Minecraft.getMinecraft().gameSettings.autoJump));
        
        if(!centralOptions.exists()) {
            System.out.println(">>>File doesn't exist!");
            centralOptions.getParentFile().mkdirs();
            centralOptions.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(centralOptions));
            optionMap.forEach((key, val) -> {
                try {
                    writer.write(String.format("%s:%s\n", key, val));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            });
            newRun = true;
            writer.close();
        } else {
            BufferedReader reader = new BufferedReader(new FileReader(centralOptions));
            List<String> lines = reader.lines().collect(Collectors.toList());
            optionMap.clear();
            for(String line : lines) {
                optionMap.put(line.split(":")[0], line.split(":")[1]);
            }
            System.out.println(optionMap);
        }
        if(options.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(options));
            List<String> lines = reader.lines().collect(Collectors.toList());
            BufferedWriter writer = new BufferedWriter(new FileWriter(options));
            if(!newRun) {
                for(String line : lines) {
                    String key = line.split(":")[0];
                    String option = optionMap.getOrDefault(key, null);
                    if(option != null) {
                        line = key + ":" + optionMap.get(key);
                    }
                    writer.write(line + "\n");
                }
            } else {
                BufferedWriter writer1 = new BufferedWriter(new FileWriter(centralOptions));
                for(String line : lines) {
                    String key = line.split(":")[0];
                    String option = optionMap.getOrDefault(key, null);
                    if(option != null) {
                        writer1.write(line + "\n");
                    }
                }
                writer1.close();
            }
            reader.close();
            writer.close();
        } else {
            BufferedWriter writer = new BufferedWriter(new FileWriter(options));
            optionMap.forEach((key, val) -> {
                try {
                    writer.write(String.format("%s:%s\n", key, val));
                } catch(IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
        }
    }
    
    
    @Mod.EventHandler
    public void preInit(FMLPostInitializationEvent ev) {
    
        PROXY.registerEvents();
    }
    
    public static void saveSettings() throws IOException {
        if(!centralOptions.exists()) {
            centralOptions.getParentFile().mkdirs();
            centralOptions.createNewFile();
        }
        BufferedReader reader = new BufferedReader(new FileReader(options));
        List<String> lines = reader.lines().collect(Collectors.toList());
        BufferedWriter writer = new BufferedWriter(new FileWriter(centralOptions));
        for(String line : lines) {
            String key = line.split(":")[0];
            String option = optionMap.getOrDefault(key, null);
            if(option != null)
                writer.write(line + "\n");
        }
        reader.close();
        writer.close();
        
    }
    
}
