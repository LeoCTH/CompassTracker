package com.leocth.compasstracker;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public final class MetaUtils {

    public static void unbind(ItemMeta meta, Player user) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(CompassTracker.COMPASS_TARGET);
        pdc.remove(CompassTracker.LAST_POSITION);
        meta.setDisplayName(null);
        resetCompassTarget(meta, user);
    }

    public static void bind(ItemMeta meta, Player user, Player target) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.set(CompassTracker.COMPASS_TARGET, PersistentDataType.INTEGER_ARRAY, SerializationUtils.uuid2IntArray(target.getUniqueId()));
        pdc.set(CompassTracker.LAST_POSITION, PersistentDataType.INTEGER_ARRAY, SerializationUtils.loc2IntArray(target.getLocation()));
        user.spigot().sendMessage(Texts.binding(target));
        MetaUtils.setLocated(meta, user, target);
    }

    public static void setLocated(ItemMeta meta, Player user, Player target) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        MetaUtils.setTitle(meta, Texts.trackingItemTitle(ChatColor.GREEN, target));
        pdc.set(CompassTracker.LAST_POSITION, PersistentDataType.INTEGER_ARRAY, SerializationUtils.loc2IntArray(target.getLocation()));
        user.setCompassTarget(target.getLocation());
    }

    public static void setLost(ItemMeta meta, Player player, OfflinePlayer target) {
        MetaUtils.setTitle(meta, Texts.trackingItemTitle(ChatColor.RED, target));
        setTargetToLastPosition(meta, player);
    }

    public static void setInterrupted(ItemMeta meta, Player player, Player target) {
        MetaUtils.setTitle(meta, Texts.trackingItemTitle(ChatColor.LIGHT_PURPLE, target));
        setTargetToLastPosition(meta, player);
    }

    public static void resetCompassTarget(ItemMeta meta, Player player) {
        Location spawn = player.getBedSpawnLocation();
        if (spawn == null) {
            spawn = player.getWorld().getSpawnLocation();
        }
        player.setCompassTarget(spawn);
    }

    public static void setTargetToLastPosition(ItemMeta meta, Player player) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        int[] locarr = pdc.get(CompassTracker.LAST_POSITION, PersistentDataType.INTEGER_ARRAY);
        if (locarr != null) {
            Location loc = SerializationUtils.intArray2Loc(locarr);
            player.setCompassTarget(loc);
        }
    }

    public static void clearTitle(ItemMeta meta) {
        meta.setDisplayName(null);
    }

    public static void setTitle(ItemMeta meta, BaseComponent... comps) {
        if (comps != null)
            meta.setDisplayName(BaseComponent.toLegacyText(comps));
        else
            clearTitle(meta);
    }
}
