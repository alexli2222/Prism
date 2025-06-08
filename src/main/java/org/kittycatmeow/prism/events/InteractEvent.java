package org.kittycatmeow.prism.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.kittycatmeow.prism.ItemManip;

public class InteractEvent implements Listener {
    @EventHandler
    public void InteractEntityHandlesCancel(PlayerInteractEntityEvent event) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (ItemManip.isPower(item))
            event.setCancelled(true);
    }
    @EventHandler
    public void InteractBlockHandlesCancel(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        if (ItemManip.isPower(event.getItem()))
            event.setCancelled(true);
    }
}
