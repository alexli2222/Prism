package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.Powers;

public class Reroll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Player p;
        if (args.length > 1) {
            Prism.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        if (args.length < 1) {
            if (!(sender instanceof Player)) {
                Prism.sendPrefixedMessage(sender, "You must be a player to use this command without arguments");
                return true;
            }
            p = (Player) sender;
        } else {
            p = Prism.getPlugin().getServer().getPlayer(args[0]);
        }
        if (p == null) {
            Prism.sendPrefixedMessage(sender, "Player not found");
            return true;
        }
        PrismItemLibrary.Ids id = Powers.randomPower(p);
        Powers.sendRerollMessage(p, id);
        String message = "Random power for "+p.getName()+" set to "+id.toString();
        Prism.sendPrefixedMessage(sender, message);
        return true;
    }
}
