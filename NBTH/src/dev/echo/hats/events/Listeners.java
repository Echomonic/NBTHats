package dev.echo.hats.events;

import dev.echo.hats.NBTMain;
import dev.echo.hats.filemanager.Config;
import dev.echo.hats.utils.Utility;
import net.minecraft.server.v1_8_R3.CreativeModeTab;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scheduler.BukkitRunnable;

public class Listeners implements Listener {

    private PacketPlayOutChat maintext;

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        try {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

                ItemStack itemStack = Utility.convertToItem(player.getInventory().getItemInHand());
                NBTTagCompound compound = (itemStack.hasTag()) ? itemStack.getTag() : new NBTTagCompound();


                if (compound.getInt("isHelmet") >= 1) {

                    int slot = player.getInventory().getHeldItemSlot();

                    player.getInventory().setHelmet(Utility.convertToBukkitItem(itemStack));
                    player.getInventory().clear(slot);


                }else{
                    if(Config.buildPermission()) {
                        return;
                    }
                }

            }else{
                if(Config.buildPermission()) {
                    return;
                }
            }
        }catch (Exception e){
            if(Config.buildPermission()) {
                return;
            }
        }
        event.setCancelled(true);
    }

    public PacketPlayOutChat getChatText() {
        return maintext;
    }
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();


        try {

            if (Config.headSlotClick()) {


                if (player.getGameMode() == GameMode.SURVIVAL) {
                    if (event.getSlot() == 39) {
                        ItemStack cursor = Utility.convertToItem(event.getCursor());
                        NBTTagCompound compound = Utility.getNBTTagCompound(cursor);

                        PlayerInventory inventory = (PlayerInventory) event.getView().getBottomInventory();

                        if (compound.getInt("isHelmet") == 1) {
                            org.bukkit.inventory.ItemStack headItem = inventory.getHelmet();


                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    player.getInventory().setHelmet(Utility.convertToBukkitItem(cursor));
                                    cancel();
                                }
                            }.runTaskTimer(NBTMain.instance, 0, 1);

                            event.setCursor(headItem);
                        }


                        event.setResult(Event.Result.ALLOW);
                    }
                }
                if(event.getInventory() instanceof InventoryCreativeEvent){

                    if (event.getSlot() == 5) {
                        ItemStack cursor = Utility.convertToItem(event.getCursor());
                        NBTTagCompound compound = Utility.getNBTTagCompound(cursor);

                        PlayerInventory inventory = (PlayerInventory) event.getView().getBottomInventory();

                        if (compound.getInt("isHelmet") == 1) {
                            org.bukkit.inventory.ItemStack headItem = inventory.getHelmet();

                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    player.getInventory().setHelmet(Utility.convertToBukkitItem(cursor));
                                    cancel();
                                }
                            }.runTaskTimer(NBTMain.instance, 0, 1);

                            event.setCursor(headItem);
                        }


                        event.setResult(Event.Result.ALLOW);
                    }
                }

                if(event.getClick().isShiftClick()){
                    ItemStack cursor = Utility.convertToItem(event.getCurrentItem());
                    NBTTagCompound compound = Utility.getNBTTagCompound(cursor);

                    PlayerInventory inventory = (PlayerInventory) event.getView().getBottomInventory();

                    if (compound.getInt("isHelmet") == 1) {
                        org.bukkit.inventory.ItemStack headItem = inventory.getHelmet();


                        new BukkitRunnable() {

                            @Override
                            public void run() {
                                player.getInventory().setHelmet(Utility.convertToBukkitItem(cursor));
                                cancel();
                            }
                        }.runTaskTimer(NBTMain.instance, 0, 1);

                        event.setCursor(headItem);
                        event.setCurrentItem(new org.bukkit.inventory.ItemStack(Material.AIR));
                        event.setCancelled(true);
                        return;
                    }
                }
            }
        }catch (Exception e){
            return;
        }
    }



//   this.maintext = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("[\"\",{\"text\":\"The item you're are currently holding is not a helmet, would you like to make it one? \",\"color\":\"aqua\"},{\"text\":\"[\",\"color\":\"dark_aqua\"},{\"text\":\"Y\",\"color\":\"green\",\"bold\":\"true\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/nbthats sethelmet true\"}},{\"text\":\"/\",\"color\":\"dark_aqua\",\"bold\":\"false\"},{\"text\":\"N\",\"color\":\"red\",\"bold\":\"true\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"/nbthats sethelmet false\"}},{\"text\":\"]\",\"color\":\"dark_aqua\",\"bold\":\"false\"}]"));
//            if (!Utility.hasTag("isHelmet", itemStack) && Config.sendNBTMessage()) {
//                PlayerConnection connection = ((CraftPlayer) player).getHandle().playerConnection;
//                connection.sendPacket(maintext);
//                return;
//            }
}