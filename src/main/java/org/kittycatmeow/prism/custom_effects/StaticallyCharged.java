package org.kittycatmeow.prism.custom_effects;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kittycatmeow.prism.CustomEffectHandler;
import org.kittycatmeow.prism.ParticleHelper;

public class StaticallyCharged implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (!(event.getDamager() instanceof LivingEntity d)) return;
        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.STATICALLY_CHARGED, p)) {
            d.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 1));
            d.setNoDamageTicks(0);
            d.damage(event.getDamage() * 0.25, p);
            d.setNoDamageTicks(0);
            ParticleHelper.DrawLine(p.getLocation().add(0, 1, 0), d.getLocation().add(0, 1, 0), Particle.ELECTRIC_SPARK, (int) (10 * p.getLocation().distance(d.getLocation())), 5);
        }
    }
}
