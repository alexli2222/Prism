package org.kittycatmeow.prism.power;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.Color;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.CustomEffectHandler;
import org.kittycatmeow.prism.ParticleHelper;
import org.kittycatmeow.prism.Powers;

public enum InteractEntityAggressivePowers {
    PERMAFROST {
        @Override
        public void execute(PlayerInteractEntityEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Player e = (Player) event.getRightClicked();
            Powers.sendBenefitMessage(p, "A chill runs through your veins and into your hand", power.name);
            Powers.sendHarmMessage(e, "A frigid sensation enters your body, causing <dark_red>excruciating</dark_red> pain", power.name);
            e.setNoDamageTicks(0);
            double a = (Math.sqrt(e.getHealth()+e.getFoodLevel()) * e.getAttribute(Attribute.ARMOR).getValue())/100.0;
            e.damage(a, p);
            e.setNoDamageTicks(0);
            e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 4));
            e.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 1));
            e.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 0));
            e.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60, 9));
            e.getWorld().playSound(e.getLocation(), Sound.BLOCK_GLASS_BREAK, 2, 1);
            ParticleHelper.Dust.DrawCylinder(e.getLocation().add(0, -1, 0), Color.AQUA, 0.5f, 1.5, 4, 1, false);
        }
    },
    MOUNTAINS_WEIGHT {
        @Override
        public void execute(PlayerInteractEntityEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Player e = (Player) event.getRightClicked();
            Powers.sendBenefitMessage(p, "The Earth responds to your prayers", power.name);
            Powers.sendHarmMessage(e, "A heavy weight crushes you from above", power.name);
            e.getWorld().playSound(e.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 2, 0.5f);
            e.getWorld().playSound(e.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 2, 0.5f);
            e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 100, 2));
            e.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 2));
            CustomEffectHandler.AddCustomEffect(CustomEffectHandler.CustomEffects.VULNERABLE, e, 300);
            ParticleHelper.DrawRing(e.getLocation().add(0, 1, 0), Particle.EXPLOSION, 2, 1);
            ParticleHelper.Dust.DrawLine(e.getLocation(), e.getLocation().add(0, 10, 0), Color.GRAY, 2, 2);
        }
    },
    WHIRLPOOL {
        @Override
        public void execute(PlayerInteractEntityEvent event, AggressivePowers power) {
            Player p = event.getPlayer();
            Player e = (Player) event.getRightClicked();
            Powers.sendBenefitMessage(p, "Nearby water bends to your wishes", power.name);
            Powers.sendHarmMessage(e, "Your movement ceases as you become trapped in a whirlpool", power.name);
            e.getWorld().playSound(e.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, 2, 1);
            e.addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 60, 2));
            e.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 60, 2));
            new BukkitRunnable() {
                int counter = 0;
                @Override
                public void run() {
                    if (counter >= 80) {
                        this.cancel();
                        e.setNoDamageTicks(0);
                        e.damage(2* e.getHealth() / 3 + 10, p);
                        e.setNoDamageTicks(0);
                        return;
                    }
                    e.setVelocity(new Vector(0, 0, 0));
                    if (counter%8 == 0) {
                        e.getWorld().playSound(e.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_HURT, 2, 0.5f);
                        e.getWorld().playSound(e.getLocation(), Sound.ENTITY_PLAYER_SPLASH_HIGH_SPEED, 2, 0.5f);
                        ParticleHelper.Dust.DrawCone(e.getLocation(), Color.BLUE, 0.5f, 3, 3, 1, false, false, false);
                    }
                    counter++;
                }
            }.runTaskTimer(Prism.getPlugin(), 0L, 1L);
        }
    }
    ;
    public abstract void execute(PlayerInteractEntityEvent event, AggressivePowers power);
}
