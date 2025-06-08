package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ChanceItemLibrary;
import org.kittycatmeow.chance.Powers;

public class Reroll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 1) {
            Chance.sendPrefixedMessage(sender, "Not enough args");
            return true;
        }
        Player p;
        if (args.length > 2) {
            Chance.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        if (args.length == 2) {
            p = Chance.getPlugin().getServer().getPlayer(args[0]);
        }
        if (p == null) {
            Chance.sendPrefixedMessage(sender, "Player not found");
            return true;
        }
        boolean show = false;
        ChanceItemLibrary.Ids power = Powers.randomPower(p);
        String message = "Random power for "+args[0]+" set";
        if (show) message += " to "+power.toString();
        Chance.sendPrefixedMessage(sender, message);
        return true;
    }
}
