package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;

import java.util.UUID;

public class ToggleInHandSneaking implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player p)) {
            Prism.sendPrefixedMessage(sender, "You must be a player to use this command");
            return true;
        }
        UUID uuid = p.getUniqueId();
        boolean newState = !Prism.getInHandSneakingDataHandler().get(uuid.toString());
        Prism.getInHandSneakingDataHandler().set(uuid.toString(), newState);
        Prism.sendPrefixedMessage(sender, "In-hand sneaking for passive abilities is now "+(newState ? "enabled" : "disabled"+'.'));
        return true;
    }
}
