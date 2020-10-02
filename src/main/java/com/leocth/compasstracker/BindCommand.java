package com.leocth.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BindCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.spigot().sendMessage(Texts.NOT_A_PLAYER);
            return true;
        }
        Player player = (Player) sender;
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack.getType() == Material.COMPASS) {
            ItemMeta meta = stack.getItemMeta();
            if (meta != null) {
                switch (args.length) {
                    case 0:
                        return unbind(stack, meta, player);
                    case 1:
                        return bind(stack, meta, player, args[0]);
                    default:
                        return false;
                }
            }
        }
        else {
            sender.sendMessage("§cYou're not holding a compass!");
        }
        return true;
    }

    private static boolean unbind(ItemStack stack, ItemMeta meta, Player player) {
        player.spigot().sendMessage(Texts.UNBIND);
        MetaUtils.unbind(meta, player);
        stack.setItemMeta(meta);
        return true;
    }

    private static boolean bind(ItemStack stack, ItemMeta meta, Player player, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.spigot().sendMessage(Texts.playerNotFound(targetName));
            return true;
        }
        MetaUtils.bind(meta, player, target);
        stack.setItemMeta(meta);
        return true;
    }
}
