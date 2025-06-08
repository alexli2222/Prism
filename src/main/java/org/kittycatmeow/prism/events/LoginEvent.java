package org.kittycatmeow.prism.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.kittycatmeow.prism.ItemManip;
import org.kittycatmeow.prism.Powers;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.PrismItemLibrary;

public class LoginEvent implements Listener {
    @EventHandler
    public void FirstLoginHandlesRandom(PlayerJoinEvent event) {
        if (!Prism.getFirstJoinDataHandler().get(event.getPlayer().getUniqueId().toString())) {
            PrismItemLibrary.Ids id = Powers.randomPower(event.getPlayer());
            Powers.sendRerollMessage(event.getPlayer(), id);
            Prism.getFirstJoinDataHandler().set(event.getPlayer().getUniqueId().toString(), true);
        }
    }
    @EventHandler
    public void LoginHandlesReplace(PlayerJoinEvent event) {
        ItemManip.replacePower(event.getPlayer());
    }
}
