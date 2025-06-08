package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;

public class Main implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Chance.sendPrefixedMessage(sender, "<green>Running Chance v"+Chance.VERSION+" by _kittycatmeow</green>");
        if (sender.isOp()) {
            Chance.sendPrefixedMessage(sender, "/getrerollitem [count (default 1)]: Get a certain number of reroll items");
            Chance.sendPrefixedMessage(sender, "/setpower <player> <power>: Set the power of a player");
            Chance.sendPrefixedMessage(sender, "/reroll <player> [-s (show result)]: Gives the designated player a random power");
            Chance.sendPrefixedMessage(sender, "/reloadpowers: Reloads the powers for all online players");
            Chance.sendPrefixedMessage(sender, "/clearcooldowns: Clears all the ability cooldowns for all players");
        }
        return true;
    }
}
