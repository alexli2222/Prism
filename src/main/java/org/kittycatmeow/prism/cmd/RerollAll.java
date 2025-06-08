package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Powers;
import org.kittycatmeow.prism.Prism;

public class RerollAll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (args.length > 0) {
            Prism.sendPrefixedMessage(sender, "Too many args");
            return true;
        }
        for (Player p : Prism.getPlugin().getServer().getOnlinePlayers()) {
            Powers.sendRerollMessage(p, Powers.randomPower(p));
        }
        return true;
    }
}
