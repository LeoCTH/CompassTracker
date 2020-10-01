package com.leocth.compasstracker;

import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static com.google.common.base.Preconditions.*;

/* this is a serial brainfart */
public final class CompassTracker extends JavaPlugin implements Listener {

    private static CompassTracker instance;
    public static NamespacedKey COMPASS_TARGET;

    public static CompassTracker getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        instance = this;
        COMPASS_TARGET = new NamespacedKey(instance, "compass_target");
        pm.registerEvents(this, this);
        checkNotNull(this.getCommand("cbind")).setExecutor(new BindCommand());
        checkNotNull(this.getCommand("cgive")).setExecutor(new GiveCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getMaterial() == Material.COMPASS) {
            Player player = event.getPlayer();
            // i miss my ?.'s now
            ItemStack itemStack = event.getItem();
            assert itemStack != null; // stfu idea

            ItemMeta meta = itemStack.getItemMeta();
            assert meta != null;

            PersistentDataContainer pdc = meta.getPersistentDataContainer();
            Player target = null;

            int[] arr = pdc.get(COMPASS_TARGET, PersistentDataType.INTEGER_ARRAY);
            if (arr != null) {
                if (arr.length == 4) {
                    OfflinePlayer ofp = Bukkit.getOfflinePlayer(SerializationUtils.intArray2Uuid(arr));
                    if (ofp.isOnline()) {
                        target = ofp.getPlayer();
                    }
                    else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.TRACE_OFFLINE_TEXT);
                        MetaUtils.resetCompassTarget(meta, player);
                    }
                }
                else
                    this.getLogger().warning("Invalid length for UUID array: " + arr.length);
                if (target != null) {
                    if (target.getWorld().getEnvironment() != player.getWorld().getEnvironment()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.TRACE_OTHER_DIM_TEXT);
                    }
                    else {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, Texts.getTrackingText(target));
                        player.setCompassTarget(target.getLocation());
                    }

                }
            }
            itemStack.setItemMeta(meta); // just to be sure
        }
    }
}
