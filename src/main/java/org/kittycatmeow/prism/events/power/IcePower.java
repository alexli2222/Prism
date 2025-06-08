package org.kittycatmeow.chance.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.chance.power.*;
import org.kittycatmeow.chance.ChanceItemLibrary;

public class IcePower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.ICE, PassivePowers.GLACIAL_HEALING);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEntityEvent event) {
        AggressivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.ICE, AggressivePowers.PERMAFROST, InteractEntityAggressivePowers.PERMAFROST);
    }
}