package org.kittycatmeow.chance.events;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ChanceItemLibrary;
import org.kittycatmeow.chance.Powers;

public class RerollEvent implements Listener {
    @EventHandler
    public void InteractHandlesReroll(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        if (!item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey("chance", "reroll"))) return;
        Player p = event.getPlayer();
        ChanceItemLibrary.Ids id = Powers.randomPower(event.getPlayer());
        Chance.sendPrefixedMessage(p,
                "You have rerolled your power, you now have the " +
                        MiniMessage.miniMessage().serialize(
                        Chance.getItemLibrary().lib.get(id).getItemMeta().customName()
                        )+'!'
        );
        p.getWorld().playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 2, 1);
        event.getItem().subtract();
    }
}
