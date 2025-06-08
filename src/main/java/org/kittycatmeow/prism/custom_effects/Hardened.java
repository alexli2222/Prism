package org.kittycatmeow.prism.custom_effects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.kittycatmeow.prism.CustomEffectHandler;

public class Hardened implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.HARDENED, p))
            event.setDamage(event.getDamage() * 0.85);
    }
}
