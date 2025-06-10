package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.Prism;

public class Main implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Prism.sendPrefixedMessage(sender, "<green>Running <gradient:dark_purple:light_purple>Prism</gradient> v"+ Prism.VERSION+" by <light_purple>_kittycatmeow</light_purple></green>");
        Prism.sendPrefixedMessage(sender, "/prism: Plugin information, this page");
        Prism.sendPrefixedMessage(sender, "/fix: Replace your power in case of a bug or you just did something stupid");
        Prism.sendPrefixedMessage(sender, "/toggleinhandsneaking: Toggles whether or not sneaking will trigger a passive ability when your power item is not in your hand");
        if (sender.isOp()) {
            Prism.sendPrefixedMessage(sender, "/getrerollitem [count (default 1)]: Get a certain number of reroll items");
            Prism.sendPrefixedMessage(sender, "/setpower <player> <power>: Set the power of a player");
            Prism.sendPrefixedMessage(sender, "/givererollitem <player> [count (default 1)]: Give a certain number of reroll items to yourself or another player");
            Prism.sendPrefixedMessage(sender, "/givererollall [count (default 1)]: Give everyone a certain number of reroll items");
            Prism.sendPrefixedMessage(sender, "/reroll [player (default self)]: Gives a random power to yourself or another player");
            Prism.sendPrefixedMessage(sender, "/rerollall: Gives a random power to all online players");
            Prism.sendPrefixedMessage(sender, "/reloadpowers: Reloads the powers for all online players");
            Prism.sendPrefixedMessage(sender, "/clearcooldowns: Clears all the ability cooldowns for all players");
        }
        return true;
    }
}
