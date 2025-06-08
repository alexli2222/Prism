package org.kittycatmeow.chance.power;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ParticleHelper;
import org.kittycatmeow.chance.Powers;

public enum InteractAggressivePowers {
    NETHERS_BLESSING {
        @Override
        public void execute(PlayerInteractEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You feel yourself getting a blessing from the nether", power.name);
            p.addPotionEffect(new PotionEffect(PotionEffectType.STRENGTH, 160, 2));
            for (Player e : p.getWorld().getNearbyPlayers(p.getLocation(), 10)) {
                if (e == p) continue;
                Powers.sendHarmMessage(e, "You body gets overly hot", power.name);
                e.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
                e.setFireTicks(e.getFireTicks() + 600);
                e.getWorld().playSound(e.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 2, 1);
                ParticleHelper.DrawLine(e.getLocation(), e.getLocation().add(0, 5, 0), Particle.LAVA, 50, 1);
            }
        }
    },
    CURSE_OF_ZEUS {
        @Override
        public void execute(PlayerInteractEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "The gods fight by your side", power.name);
            for (LivingEntity e : p.getWorld().getNearbyLivingEntities(p.getLocation(), 8)) {
                if (e == p || e instanceof ArmorStand) continue;
                if (e instanceof Player pe) {
                    Powers.sendHarmMessage(pe, "A bolt of lightning strikes you down, causing significant pain and disorientation", power.name);
                }
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
                        e.damage(5, p);
                        e.setNoDamageTicks(0);
                        counter++;
                    }
                }.runTaskTimer(Chance.getPlugin(), 0L, 5L);
            }
        }
    }
    ;
    public abstract void execute(PlayerInteractEvent event, AggressivePowers power);
}
