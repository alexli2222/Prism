package org.kittycatmeow.prism.events;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.kittycatmeow.prism.ItemManip;

public class InventoryEvent implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;
        if (player.getGameMode() == GameMode.CREATIVE) return;

        if (event.getCurrentItem() == null || event.getCurrentItem().getType().isAir()) return;
        if (!ItemManip.isPower(event.getCurrentItem())) return;

        InventoryView view = event.getView();
        Inventory topInventory = view.getTopInventory();
        if (!(topInventory instanceof CraftingInventory)) {
            event.setCancelled(true);
        }
    }
}