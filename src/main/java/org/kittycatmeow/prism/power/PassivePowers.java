package org.kittycatmeow.prism.power;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.kittycatmeow.prism.CustomEffectHandler;
import org.kittycatmeow.prism.ParticleHelper;
import org.kittycatmeow.prism.Powers;
import org.kittycatmeow.prism.PrismItemLibrary;

import java.util.List;

public enum PassivePowers {
    GLACIAL_HEALING(
            PrismItemLibrary.Ids.ICE,
            "<aqua>Glacial Healing</aqua>",
            "Heals you for 25% of your max health, then gain regeneration 3 for 5 seconds. Also applies the "+CustomEffectHandler.CustomEffects.HARDENED.name+" effect for 10 seconds. "+CustomEffectHandler.CustomEffects.HARDENED.getDescription(),
            20000L
    ) {
        @Override
        public void execute(PlayerToggleSneakEvent event) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You feel empowered as your pains go away", name);
            p.heal(p.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.25);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 2));
            CustomEffectHandler.AddCustomEffect(CustomEffectHandler.CustomEffects.HARDENED, p, 200);
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, 2, 1);
        }
    },
    SCORCHED_SKIN(
            PrismItemLibrary.Ids.FIRE,
            "<gold>Scorched Skin</gold>",
            "Heals you for 20% of your max health if you are on fire, then gain fire resistance for 30 seconds",
            30000L
    ) {
        @Override
        public void execute(PlayerToggleSneakEvent event) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You feel your body getting stronger and hotter", name);
            if (p.getFireTicks() > 0) {
                p.heal(p.getAttribute(Attribute.MAX_HEALTH).getBaseValue() * 0.20);
            }
            p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 600, 9));
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE, 1, 0.5f);
        }
    },
    TERRASHIELD (
            PrismItemLibrary.Ids.EARTH,
            "<dark_green>Terrashield</dark_green>",
            "Obtain resistance 3 for 10 seconds.",
            40000L
    ) {
        @Override
        public void execute(PlayerToggleSneakEvent event) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You no longer seem to feel pain when being attacked", name);
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 200, 2));
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_PLACE, 2, 0.5f);
            Location location = p.getLocation().add(0, 0.5, 0);
            for (int i = 0; i < 11; i++) {
                ParticleHelper.Dust.DrawRing(location, Color.fromRGB(55, 75, 60), 0.5f, 2, 50, 1);
                location.add(0, 0.1, 0);
            }
        }
    },
    REFRESH (
            PrismItemLibrary.Ids.WATER,
            "<green>Refresh</green>",
                    "Obtain regeneration 2 and health boost 2 for 15 seconds.",
                    30000L
    ) {
        @Override
        public void execute(PlayerToggleSneakEvent event) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "You feel refreshed", name);
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 300, 1));
            p.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, 300, 1));
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 2, 1);
        }
    },
    STATIC_SHIELD (
            PrismItemLibrary.Ids.ELECTRICITY,
            "<yellow>Static Shield</yellow>",
            "Obtain resistance 1 for 10 seconds, and the "+ CustomEffectHandler.CustomEffects.STATICALLY_CHARGED.name+" effect for 15 seconds. "+CustomEffectHandler.CustomEffects.STATICALLY_CHARGED.getDescription(),
            35000L
    ) {
        @Override
        public void execute(PlayerToggleSneakEvent event) {
            Player p = event.getPlayer();
            Powers.sendBenefitMessage(p, "The power of electricity runs through you", name);
            p.addPotionEffect(new PotionEffect(PotionEffectType.RESISTANCE, 300, 0));
            CustomEffectHandler.AddCustomEffect(CustomEffectHandler.CustomEffects.STATICALLY_CHARGED, p, 200);
            ParticleHelper.Dust.DrawSphere(p.getLocation(), Color.YELLOW, 0.5f, 3, 100, 100, 50, 1, false);
            p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CONVERTED, 2, 0.5f);
        }
    },
    ;
    public final PrismItemLibrary.Ids id;
    public final String name;
    public final String description;
    public final long cooldown;
    PassivePowers(PrismItemLibrary.Ids id, String name, String description, long cooldown) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }
    public abstract void execute(PlayerToggleSneakEvent event);
    public List<String> descriptionParsed() {
        return Powers.parseDescription(description);
    }
}
