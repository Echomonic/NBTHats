package dev.echo.hats.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigurationUtil {
    public static void save(File file, YamlConfiguration configuration){

        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void save(File file, FileConfiguration configuration){

        try {
            configuration.save(file);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void create(File file, JavaPlugin plugin, String child){

        file = new File(plugin.getDataFolder(),child);

        if(!isFileCreated(file)){
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void create(File file){

        try {
            file.createNewFile();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

    public static void create(File file,String child){

        if(!isFileCreated(file)){
            file = new File(child);
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

    }
    public static void createDir(File file){

        if(!isFileCreated(file)){
            file.mkdir();
        }

    }
    public static void createDataFolder(JavaPlugin plugin){
        if(!isFileCreated(plugin.getDataFolder())){
            createDir(plugin.getDataFolder());
        }
    }

    public static boolean isFileCreated(File file){

        if(file.exists()) {
            return true;
        }
        return false;
    }
    public static Player getAllPlayers(){

        Player player = Bukkit.getOnlinePlayers().iterator().next().getPlayer();

        return player;
    }

    public static JavaPlugin getInstance(Class clazz){

        if(clazz.getName() == null || clazz.getName().equals("")){

            throw new NullPointerException();

        }

        return JavaPlugin.getPlugin(clazz);
    }

    public static Class<?> getClazz(Class clazz){


        if(clazz.getName() == null || clazz.getName().equals("")){
            throw new NullPointerException();
        }

        return clazz;
    }
    public static YamlConfiguration loadConfiguration(File file){


        return YamlConfiguration.loadConfiguration(file);
    }

}
