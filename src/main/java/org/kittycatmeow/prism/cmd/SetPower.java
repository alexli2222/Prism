package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.Powers;

public class SetPower implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 2) {
            Prism.sendPrefixedMessage(sender, "Not enough args");
            return true;
        }
        if (args.length > 2) {
            Prism.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        Player p = Prism.getPlugin().getServer().getPlayer(args[0]);
        if (p == null) {
            Prism.sendPrefixedMessage(sender, "Player not found");
            return true;
        }
        try {
            PrismItemLibrary.Ids id = PrismItemLibrary.Ids.valueOf(args[1].toUpperCase());
            Powers.setPower(p, id);
            Prism.sendPrefixedMessage(sender, "Power for "+args[0]+" set to "+id);
            return true;
        } catch (Exception e) {
            Prism.sendPrefixedMessage(sender, "Invalid power ID");
            return true;
        }
    }
}
