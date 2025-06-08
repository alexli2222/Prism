package org.kittycatmeow.prism;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.kittycatmeow.prism.power.AggressivePowers;
import org.kittycatmeow.prism.power.PassivePowers;

import java.util.HashMap;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class ChanceItemLibrary {
    public ItemStack REROLL;
    public HashMap<Ids, ItemStack> lib = new HashMap<>();

    public ChanceItemLibrary() {
        registerRerollItem();
        registerChanceItem(Material.LIGHT_BLUE_DYE, "<!italic><gradient:aqua:blue>Ice Power", Ids.ICE);
        registerChanceItem(Material.ORANGE_DYE, "<!italic><gradient:gold:red>Fire Power", Ids.FIRE);
        registerChanceItem(Material.GREEN_DYE, "<!italic><gradient:dark_green:dark_gray:dark_green>Earth Power", Ids.EARTH);
        registerChanceItem(Material.BLUE_DYE, "<!italic><gradient:blue:dark_blue>Water Power", Ids.WATER);
        registerChanceItem(Material.YELLOW_DYE, "<!italic><gradient:white:yellow>Electricity Power", Ids.ELECTRICITY);
    }

    public enum Ids {
        ICE,
        FIRE,
        EARTH,
        WATER,
        ELECTRICITY
    }

    private void registerRerollItem() {
        ItemStack item = new ItemStack(Material.COMMAND_BLOCK);
        ItemMeta meta = item.getItemMeta();
        meta.customName(MiniMessage.miniMessage().deserialize("<!italic><gold>Reroll Item</gold>"));
        List<Component> lore = newArrayList();
        lore.add(Component.empty());
        lore.add(MiniMessage.miniMessage().deserialize("<!italic><rainbow>Special Ability: Reroll</rainbow> <yellow><bold>RIGHT CLICK</bold></yellow>"));
        lore.add(MiniMessage.miniMessage().deserialize("<!italic><gold>Reroll your power!</gold>"));
        lore.add(Component.empty());
        meta.lore(lore);
        meta.getPersistentDataContainer().set(
                new NamespacedKey("chance", "reroll"),
                PersistentDataType.BOOLEAN,
                true
        );
        item.setItemMeta(meta);
        REROLL = item;
    }

    private void registerChanceItem(Material base, String name, Ids id) {
        PassivePowers passive = null;
        AggressivePowers aggressive = null;
        for (PassivePowers p : PassivePowers.values())
            if (p.id == id) {
                passive = p;
                break;
            }
        if (passive == null)
            return;
        for (AggressivePowers p : AggressivePowers.values()) {
            if (p.id == id) {
                aggressive = p;
                break;
            }
        }
        if (aggressive == null)
            return;
        ItemStack item = new ItemStack(base);
        ItemMeta meta = item.getItemMeta();
        meta.customName(MiniMessage.miniMessage().deserialize(name));
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(
                new NamespacedKey("chance", "power"),
                org.bukkit.persistence.PersistentDataType.STRING,
                id.toString()
        );
        List<Component> lore = newArrayList();
        lore.add(Component.empty());
        lore.add(
                MiniMessage.miniMessage().deserialize("<!italic><gold>Passive Ability: </gold>"+passive.name+" <yellow><bold>SNEAK</bold></yellow>")
        );
        List<String> passiveDescription = passive.descriptionParsed();
        for (String s : passiveDescription)
            lore.add(MiniMessage.miniMessage().deserialize("<!italic><gray>"+s+"</gray>"));
        lore.add(MiniMessage.miniMessage().deserialize("<!italic><dark_gray>Cooldown: "+passive.cooldown/1000+"s</dark_gray>"));
        lore.add(Component.empty());
        lore.add(
                MiniMessage.miniMessage().deserialize("<!italic><red>Aggressive Ability: </red>"+aggressive.name+" "+aggressive.type.actionName)
        );
        List<String> aggressiveDescription = aggressive.descriptionParsed();
        for (String s : aggressiveDescription)
            lore.add(MiniMessage.miniMessage().deserialize("<!italic><gray>"+s+"</gray>"));
        lore.add(MiniMessage.miniMessage().deserialize("<!italic><dark_gray>Cooldown: "+aggressive.cooldown/1000+"s</dark_gray>"));
        lore.add(Component.empty());
        meta.lore(lore);
        item.setItemMeta(meta);
        lib.put(id, item);
    }
}
