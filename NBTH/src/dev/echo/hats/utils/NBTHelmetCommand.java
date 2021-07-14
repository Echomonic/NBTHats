package dev.echo.hats.utils;

import dev.echo.hats.NBTMain;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import java.util.List;

public abstract class NBTHelmetCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] strings) {
        execute(commandSender,strings);
        return false;
    }
    public abstract boolean execute(CommandSender sender,String[] args);

}
