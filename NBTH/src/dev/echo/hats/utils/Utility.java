package dev.echo.hats.utils;

import dev.echo.hats.NBTMain;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTBase;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import java.lang.reflect.Constructor;

public class Utility {

    public static String color(String message){


        return ChatColor.translateAlternateColorCodes('&',message);
    }

    public static boolean hasTag(String base,ItemStack stack){
        NBTTagCompound compound = getNBTTagCompound(stack);


        return compound.get(base) == null;
    }

    public static boolean hasTag(String base,int i,ItemStack stack){
        NBTTagCompound compound = (stack.hasTag()) ? stack.getTag() : new NBTTagCompound();


        return compound.getInt(base) == i;
    }


    public static void addTag(String tag, NBTBase nbtBase, ItemStack stack){

        NBTTagCompound compound = getNBTTagCompound(stack);

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
    public static Class<?> getNMSClass(String clazz) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static Class<?> getCraftBukkitClass(String clazz) {
        try {
            return Class.forName("org.bukkit.craftbukkit." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String getErrorMessage(String message) {

        return ChatColor.translateAlternateColorCodes('&', NBTMain.instance.getConfig().getString("errors."+ message));
    }
    public void sendReflectionTitle(Player player, String titleText){
        try {
            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
            Object titleChat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a",String.class).invoke(null, "{\"text\":\"" + titleText + "\"}");

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],getNMSClass("IChatBaseComponent"),int.class,int.class,int.class);
            Object titlePacket = titleConstructor.newInstance(enumTitle,titleChat,1,20,1);

            sendPacketToPlayer(player,titlePacket);
//            PacketPlayOutChat

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void sendPacketToPlayer(Player player,Object packet){
        try {
            Object handle = player.getClass().getMethod("getHandle").invoke(player);
            Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
            playerConnection.getClass().getMethod("sendPacket",getNMSClass("Packet")).invoke(playerConnection,packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sendActionBar(Player player, String titleText){
        try {
            Object aBString = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a",String.class).invoke(null ,"{\"text\":\"" + ChatColor.translateAlternateColorCodes('&',titleText) + "\"}");

            Constructor<?> actionBarConstructor = getNMSClass("PacketPlayOutChat").getConstructor(getNMSClass("IChatBaseComponent"),byte.class);

            Object actionBarSent = actionBarConstructor.newInstance(aBString,(byte) 2);
            sendPacketToPlayer(player,actionBarSent);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
    public void resetTitle(Player player){
        try {
            Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);

            Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0],getNMSClass("IChatBaseComponent"),int.class,int.class,int.class);
            Object titlePacket = titleConstructor.newInstance(enumTitle, null,1,20,1);

            sendPacketToPlayer(player,titlePacket);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
