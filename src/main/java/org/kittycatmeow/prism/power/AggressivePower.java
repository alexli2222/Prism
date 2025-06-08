package org.kittycatmeow.prism.power;

import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.kittycatmeow.prism.perishable_data_storage.CooldownStorage;
import org.kittycatmeow.prism.ItemManip;
import org.kittycatmeow.prism.PrismItemLibrary;

import java.util.UUID;

public class AggressivePower {
    public static void ExecuteWithCooldown(PlayerInteractEvent event, PrismItemLibrary.Ids id, AggressivePowers base, InteractAggressivePowers power) {
        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
        ItemStack item = event.getItem();
        if (!ItemManip.isPower(item)) return;
        if (ItemManip.getPower(item) == id) {
            long cooldown = base.cooldown;
            UUID uuid = event.getPlayer().getUniqueId();
            long since = CooldownStorage.since(
                    CooldownStorage.InteractAggressive.Cooldowns.get(base).getOrDefault(uuid, 0L)
            );
            if (since >= cooldown) {
                power.execute(event, base);
                CooldownStorage.InteractAggressive.Cooldowns.get(base).put(uuid, System.currentTimeMillis());
            } else {
                CooldownStorage.sendCooldownMessage(
                        event.getPlayer(),
                        base,
                        cooldown-since
                );
            }
        }
    }
    public static void ExecuteWithCooldown(PlayerInteractEntityEvent event, PrismItemLibrary.Ids id, AggressivePowers base, InteractEntityAggressivePowers power) {
        ItemStack item = event.getPlayer().getInventory().getItem(event.getHand());
        if (!ItemManip.isPower(item)) return;
        if (ItemManip.getPower(item) == id) {
            long cooldown = base.cooldown;
            if (base.type == AggressivePowers.AggressivePowerTypes.INTERACTPLAYER)
                if (!(event.getRightClicked() instanceof Player)) return;
            UUID uuid = event.getPlayer().getUniqueId();
            long since = CooldownStorage.since(
                    CooldownStorage.InteractEntityAggressive.Cooldowns.get(base).getOrDefault(uuid, 0L)
            );
            if (since >= cooldown) {
                power.execute(event, base);
                CooldownStorage.InteractEntityAggressive.Cooldowns.get(base).put(uuid, System.currentTimeMillis());
            } else {
                CooldownStorage.sendCooldownMessage(
                        event.getPlayer(),
                        base,
                        cooldown-since
                );
            }
        }
    }
}
