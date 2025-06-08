package org.kittycatmeow.prism.events;

import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.kittycatmeow.prism.ItemManip;

public class DyeSheepEvent implements Listener {
    @EventHandler
    public void DyeSheepHandlesCancel(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof Sheep)) return;
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (ItemManip.isPower(item))
            event.setCancelled(true);
    }
}
