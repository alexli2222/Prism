package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.ItemManip;

public class ReloadPowers implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Prism.getPlugin().getServer().getOnlinePlayers().forEach(ItemManip::replacePower);
        Prism.sendPrefixedMessage(sender, "Reloaded powers");
        return true;
    }
}
