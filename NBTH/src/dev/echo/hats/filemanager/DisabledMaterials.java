package dev.echo.hats.filemanager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;

import static dev.echo.hats.utils.ConfigurationUtil.*;

public class DisabledMaterials {

    private JavaPlugin plugin;

    public DisabledMaterials(){

    }

    public DisabledMaterials(JavaPlugin plugin){
        setPlugin(plugin);
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private File file;
    private YamlConfiguration configuration;

    public void createFile(){
        file = new File(plugin.getDataFolder(),"disabledMaterials.yml");

        if(!isFileCreated(file)){
            create(file);
        }
        configuration = loadConfiguration(file);

        createData();

    }
    private void createData(){

            if (configuration.getConfigurationSection("materials") == null) {
                configuration.createSection("materials");
                configuration.getConfigurationSection("materials").set("disabled", new ArrayList<String>());
                save(file, configuration);
            }
    }

    public boolean holdingInvalidItem(Player player){
        String string = player.getInventory().getItemInHand().getType().name();
        int data = player.getInventory().getItemInHand().getData().getData();
        if(configuration.getConfigurationSection("materials").getStringList("disabled").contains(string)){
            return true;
        }


        return false;
    }
}
