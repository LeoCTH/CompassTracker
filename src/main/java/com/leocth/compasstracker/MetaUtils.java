package com.leocth.compasstracker;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

public final class MetaUtils {
    public static void resetCompassTarget(ItemMeta meta, Player player) {
        Location spawn = player.getBedSpawnLocation();
        if (spawn == null) {
            spawn = player.getWorld().getSpawnLocation();
        }
        player.setCompassTarget(spawn);
    }
}
