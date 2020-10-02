package com.leocth.compasstracker;

import org.bukkit.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static com.google.common.base.Preconditions.*;

public final class CompassTracker extends JavaPlugin implements Listener {

    private static CompassTracker instance;
    public static NamespacedKey COMPASS_TARGET;
    public static NamespacedKey LAST_POSITION;
    public static Logger LOGGER;

    public static CompassTracker getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getServer().getPluginManager();
        instance = this;
        COMPASS_TARGET = getKey("compass_target");
        LAST_POSITION = getKey("last_position");
        LOGGER = this.getLogger();
        pm.registerEvents(new EventListener(), this);
        checkNotNull(this.getCommand("cbind")).setExecutor(new BindCommand());
        checkNotNull(this.getCommand("cgive")).setExecutor(new GiveCommand());
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static NamespacedKey getKey(String key) {
        return new NamespacedKey(instance, key);
    }
}
