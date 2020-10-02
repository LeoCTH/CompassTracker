package com.leocth.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

public final class SerializationUtils {

    public static OfflinePlayer getOfflinePlayer(int[] arr) {
        if (arr != null) {
            if (arr.length == 4) {
                return Bukkit.getOfflinePlayer(SerializationUtils.intArray2Uuid(arr));
            }
            else {
                CompassTracker.LOGGER.warning("Invalid length for UUID array: " + arr.length);
            }
        }
        return null;
    }

    public static int[] uuid2IntArray(UUID uuid) {
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        return new int[]{(int)(most >> 32), (int)most, (int)(least >> 32), (int)least};
    }

    public static UUID intArray2Uuid(int[] array) {
        if (array.length != 4) // just in case (tm)
            throw new IllegalArgumentException("array has wrong size: " + array.length + "!");
        return new UUID(((long)array[0] << 32) | (long)array[1] & 4294967295L, ((long)array[2] << 32) | (long)array[3] & 4294967295L);
    }

    public static int[] loc2IntArray(Location loc) {
        return new int[]{loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()};
    }

    /*
     * Warning: does NOT contain world info!
     */
    public static Location intArray2Loc(int[] vec) {
        if (vec.length != 3)
            throw new IllegalArgumentException("array has wrong size: " + vec.length + "!");
        return new Location(null, vec[0], vec[1], vec[2]);
    }
}
