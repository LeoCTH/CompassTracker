package com.leocth.compasstracker;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class BindCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cYou're not a player!");
            return true;
        }
        Player player = (Player) sender;
        ItemStack stack = player.getInventory().getItemInMainHand();
        if (stack.getType() == Material.COMPASS) {
            ItemMeta meta = stack.getItemMeta();
            if (meta != null) {
                switch (args.length) {
                    case 0:
                        return this.debind(stack, meta, player);
                    case 1:
                        return this.bind(stack, meta, player, args[0]);
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

    private boolean debind(ItemStack stack, ItemMeta meta, Player player) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        pdc.remove(CompassTracker.COMPASS_TARGET);
        player.sendMessage("§aCompass unbound!");
        meta.setDisplayName(null);
        MetaUtils.resetCompassTarget(meta, player);
        stack.setItemMeta(meta);
        return true;
    }

    private boolean bind(ItemStack stack, ItemMeta meta, Player player, String targetName) {
        PersistentDataContainer pdc = meta.getPersistentDataContainer();
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            player.sendMessage("§cCould not find the player: " + targetName + "!");
            return true;
        }
        pdc.set(CompassTracker.COMPASS_TARGET, PersistentDataType.INTEGER_ARRAY, SerializationUtils.uuid2IntArray(target.getUniqueId()));
        player.spigot().sendMessage(Texts.getBindingText(target));
        MetaUtils.setLocated(meta, player);
        stack.setItemMeta(meta);
        return true;
    }
}
