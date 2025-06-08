package org.kittycatmeow.chance.custom_effects;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.kittycatmeow.chance.CustomEffectHandler;

public class Vulnerable implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.VULNERABLE, p))
            event.setDamage(event.getDamage() * 1.2);
    }
}
