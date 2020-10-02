package com.leocth.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.spigot().sendMessage(Texts.NOT_A_PLAYER);
            return true;
        }
        if (args.length != 1) {
            return false;
        }
        Player player = (Player) sender;
        ItemStack stack = new ItemStack(Material.COMPASS);
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            String targetName = args[0];
            Player target = Bukkit.getPlayer(targetName);
            if (target == null) {
                player.spigot().sendMessage(Texts.playerNotFound(targetName));
                return true;
            }
            MetaUtils.bind(meta, player, target);
            stack.setItemMeta(meta);
        }

        player.getInventory().addItem(stack);
        return true;
    }
}
