package com.leocth.compasstracker;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public final class MetaUtils {

    public static void setLocated(ItemMeta meta, Player player) {
        meta.setDisplayName(
                ChatColor.WHITE +
                        "Compass " +
                ChatColor.GREEN +
                        "[TRACKING " + target.getDisplayName() + "]");
        player.setCompassTarget(player.getLocation());
    }

    public static void setLost(ItemMeta meta, Player player) {
        resetCompassTarget(meta, player);
    }

    public static void setInterrupted(ItemMeta meta, Player player) {
        Location spawn = player.getBedSpawnLocation();
        if (spawn == null) {
            spawn = player.getWorld().getSpawnLocation();
        }
        player.setCompassTarget(spawn);
    }
    public static void resetCompassTarget(ItemMeta meta, Player player) {
        Location spawn = player.getBedSpawnLocation();
        if (spawn == null) {
            spawn = player.getWorld().getSpawnLocation();
        }
        player.setCompassTarget(spawn);
    }
}
