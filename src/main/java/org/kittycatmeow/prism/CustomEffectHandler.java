package org.kittycatmeow.prism;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.kittycatmeow.prism.perishable_data_storage.CustomEffectStorage;
import org.kittycatmeow.prism.perishable_data_storage.custom_effects.HardenedStorage;
import org.kittycatmeow.prism.perishable_data_storage.custom_effects.StaticallyChargedStorage;
import org.kittycatmeow.prism.perishable_data_storage.custom_effects.VulnerableStorage;

import java.util.UUID;

public class CustomEffectHandler {
    public static void AddCustomEffect(CustomEffects effect, Player target, int ticks) {
        CustomEffectStorage storage = effect.storage;
        SendApplyEffectMessage(target, effect, ticks);
        UUID uuid = target.getUniqueId();
        storage.addTarget(uuid);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!storage.hasTarget(uuid)) return;
                storage.removeTarget(uuid);
                SendExpiredEffectMessage(target, effect);
            }
        }.runTaskLater(Prism.getPlugin(), ticks);
    }
    public static boolean HasCustomEffect(CustomEffects effect, Player target) {
        CustomEffectStorage storage = effect.storage;
        return (storage.hasTarget(target.getUniqueId()));
    }
    public static void SendApplyEffectMessage(Player target, CustomEffects effect, int ticks) {
        Prism.sendPrefixedMessage(
                target,
                "The "+effect.name+" effect has been applied to you for "+ticks/20+" seconds. "+effect.getDescription()
        );
    }
    public static void SendExpiredEffectMessage(Player target, CustomEffects effect) {
        Prism.sendPrefixedMessage(
                target,
                "The "+effect.name+" effect has expired."
        );
    }
    public enum CustomEffects {
        HARDENED(
                new HardenedStorage(),
                "<blue>Hardened</blue>",
                "one takes 15% less damage from all sources."
        ),
        VULNERABLE(
                new VulnerableStorage(),
                "<red>Vulnerable</red>",
                "one takes 20% more damage from all sources."
        ),
        STATICALLY_CHARGED(
                new StaticallyChargedStorage(),
                "<yellow>Statically Charged</yellow>",
                "any attacking entity will take 25% of the hit's damage, while gaining slowness 2 for 2 seconds."
        )
        ;
        private final CustomEffectStorage storage;
        private final String description;
        public final String name;
        CustomEffects(CustomEffectStorage storage, String name, String description) {
            this.storage = storage;
            this.name = name;
            this.description = description;
        }
        public String getDescription() {
            return "While "+name+", "+description;
        }
    }
}
