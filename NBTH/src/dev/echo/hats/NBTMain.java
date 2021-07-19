package dev.echo.hats;

import dev.echo.hats.commands.NbtHatCommand;
import dev.echo.hats.events.Listeners;
import dev.echo.hats.filemanager.Config;
import dev.echo.hats.filemanager.DisabledMaterials;
import dev.echo.hats.utils.Utility;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NBTMain extends JavaPlugin {

    public static String PREFIX = Utility.color("&3[&bNBTHATS&3]&c ");
    public static String ERROR_PREFIX = Utility.color("&4[&cNBTHATS&4]&c ");

    public static NBTMain instance;

    private Listeners listeners;
    private DisabledMaterials disabledMaterials;

    @Override
    public void onEnable(){
        super.onEnable();

        instance = this;

        listeners = new Listeners();

        Bukkit.getPluginManager().registerEvents(listeners,this);

        new Config(this);

        this.disabledMaterials = new DisabledMaterials(this);
        disabledMaterials.createFile();

        registerCommands();

    }

    @Override
    public void onDisable() {
        super.onDisable();

    }
    public void registerCommands(){

        this.getCommand("nbthats").setExecutor(new NbtHatCommand());
        this.getCommand("nbthats").setTabCompleter(new NbtHatCommand());

    }

    public Listeners getListeners() {
        return listeners;
    }

    public DisabledMaterials getDisabledMaterials() {
        return disabledMaterials;
    }
}
