package com.blamejared.visualize;

import com.blamejared.visualize.proxy.CommonProxy;
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
        optionMap.put("invertYMouse", "false");
        optionMap.put("mouseSensitivity", "0.5");
        optionMap.put("fov", "0.0");
        optionMap.put("gamma", "0.0");
        optionMap.put("saturation", "0.0");
        optionMap.put("renderDistance", "12");
        optionMap.put("guiScale", "0");
        optionMap.put("particles", "0");
        optionMap.put("bobView", "true");
        optionMap.put("anaglyph3d", "false");
        optionMap.put("maxFps", "120");
        optionMap.put("fboEnable", "true");
        optionMap.put("fancyGraphics", "false");
        optionMap.put("ao", "2");
        optionMap.put("renderClouds", "true");
        optionMap.put("lang", "en_us");
        optionMap.put("chatVisibility", "true");
        optionMap.put("chatColors", "true");
        optionMap.put("chatLinks", "true");
        optionMap.put("chatLinksPrompt", "true");
        optionMap.put("chatOpacity", "1.0");
        optionMap.put("snooperEnabled", "true");
        optionMap.put("fullscreen", "false");
        optionMap.put("enableVsync", "true");
        optionMap.put("useVbo", "true");
        optionMap.put("hideServerAddress", "false");
        optionMap.put("touchscreen", "false");
        optionMap.put("mipmapLevels", "4");
        optionMap.put("forceUnicodeFont", "false");
        optionMap.put("entityShadows", "true");
        optionMap.put("mainHand", "right");
        optionMap.put("attackIndicator", "1");
        optionMap.put("showSubtitles", "false");
        optionMap.put("entityShadows", "true");
        optionMap.put("autoJump", "true");
        if(!centralOptions.exists()) {
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
