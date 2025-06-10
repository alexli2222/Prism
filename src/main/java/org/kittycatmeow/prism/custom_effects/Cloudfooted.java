package org.kittycatmeow.prism.custom_effects;

import org.bukkit.Color;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kittycatmeow.prism.CustomEffectHandler;
import org.kittycatmeow.prism.ParticleHelper;

public class Cloudfooted implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player p)) return;
        if (!event.isCritical()) return;
        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.CLOUDFOOTED, p)) {
            event.setDamage(event.getDamage() * 1.1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 2));
            p.heal(1);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WIND_CHARGE_WIND_BURST, 2, 1);
            ParticleHelper.Dust.DrawRing(event.getEntity().getLocation().add(0, 1, 0), Color.WHITE, 0.5f, 1.5f, 1);
        }
    }
    @EventHandler
    public void onLand(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.FALL) return;
        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.CLOUDFOOTED, p))
            event.setCancelled(true);
    }
}
