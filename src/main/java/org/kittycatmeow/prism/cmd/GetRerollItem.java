package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;

public class GetRerollItem implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player p)) {
            Prism.sendPrefixedMessage(sender, "You must be a player to use this command");
            return true;
        }
        if (args.length > 1) {
            Prism.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        int count = 1;
        if (args.length > 0) {
            try {
                count = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                Prism.sendPrefixedMessage(sender, "Argument 1 must be a number or nothing");
                return true;
            }
        }
        for (int i = 0; i < count; i++) {
            p.getInventory().addItem(Prism.getItemLibrary().REROLL);
        }
        Prism.sendPrefixedMessage(sender, "You have been given "+count+" reroll item(s)");
        return true;
    }
}
