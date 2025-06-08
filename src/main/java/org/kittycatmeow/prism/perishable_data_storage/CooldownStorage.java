package org.kittycatmeow.chance.perishable_data_storage;

import org.bukkit.entity.Player;
import org.kittycatmeow.chance.Chance;
import org.kittycatmeow.chance.ParticleHelper;
import org.kittycatmeow.chance.power.AggressivePowers;
import org.kittycatmeow.chance.power.PassivePowers;

import java.util.HashMap;
import java.util.UUID;

public class CooldownStorage {
    public static class Passive {
        public static HashMap<PassivePowers, HashMap<UUID, Long>> Cooldowns = new HashMap<>();
        static {
            Cooldowns.put(PassivePowers.GLACIAL_HEALING, new HashMap<>());
            Cooldowns.put(PassivePowers.SCORCHED_SKIN, new HashMap<>());
            Cooldowns.put(PassivePowers.TERRASHIELD, new HashMap<>());
            Cooldowns.put(PassivePowers.REFRESH, new HashMap<>());
            Cooldowns.put(PassivePowers.STATIC_SHIELD, new HashMap<>());
        }
    }
    public static class InteractAggressive {
        public static HashMap<AggressivePowers, HashMap<UUID, Long>> Cooldowns = new HashMap<>();
        static {
            Cooldowns.put(AggressivePowers.NETHERS_BLESSING, new HashMap<>());
            Cooldowns.put(AggressivePowers.CURSE_OF_ZEUS, new HashMap<>());
        }
    }
    public static class InteractEntityAggressive {
        public static HashMap<AggressivePowers, HashMap<UUID, Long>> Cooldowns = new HashMap<>();
        static {
            Cooldowns.put(AggressivePowers.PERMAFROST, new HashMap<>());
            Cooldowns.put(AggressivePowers.MOUNTAINS_WEIGHT, new HashMap<>());
            Cooldowns.put(AggressivePowers.WHIRLPOOL, new HashMap<>());
        }
    }
    public static long since(long last) {
        return System.currentTimeMillis() - last;
    }
    public static void sendCooldownMessage(Player p, PassivePowers passive, long left) {
        Chance.sendPrefixedMessage(p, passive.name + " is on cooldown for " + left / 1000 + " seconds.");
    }
    public static void sendCooldownMessage(Player p, AggressivePowers aggressive, long left) {
        Chance.sendPrefixedMessage(p, aggressive.name + " is on cooldown for " + left / 1000 + " seconds.");
    }
}
