package dev.echo.hats.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.util.Optional;

public class Utility {

    public static String color(String message){


        return ChatColor.translateAlternateColorCodes('&',message);
    }

    /**
     *
     * This doesn't currently work as spigot thinks an integer 0 is nothing,
     * while we need it to check if the itemstack even has the tag at all.
     *
     * @param base the nbt tag that you want to check.
     * @param stack the nms itemstack you want to get the compound of.
     *
     * @return just a simple boolean;
     *
     */

    public static boolean hasTag(String base,ItemStack stack){
        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();


        return compound.get(base) == null;
    }
    public static boolean hasTag(String base,int i,ItemStack stack){
        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();


        return compound.getInt(base) == i;
    }
    public static boolean hasTagInt(String base,ItemStack stack){
        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();

        if(!(compound.hasKey(base))){
            return false;
        }

        return true;
    }
    public static void addTag(String tag, NBTBase nbtBase, ItemStack stack){

        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();

        compound.set(tag,nbtBase);



        stack.setTag(compound);
    }


    public static void removeTag(String tag, ItemStack stack){

        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();

        compound.remove(tag);



        stack.setTag(compound);
    }
    public static ItemStack convertToItem(org.bukkit.inventory.ItemStack stack){


        return CraftItemStack.asNMSCopy(stack);
    }
    public static org.bukkit.inventory.ItemStack convertToBukkitItem(ItemStack stack){


        return CraftItemStack.asBukkitCopy(stack);
    }
    public static void sendClickableText(String text, ClickEvent.Action action, String actionRun,Player player){
        TextComponent component = new TextComponent(color(text));
        component.setClickEvent(new ClickEvent(action,actionRun));

        player.spigot().sendMessage(component);
    }
    public static NBTTagCompound getNBTTagCompound(ItemStack stack){
        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();


        return compound;
    }
    public static void sendClickableHoverText(String text, ClickEvent.Action action, HoverEvent.Action hoverAction, String click, String hover, Player player){
        TextComponent component = new TextComponent(color(text));
        component.setClickEvent(new ClickEvent(action,click));
        component.setHoverEvent(new HoverEvent(hoverAction, new TextComponent[]{new TextComponent(color(hover))}));

        player.spigot().sendMessage(component);
    }
}
