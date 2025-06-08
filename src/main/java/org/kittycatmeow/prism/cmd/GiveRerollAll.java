package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.Powers;

public class GiveRerollAll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length > 1) {
            Chance.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        int count = 1;
        if (args.length > 0) {
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
        for (Player p : Chance.getPlugin().getServer().getOnlinePlayers()) {
            Powers.giveRerollItem(p, count);
        }
        return true;
    }
}
