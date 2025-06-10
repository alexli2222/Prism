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
import org.kittycatmeow.prism.perishable_data_storage.CooldownStorage;
import org.kittycatmeow.prism.perishable_data_storage.custom_effects.StaticallyChargedStorage;

public class StaticallyCharged implements Listener {
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player p)) return;
        if (!(event.getDamager() instanceof LivingEntity d)) return;

        if (CustomEffectHandler.HasCustomEffect(CustomEffectHandler.CustomEffects.STATICALLY_CHARGED, p)) {
            StaticallyChargedStorage cdstorage = (StaticallyChargedStorage) CustomEffectHandler.CustomEffects.STATICALLY_CHARGED.getStorage();
            long since = CooldownStorage.since(cdstorage.cooldowns.getOrDefault(p.getUniqueId(), 0L));
            if (since < 100L) return;
            d.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 40, 1));
            d.setNoDamageTicks(0);
            d.damage(event.getDamage() * 0.35);
            d.setNoDamageTicks(0);
            ParticleHelper.DrawLine(p.getLocation().add(0, 1, 0), d.getLocation().add(0, 1, 0), Particle.ELECTRIC_SPARK, 5);
            cdstorage.cooldowns.put(p.getUniqueId(), System.currentTimeMillis());
        }
    }
}
