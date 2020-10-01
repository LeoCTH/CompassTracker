package com.leocth.compasstracker;

import java.util.UUID;

public final class SerializationUtils {
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
}
