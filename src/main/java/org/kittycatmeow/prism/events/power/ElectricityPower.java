package org.kittycatmeow.chance.events.power;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.chance.ChanceItemLibrary;
import org.kittycatmeow.chance.power.*;

public class ElectricityPower implements Listener {
    @EventHandler
    public void Passive(PlayerToggleSneakEvent event) {
        PassivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.ELECTRICITY, PassivePowers.STATIC_SHIELD);
    }
    @EventHandler
    public void Aggressive(PlayerInteractEvent event) {
        AggressivePower.ExecuteWithCooldown(event, ChanceItemLibrary.Ids.ELECTRICITY, AggressivePowers.CURSE_OF_ZEUS, InteractAggressivePowers.CURSE_OF_ZEUS);
    }
}