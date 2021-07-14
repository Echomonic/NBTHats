package dev.echo.hats;

import dev.echo.hats.commands.NbtHatCommand;
import dev.echo.hats.events.Listeners;
import dev.echo.hats.filemanager.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NBTMain extends JavaPlugin {

    public static NBTMain instance;
    private Listeners listeners;
    @Override
    public void onEnable(){
        super.onEnable();

        instance = this;

        listeners = new Listeners();

        Bukkit.getPluginManager().registerEvents(listeners,this);

        new Config(this);

        registerCommands();

    }

    @Override
    public void onDisable() {
        super.onDisable();
    }
    public void registerCommands(){

        this.getCommand("nbthats").setExecutor(new NbtHatCommand());

    }

    public Listeners getListeners() {
        return listeners;
    }
}
