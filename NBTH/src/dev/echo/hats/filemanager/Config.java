package dev.echo.hats.filemanager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

    private static JavaPlugin plugin;

    public Config(JavaPlugin loadedPlugin){
        plugin = loadedPlugin;

        addFileParams();

    }
    public static String getPermissionMessage(){

        return ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("permissions.noPerm"));
    }
    public static boolean sendNBTMessage(){



        return plugin.getConfig().getBoolean("options.NBTMessage");
    }

    public static boolean headSlotClick() {

        return plugin.getConfig().getBoolean("options.clickInv");
    }
    public static boolean canBuild() {

        return plugin.getConfig().getBoolean("options.build");
    }

    public void addFileParams(){
        if(!(plugin.getConfig().contains("permissions") && plugin.getConfig().contains("error") && plugin.getConfig().contains("options"))) {
            plugin.getConfig().set("permissions.noPerm", "&cYou don't have permission to perform this command!");

            plugin.getConfig().set("error.failed", "&cThis isn`t a value in the config");
            plugin.getConfig().set("error.command-runtime-error", "&cThis command failed to perform!");

            plugin.getConfig().set("options.NBTMessage", false);
            plugin.getConfig().set("options.clickInv", false);
            plugin.getConfig().set("options.build", false);


            plugin.saveConfig();

        }
    }
}
