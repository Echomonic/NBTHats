package dev.echo.hats.utils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public abstract class NBTHelmetCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] strings) {
        execute(commandSender,strings);
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String l, String[] args) {
        return tab(sender,cmd.getLabel(),args);
    }
    public abstract List<String> tab(CommandSender sender,String label, String[] args);

    public abstract boolean execute(CommandSender sender, String[] args);

}
