package com.leocth.compasstracker;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;

// TODO this entire thing is a massive todo
public final class Texts {

    public static final BaseComponent[] TRACE_OFFLINE_TEXT
            = new ComponentBuilder()
            .color(ChatColor.RED)
            .append("TRACE LOST")
            .bold(true)
            .append(" - target is offline!")
            .bold(false)
            .create();

    public static final BaseComponent[] TRACE_OTHER_DIM_TEXT
            = new ComponentBuilder()
            .color(ChatColor.LIGHT_PURPLE)
            .append("TRACE INTERRUPTED")
            .bold(true)
            .append(" - target is in another dimension!")
            .bold(false)
            .create();

    public static BaseComponent[] getBindingText(Player target) {
        return new ComponentBuilder()
                .color(ChatColor.GREEN)
                .append("Compass bound to track ")
                .append(target.getDisplayName())
                .bold(true)
                .create();
    }

    public static BaseComponent[] getTrackingText(Player target) {
        return new ComponentBuilder()
                .color(ChatColor.GREEN)
                .append("Targeting ")
                .append(target.getDisplayName())
                .bold(true)
                .append("'s last position!")
                .bold(false)
                .create();
    }
}
