package org.kittycatmeow.chance.power;

import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.chance.perishable_data_storage.CooldownStorage;
import org.kittycatmeow.chance.ItemManip;
import org.kittycatmeow.chance.ChanceItemLibrary;

import java.util.UUID;

public class PassivePower {
    public static void ExecuteWithCooldown(PlayerToggleSneakEvent event, ChanceItemLibrary.Ids id, PassivePowers power) {
        if (event.isSneaking())
            if (ItemManip.getPower(event.getPlayer()) == id) {
                long cooldown = power.cooldown;
                UUID uuid = event.getPlayer().getUniqueId();
                long since = CooldownStorage.since(
                        CooldownStorage.Passive.Cooldowns.get(power).getOrDefault(uuid, 0L)
                );
                if (since >= cooldown) {
                    power.execute(event);
                    CooldownStorage.Passive.Cooldowns.get(power).put(uuid, System.currentTimeMillis());
                } else {
                    CooldownStorage.sendCooldownMessage(
                            event.getPlayer(),
                            power,
                            cooldown-since
                    );
                }
            }
    }
}
