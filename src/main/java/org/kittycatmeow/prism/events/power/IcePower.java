package org.kittycatmeow.prism.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.prism.power.*;
import org.kittycatmeow.prism.PrismItemLibrary;

public class IcePower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.ICE, PassivePowers.GLACIAL_HEALING);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEntityEvent event) {
        AggressivePower.ExecuteWithCooldown(event, PrismItemLibrary.Ids.ICE, AggressivePowers.PERMAFROST, InteractEntityAggressivePowers.PERMAFROST);
    }
}