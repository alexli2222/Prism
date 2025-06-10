package org.kittycatmeow.prism.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.power.*;

public class WindPower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.WIND, PassivePowers.BREEZE);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEvent event) {
        AggressivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.WIND, AggressivePowers.WIND_VEIL, InteractAggressivePowers.WIND_VEIL);
    }
}