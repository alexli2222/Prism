package org.kittycatmeow.prism.events;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.kittycatmeow.prism.PrismItemLibrary;
import org.kittycatmeow.prism.Powers;

public class RerollEvent implements Listener {
    @EventHandler
    public void InteractHandlesReroll(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey("prism", "reroll"))) return;
        Player p = event.getPlayer();
        PrismItemLibrary.Ids id = Powers.randomPower(event.getPlayer());
        Powers.sendRerollMessage(p, id);
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
        event.getItem().subtract();
    }
}
