package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ChanceItemLibrary;
import org.kittycatmeow.chance.Powers;

public class SetPower implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length < 2) {
            Chance.sendPrefixedMessage(sender, "Not enough args");
            return true;
        }
        if (args.length > 2) {
            Chance.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        Player p = Chance.getPlugin().getServer().getPlayer(args[0]);
        if (p == null) {
            Chance.sendPrefixedMessage(sender, "Player not found");
            return true;
        }
        try {
            ChanceItemLibrary.Ids id = ChanceItemLibrary.Ids.valueOf(args[1].toUpperCase());
            Powers.setPower(p, id);
            Chance.sendPrefixedMessage(sender, "Power for "+args[0]+" set to "+id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            Chance.sendPrefixedMessage(sender, "Invalid power ID");
            return true;
        }
    }
}
