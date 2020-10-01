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
            .create();

    public static final BaseComponent[] TRACE_OTHER_DIM_TEXT
            = new ComponentBuilder()
            .color(ChatColor.LIGHT_PURPLE)
            .append("TRACE INTERRUPTED")
            .bold(true)
            .append(" - target is in another dimension!")
            .create();

    public static BaseComponent[] getTrackingText(Player player) {
        return new ComponentBuilder()
                .color(ChatColor.GREEN)
                .append("Targeting ")
                .append(player.getDisplayName())
                .bold(true)
                .append("'s last position!")
                .create();
    }
}
