package org.kittycatmeow.prism;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ItemManip {
    public static boolean isPower(@Nullable ItemStack i) {
        if (i == null) {
            return false;
        }
        ItemMeta meta = i.getItemMeta();
        if (meta == null) {
            return false;
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        for (PrismItemLibrary.Ids id : PrismItemLibrary.Ids.values()) {
            if (Objects.equals(container.get(new NamespacedKey("prism", "power"),
                    PersistentDataType.STRING), id.toString())
            ) {
                return true;
            }
        }
        return false;
    }
    public static PrismItemLibrary.Ids getPower(Player p) {
        for (ItemStack item : p.getInventory().getContents()) {
            if (isPower(item)) {
                PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
                String sid = container.get(new NamespacedKey("prism", "power"), PersistentDataType.STRING);
                return PrismItemLibrary.Ids.valueOf(sid);
            }
        }
        return null;
    }
    public static PrismItemLibrary.Ids getPower(ItemStack item) {
        if (isPower(item)) {
            PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            String sid = container.get(new NamespacedKey("prism", "power"), PersistentDataType.STRING);
            return PrismItemLibrary.Ids.valueOf(sid);
        }
        return null;
    }
    private static int removePower(Player p) {
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            if (isPower(p.getInventory().getItem(i))) {
                p.getInventory().remove(p.getInventory().getItem(i));
                return i;
            }
        }
        if (isPower(p.getInventory().getItemInOffHand())) {
            p.getInventory().setItemInOffHand(null);
            return -1;
        }
        return -2;
    }
    private static void addPower(Player p) {
        try {
            PrismItemLibrary.Ids id = PrismItemLibrary.Ids.valueOf(
                    Prism.getDataHandler().get(p.getUniqueId().toString())
            );
            p.getInventory().addItem(Prism.getItemLibrary().lib.get(id));
        } catch (Exception ignored) {
        }
    }
    public static void addPower(Player p, int slot) {
        try {
            PrismItemLibrary.Ids id = PrismItemLibrary.Ids.valueOf(
                    Prism.getDataHandler().get(p.getUniqueId().toString())
            );
            p.getInventory().setItem(slot, Prism.getItemLibrary().lib.get(id));
        } catch (Exception ignored) {
        }
    }
    public static void addPowerInOffhand(Player p) {
        try {
            PrismItemLibrary.Ids id = PrismItemLibrary.Ids.valueOf(
                    Prism.getDataHandler().get(p.getUniqueId().toString())
            );
            p.getInventory().setItemInOffHand(Prism.getItemLibrary().lib.get(id));
        } catch (Exception ignored) {
        }
    }
    public static void replacePower(Player p) {
        int prevSlot = removePower(p);
        if (prevSlot == -2) {
            addPower(p);
            return;
        }
        if (prevSlot == -1) {
            addPowerInOffhand(p);
            return;
        }
        addPower(p, prevSlot);
    }
}
