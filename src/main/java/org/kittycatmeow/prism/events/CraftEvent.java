package org.kittycatmeow.prism.events;

import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;

public class CraftEvent implements Listener {
    @EventHandler
    public void CraftHandlesCancel(CraftItemEvent event) {
        NamespacedKey key = new NamespacedKey("prism", "power");
        for (ItemStack item : event.getInventory().getMatrix()) {
            if (item == null || !item.hasItemMeta()) continue;
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            if (container.has(key)) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
