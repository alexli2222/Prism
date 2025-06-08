package org.kittycatmeow.prism.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.kittycatmeow.prism.ItemManip;

public class DeathEvent implements Listener {
    @EventHandler
    public void DeathHandlesDestroy(PlayerDeathEvent event) {
        Player p = event.getPlayer();
        p.closeInventory();
        event.getDrops().removeIf(ItemManip::isPower);
        ItemManip.removePower(p);
    }
    @EventHandler
    public void RespawnHandlesRegive(PlayerRespawnEvent event) {
        Player p = event.getPlayer();
        ItemManip.addPower(p);
    }
}
