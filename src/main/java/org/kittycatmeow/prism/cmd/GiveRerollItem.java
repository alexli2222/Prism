package org.kittycatmeow.chance.cmd;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.Powers;

public class GiveRerollItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 1) {
            Chance.sendPrefixedMessage(sender, "Not enough args");
            return true;
        }
        if (args.length > 2) {
            Chance.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        Player p = Bukkit.getPlayer(args[0]);
        if (p == null) {
            Chance.sendPrefixedMessage(sender, "Player not found");
            return true;
        }
        int count = 1;
        if (args.length > 1) {
            try {
                count = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                Chance.sendPrefixedMessage(sender, "Argument 1 must be a number or nothing");
                return true;
            }
        }
        if (count < 1) {
            Chance.sendPrefixedMessage(sender, "Argument 1 must be a positive number");
            return true;
        }
        Powers.giveRerollItem(p, count);
        Chance.sendPrefixedMessage(sender, "You have given "+count+" reroll item"+(count == 1 ? "" : "s")+" to "+p.getName());
        return true;
    }
}
