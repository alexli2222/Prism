package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ItemManip;

public class ReloadPowers implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Chance.getPlugin().getServer().getOnlinePlayers().forEach(ItemManip::replacePower);
        Chance.sendPrefixedMessage(sender, "Reloaded powers");
        return true;
    }
}
