package com.leocth.compasstracker;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.COMPASS) {
            Player user = event.getPlayer();
            ItemStack stack = event.getItem();
            ItemMeta meta = stack != null ? stack.getItemMeta() : null;
            if (meta == null)
                return;

            PersistentDataContainer pdc = meta.getPersistentDataContainer();

            int[] arr = pdc.get(CompassTracker.COMPASS_TARGET, PersistentDataType.INTEGER_ARRAY);
            OfflinePlayer ofp = SerializationUtils.getOfflinePlayer(arr);

            if (ofp == null) {
                return;
            }
            else if (ofp.getPlayer() == null) {
                user.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.TRACE_OFFLINE);
                MetaUtils.setLost(meta, user, ofp);
            }
            else {
                Player target = ofp.getPlayer();
                if (target.getWorld().getEnvironment() != user.getWorld().getEnvironment()) {
                    // if in another dimension
                    user.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.TRACE_OTHER_DIM);
                    MetaUtils.setInterrupted(meta, user, target);
                }
                else {
                    user.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.tracking(target));
                    MetaUtils.setLocated(meta, user, target);
                }
            }
            stack.setItemMeta(meta); // just to be sure
        }
    }
}
