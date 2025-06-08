package org.kittycatmeow.prism.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.kittycatmeow.prism.ItemManip;

public class DropEvent implements Listener {
    @EventHandler
    public void DropHandlesCancel(PlayerDropItemEvent event) {
        if (ItemManip.isPower(event.getItemDrop().getItemStack()))
            event.setCancelled(true);
    }
}
