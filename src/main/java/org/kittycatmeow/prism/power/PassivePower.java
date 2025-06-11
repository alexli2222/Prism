package org.kittycatmeow.prism.power;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.kittycatmeow.prism.Prism;
import org.kittycatmeow.prism.perishable_data_storage.CooldownStorage;
import org.kittycatmeow.prism.ItemManip;
import org.kittycatmeow.prism.PrismItemLibrary;

import java.util.UUID;

public class PassivePower {
    public static void ExecuteWithCooldown(PlayerToggleSneakEvent event, PrismItemLibrary.Ids id, PassivePowers power) {
        if (event.isSneaking()) {
            Player p = event.getPlayer();
            UUID uuid = p.getUniqueId();
            if (Prism.getInHandSneakingDataHandler().get(uuid.toString()) &&
                            !ItemManip.isPower(p.getInventory().getItemInMainHand()) &&
                            !ItemManip.isPower(p.getInventory().getItemInOffHand()))
                return;
            if (ItemManip.getPower(p) == id) {
                long cooldown = power.cooldown;
                long since = CooldownStorage.since(
                        CooldownStorage.Passive.Cooldowns.get(power).getOrDefault(uuid, 0L)
                );
                if (since >= cooldown) {
                    power.execute(event);
                    CooldownStorage.Passive.Cooldowns.get(power).put(uuid, System.currentTimeMillis());
                } else {
                    CooldownStorage.sendCooldownMessage(
                            p,
                            power,
                            cooldown - since
                    );
                }
            }
        }
    }
}
