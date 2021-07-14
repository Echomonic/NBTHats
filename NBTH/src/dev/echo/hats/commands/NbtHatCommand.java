package dev.echo.hats.commands;

import dev.echo.hats.filemanager.Config;
import dev.echo.hats.utils.NBTHelmetCommand;
import dev.echo.hats.utils.Utility;
import lombok.SneakyThrows;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.minecraft.server.v1_8_R3.NBTTagInt;
import net.minecraft.server.v1_8_R3.PacketPlayInSetCreativeSlot;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import static dev.echo.hats.utils.Utility.*;
public class NbtHatCommand extends NBTHelmetCommand {

    @SneakyThrows
    @Override
    public boolean execute(CommandSender sender, String[] args) {
        if(!(sender instanceof Player))
            sender.sendMessage(color("&CThis command is for players only!"));

        assert sender instanceof Player;

        Player player = (Player) sender;

        if(!player.hasPermission("nbthats.command"))
            player.sendMessage(Config.getPermissionMessage());

        assert player.hasPermission("nbthats.command");

        if(args.length >= 1){

            if(args[0].equalsIgnoreCase("help")){
                player.sendMessage(color("&3&l&----------------------"));
                player.sendMessage(color("&b/nbthats sethelmet <true : false>"));
                player.sendMessage(color("&b/nbthats config <option> <disable|enable>"));
                player.sendMessage(color("&b/nbthats gui"));
                player.sendMessage(color("&3&l----------------------"));
            }
            if(args[0].equalsIgnoreCase("sethelmet")){
                try{
                    ItemStack value = player.getInventory().getItemInHand();

                    net.minecraft.server.v1_8_R3.ItemStack changed = CraftItemStack.asNMSCopy(value);

                    if (args[1].equalsIgnoreCase("true")) {

                        if(hasTag("isHelmet",0,changed)) {
                            addTag("isHelmet", new NBTTagInt(1), changed);

                            player.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(changed));
                            player.sendMessage(color("&aYour item is now a helmet!"));

                        }else{
                            player.sendMessage(color("&cThis item is already a helmet!"));
                        }
                    } else if (args[1].equalsIgnoreCase("false")) {
                        if(hasTag("isHelmet",1,changed)) {

                            removeTag("isHelmet", changed);

                            player.getInventory().setItemInHand(CraftItemStack.asBukkitCopy(changed));
                            player.sendMessage(color("&aYour item is now not a helmet!"));
                        }else{
                            player.sendMessage(color("&cThis item isn't a helmet!"));
                        }
                    }
                }catch (Exception exception){
                    player.sendMessage(Utility.color("&3Usage:&b /nbthats sethelmet <true : false>"));
                    return true;
                }
            }else if(args[0].equalsIgnoreCase("config")){
                player.sendMessage(color("&cThis command argument is still currently under development, or there are options to set!"));
            }

        }else{
            sendClickableHoverText("&b/nbthats help", ClickEvent.Action.RUN_COMMAND, HoverEvent.Action.SHOW_TEXT,"/nbthats help","&b&l&k||&r &3Click this to run &b/nbthats help&3! &b&l&k||",player);
        }

        return true;
    }
}
