package org.kittycatmeow.chance.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.chance.ChanceItemLibrary;
import org.kittycatmeow.chance.power.*;

public class WaterPower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.WATER, PassivePowers.REFRESH);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEntityEvent event) {
        AggressivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.WATER, AggressivePowers.WHIRLPOOL, InteractEntityAggressivePowers.WHIRLPOOL);
    }
}