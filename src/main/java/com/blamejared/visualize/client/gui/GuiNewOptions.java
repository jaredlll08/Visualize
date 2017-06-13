package com.blamejared.visualize.client.gui;

import com.blamejared.visualize.Visualize;
import net.minecraft.client.gui.*;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.EnumDifficulty;

import java.io.IOException;

public class GuiNewOptions extends GuiOptions {
    
    public GuiNewOptions(GuiScreen p_i1046_1_, GameSettings p_i1046_2_) {
        super(p_i1046_1_, p_i1046_2_);
    }
    
    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if(keyCode == 1) {
            try {
                Visualize.saveSettings();
            } catch(IOException e1) {
                e1.printStackTrace();
            }
        }
        super.keyTyped(typedChar, keyCode);
        
    }
    
    /**
     * Called by the controls from the buttonList when activated. (Mouse pressed for buttons)
     */
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.enabled) {
            if(button.id < 100 && button instanceof GuiOptionButton) {
                GameSettings.Options gamesettings$options = ((GuiOptionButton) button).returnEnumOptions();
                settings.setOptionValue(gamesettings$options, 1);
                button.displayString = this.settings.getKeyBinding(GameSettings.Options.getEnumOptions(button.id));
            }
            
            if(button.id == 108) {
                this.mc.world.getWorldInfo().setDifficulty(EnumDifficulty.getDifficultyEnum(this.mc.world.getDifficulty().getDifficultyId() + 1));
                this.difficultyButton.displayString = this.getDifficultyText(this.mc.world.getDifficulty());
            }
            
            if(button.id == 109) {
                this.mc.displayGuiScreen(new GuiYesNo(this, (new TextComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new TextComponentTranslation("difficulty.lock.question", new Object[]{new TextComponentTranslation(this.mc.world.getWorldInfo().getDifficulty().getDifficultyResourceKey(), new Object[0])})).getFormattedText(), 109));
            }
            
            if(button.id == 110) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiCustomizeSkin(this));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 101) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiVideoSettings(this, this.settings));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 100) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiControls(this, this.settings));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 102) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiLanguage(this, this.settings, this.mc.getLanguageManager()));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 103) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new ScreenChatOptions(this, this.settings));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 104) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiSnooper(this, this.settings));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 200) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(lastScreen);
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 105) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenResourcePacks(this));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
            
            if(button.id == 106) {
                this.mc.gameSettings.saveOptions();
                this.mc.displayGuiScreen(new GuiScreenOptionsSounds(this, this.settings));
                try {
                    Visualize.saveSettings();
                } catch(IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
