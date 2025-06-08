package org.kittycatmeow.prism.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.power.*;

public class FirePower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.FIRE, PassivePowers.SCORCHED_SKIN);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEvent event) {
        AggressivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.FIRE, AggressivePowers.NETHERS_BLESSING, InteractAggressivePowers.NETHERS_BLESSING);
    }
}