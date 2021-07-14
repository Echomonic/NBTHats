package dev.echo.hats.filemanager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Config {

    private static JavaPlugin plugin;

    public Config(JavaPlugin loadedPlugin){
        plugin = loadedPlugin;

        loadedPlugin.getConfig().options().copyDefaults(true);

        loadedPlugin.saveConfig();
    }
    public static String getPermissionMessage(){

        return ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("Permissions.noPerm"));
    }
    public static boolean sendNBTMessage(){



        return plugin.getConfig().getBoolean("options.NBTMessage");
    }

    public static boolean headSlotClick() {

        return plugin.getConfig().getBoolean("options.putHeadOnClickInv");
    }
    public static boolean buildPermission() {

        return plugin.getConfig().getBoolean("Permissions.build");
    }
}
