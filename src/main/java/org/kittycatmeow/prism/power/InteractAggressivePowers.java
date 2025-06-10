package org.kittycatmeow.prism.power;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.kittycatmeow.prism.CustomEffectHandler;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.ParticleHelper;
import org.kittycatmeow.prism.Powers;

public enum InteractAggressivePowers {
    NETHERS_BLESSING {
        @Override
        public void execute(PlayerInteractEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You feel yourself getting a blessing from the nether", power.name);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_WITHER_SPAWN, 2, 1);
            p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 100, 2));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
            for (Player e : p.getWorld().getNearbyPlayers(p.getLocation(), 10)) {
                if (e == p) continue;
                Powers.sendHarmMessage(e, "You body gets overly hot", power.name);
                e.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                e.setFireTicks(e.getFireTicks() + 400);
                e.getWorld().playSound(e.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 2, 1);
                ParticleHelper.DrawLine(e.getLocation(), e.getLocation().add(0, 5, 0), Particle.LAVA, 1);
            }
        }
    },
    CURSE_OF_ZEUS {
        @Override
        public void execute(PlayerInteractEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "The gods fight by your side", power.name);
            p.getWorld().playSound(p.getLocation(), Sound.ITEM_TRIDENT_THUNDER, 2, 1);
            for (LivingEntity e : p.getWorld().getNearbyLivingEntities(p.getLocation(), 8)) {
                if (e == p || e instanceof ArmorStand) continue;
                if (e instanceof Player pe) {
                    Powers.sendHarmMessage(pe, "A bolt of lightning strikes you down, causing significant pain and disorientation", power.name);
                }
                e.getWorld().strikeLightningEffect(e.getLocation());
                e.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 0));
                e.addPotionEffect(new PotionEffect(PotionEffectType.NAUSEA, 100, 0));
                e.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 1));
                e.setNoDamageTicks(0);
                e.damage(15, p);
                e.setNoDamageTicks(0);
                new BukkitRunnable() {
                    int counter = 0;
                    @Override
                    public void run() {
                        if (counter >= 8) {
                            this.cancel();
                            return;
                        }
                        e.setNoDamageTicks(0);
                        e.damage(6, p);
                        e.setNoDamageTicks(0);
                        counter++;
                    }
                }.runTaskTimer(Prism.getPlugin(), 0L, 5L);
            }
        }
    },
    WIND_VEIL {
        @Override
        public void execute(PlayerInteractEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "The wind boost your strength and dexterity", power.name);
            p.addPotionEffect(new PotionEffect(PotionEffectType.HASTE, 200, 3));
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 0));
            CustomEffectHandler.AddCustomEffect(CustomEffectHandler.CustomEffects.CLOUDFOOTED, p, 300);
            new BukkitRunnable() {
                int counter = 0;
                @Override
                public void run() {
                    if (counter >= 20) {
                        this.cancel();
                        return;
                    }
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_BREEZE_HURT, 2, 1);
                    counter++;
                }
            }.runTaskTimer(Prism.getPlugin(), 0L, 1L);
        }
    }
    ;
    public abstract void execute(PlayerInteractEvent event, AggressivePowers power);
}
