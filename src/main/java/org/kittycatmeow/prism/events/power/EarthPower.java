package org.kittycatmeow.prism.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.power.*;

public class EarthPower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.EARTH, PassivePowers.TERRASHIELD);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEntityEvent event) {
        AggressivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.EARTH, AggressivePowers.MOUNTAINS_WEIGHT, InteractEntityAggressivePowers.MOUNTAINS_WEIGHT);
    }
}