package com.blamejared.visualize.reference;

import net.minecraftforge.fml.common.Mod;

import java.io.File;

public class Reference {
    
    public static final String MODID = "visualize";
    public static final String NAME = "Visualize";
    public static final String VERSION = "1.0.0";
    public static File options = new File("options.txt");
    public static File centralOptions = new File(new File(new File(System.getProperty("user.home"), "BlameJared"), MODID), "options.txt");
}
