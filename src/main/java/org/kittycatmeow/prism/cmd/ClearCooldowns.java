
package org.kittycatmeow.chance.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.perishable_data_storage.CooldownStorage;

public class ClearCooldowns implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        CooldownStorage.Passive.Cooldowns.forEach((k, v) -> v.clear());
        CooldownStorage.InteractAggressive.Cooldowns.forEach((k, v) -> v.clear());
        CooldownStorage.InteractEntityAggressive.Cooldowns.forEach((k, v) -> v.clear());
        Chance.sendPrefixedMessage(sender, "Cleared all cooldowns");
        return true;
    }
}
