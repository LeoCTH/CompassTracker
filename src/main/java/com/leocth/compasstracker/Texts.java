package com.leocth.compasstracker;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public final class Texts {

    public static final BaseComponent[] TRACE_OFFLINE
            = new ComponentBuilder()
            .color(ChatColor.RED)
            .append("TRACE LOST")
            .bold(true)
            .append(" - target is offline!")
            .bold(false)
            .create();

    public static final BaseComponent[] PLAYER_NOT_FOUND
            = new ComponentBuilder()
            .color(ChatColor.RED)
            .append("TRACE LOST")
            .bold(true)
            .append(" - target not found in the database! Resetting...")
            .bold(false)
            .create();

    public static final BaseComponent[] TRACE_OTHER_DIM
            = new ComponentBuilder()
            .color(ChatColor.LIGHT_PURPLE)
            .append("TRACE INTERRUPTED")
            .bold(true)
            .append(" - target is in another dimension!")
            .bold(false)
            .create();

    public static final BaseComponent[] NOT_A_PLAYER
            = new ComponentBuilder()
            .color(ChatColor.RED)
            .append("You're not a player!")
            .create();

    public static final BaseComponent[] UNBIND
            = new ComponentBuilder()
            .color(ChatColor.GREEN)
            .append("Compass unbound!")
            .create();

    public static BaseComponent[] playerNotFound(String name) {
        return new ComponentBuilder()
                .color(ChatColor.RED)
                .append("Could not find the player ")
                .append(name)
                .bold(true)
                .append("!")
                .bold(false)
                .create();
    }

    public static BaseComponent[] binding(Player target) {
        return new ComponentBuilder()
                .color(ChatColor.GREEN)
                .append("Compass bound to track ")
                .append(target.getDisplayName())
                .bold(true)
                .create();
    }

    public static BaseComponent[] tracking(Player target) {
        return new ComponentBuilder()
                .color(ChatColor.GREEN)
                .append("Targeting ")
                .append(target.getDisplayName())
                .bold(true)
                .append("'s last position!")
                .bold(false)
                .create();
    }

    public static BaseComponent[] trackingItemTitle(ChatColor color, OfflinePlayer target) {
        return new ComponentBuilder()
                .append(new TranslatableComponent("item.minecraft.compass"))
                .append(" ")
                .color(color)
                .append("[TRACKING ")
                .append(target.getName())
                .bold(true)
                .append("]")
                .bold(false)
                .create();
    }

}
