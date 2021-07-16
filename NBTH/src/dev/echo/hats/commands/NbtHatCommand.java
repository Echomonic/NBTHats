package dev.echo.hats.commands;

import dev.echo.hats.NBTMain;
import dev.echo.hats.filemanager.Config;
import dev.echo.hats.utils.ConfigTypes;
import dev.echo.hats.utils.NBTHelmetCommand;
import dev.echo.hats.utils.Utility;
import lombok.SneakyThrows;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static dev.echo.hats.NBTMain.*;

public class NbtHatCommand extends NBTHelmetCommand {



    @SneakyThrows
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player))
            if(args.length >= 1) {
                if (args[0].equalsIgnoreCase("config")) {
                    try {
                        StringBuilder builder = new StringBuilder();
                        for (int i = 3; i < args.length; i++) {
                            builder.append(args[i]).append(' ');
                        }
                        String configOption = ConfigTypes.valueOf(args[1]).getType() + "." + args[2];
                        String valueSet = builder.toString();

                        if (NBTMain.instance.getConfig().getString(configOption) != null) {
                            sender.sendMessage(Utility.color("&3Config:&b Set " + configOption.replace(ConfigTypes.valueOf(args[1]).getType() + ".", "") + " to " + valueSet));
                            NBTMain.instance.getConfig().set(configOption, valueSet);
                            NBTMain.instance.saveConfig();
                        } else {
                            sender.sendMessage(Utility.getErrorMessage("failed"));
                        }
                    } catch (Exception e) {
                        sender.sendMessage(Utility.getErrorMessage("command-runtime-error"));
                        return true;
                    }
                }
            } else {
                sender.sendMessage(Utility.color("/nbthats config <config type> <The thing you want to set it to>"));
                return true;
            }
        assert sender instanceof Player;

        Player player = (Player) sender;

        if(!player.hasPermission("nbthats.command")) {
            player.sendMessage(Config.getPermissionMessage());
        }else {

            if (args.length >= 1) {

                if (args[0].equalsIgnoreCase("help")) {
                    player.sendMessage(Utility.color("&3&l&----------------------"));
                    player.sendMessage(Utility.color("&b/nbthats sethelmet <true : false>"));
                    player.sendMessage(Utility.color("&b/nbthats config <option> <disable|enable>"));
                    player.sendMessage(Utility.color("&3&l----------------------"));
                }
                if (args[0].equalsIgnoreCase("sethelmet")) {

                        if(player.getInventory().getItemInHand() != null) {


                                ItemStack value = player.getInventory().getItemInHand();

                                net.minecraft.server.v1_8_R3.ItemStack changed = CraftItemStack.asNMSCopy(value);

                                if (args[1].equalsIgnoreCase("true")) {

                                    if (Utility.hasTag("isHelmet", 0, changed)) {
                                        Utility.addTag("isHelmet", new NBTTagInt(1), changed);

                                        player.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(changed));
                                        player.sendMessage(Utility.color(NBTMain.PREFIX + "&aYour item is now a helmet!"));

                                    } else {
                                        player.sendMessage(Utility.color(NBTMain.ERROR_PREFIX + "&cThis item is already a helmet!"));
                                    }
                                } else if (args[1].equalsIgnoreCase("false")) {
                                    if (Utility.hasTag("isHelmet", 1, changed)) {

                                        Utility.removeTag("isHelmet", changed);

                                        player.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(changed));
                                        player.sendMessage(Utility.color(PREFIX + "&aYour item is now not a helmet!"));
                                    } else {
                                        player.sendMessage(Utility.color(ERROR_PREFIX + "&cThis item isn't a helmet!"));
                                    }
                                }
                        }else{
                            player.sendMessage(NBTMain.ERROR_PREFIX + ChatColor.RED + "You're not holding an item!");
                        }

                } else if (args[0].equalsIgnoreCase("config")) {
                    try {
                        StringBuilder builder = new StringBuilder();
                        for(int i = 3; i < args.length; i++){
                            builder.append(args[i]).append(' ');
                        }
                        String configOption = ConfigTypes.valueOf(args[1]).getType() + "." + args[2];
                        String valueSet = builder.toString();

                        if (NBTMain.instance.getConfig().getString(configOption) != null) {
                            player.sendMessage(Utility.color("&3Config:&b Set " + configOption.replace(ConfigTypes.valueOf(args[1]).getType() + ".","") + " to " + valueSet));
                            NBTMain.instance.getConfig().set(configOption, valueSet);
                            NBTMain.instance.saveConfig();
                        } else {
                            player.sendMessage(NBTMain.ERROR_PREFIX + Utility.getErrorMessage("failed"));
                        }
                    }catch (Exception e){
                        player.sendMessage(NBTMain.ERROR_PREFIX + Utility.getErrorMessage("command-runtime-error"));
                        return true;
                    }
                }

            } else {
                if (player.hasPermission("nbthats.command")) {
                    Utility.sendClickableHoverText("&b/nbthats help", ClickEvent.Action.RUN_COMMAND, HoverEvent.Action.SHOW_TEXT, "/nbthats help", "&b&l&k||&r &3Click this to run &b/nbthats help&3! &b&l&k||", player);
                }
            }
        }
        return true;
    }

    @Override
    public List<String> tab(CommandSender sender,String cmd, String[] args) {
        List<String> tabComplete = new ArrayList<>();
        if(cmd.equalsIgnoreCase("nbthats")) {
            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("sethelmet")) {
                    tabComplete.addAll(Arrays.asList("true", "false"));
                } else if (args[0].equalsIgnoreCase("config")) {
                    for (ConfigTypes configTypes : ConfigTypes.values()) {
                        tabComplete.addAll(Arrays.asList(configTypes.getType()));
                        if(configTypes.getType() == ConfigTypes.options.getType()){
                            tabComplete.addAll(Arrays.asList("true", "false"));
                        }
                    }

                }

            }
        }
        return tabComplete;
    }
}
