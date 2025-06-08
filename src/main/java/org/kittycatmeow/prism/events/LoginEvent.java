package org.kittycatmeow.chance.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kittycatmeow.chance.ItemManip;

public class LoginEvent implements Listener {
    @EventHandler
    public void LoginHandlesReplace(PlayerJoinEvent event) {
        ItemManip.replacePower(event.getPlayer());
    }
}
