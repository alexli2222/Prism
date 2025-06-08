package org.kittycatmeow.prism;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kittycatmeow.prism.cmd.*;
import org.kittycatmeow.prism.custom_effects.*;
import org.kittycatmeow.prism.events.*;
import org.kittycatmeow.prism.events.power.*;

public final class Prism extends JavaPlugin {

    private static Prism instance;
    private static DataHandler dataHandler;
    private static FirstJoinDataHandler firstJoinDataHandler;
    private static PrismItemLibrary itemLibrary;

    public static String VERSION = "1.2.0";

    @Override
    public void onEnable() {
        instance = this;
        try {
            dataHandler = new DataHandler();
            firstJoinDataHandler = new FirstJoinDataHandler();
        } catch (Exception e) {
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
        itemLibrary = new PrismItemLibrary();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        dataHandler.save();
        firstJoinDataHandler.save();
    }

    public static Prism getPlugin() {
        return instance;
    }

    public static DataHandler getDataHandler() {
        return dataHandler;
    }

    public static FirstJoinDataHandler getFirstJoinDataHandler() {
        return firstJoinDataHandler;
    }

    public static PrismItemLibrary getItemLibrary() {
        return itemLibrary;
    }

    private void registerListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new LoginEvent(), getPlugin());
        manager.registerEvents(new DeathEvent(), getPlugin());
        manager.registerEvents(new DropEvent(), getPlugin());
        manager.registerEvents(new InventoryEvent(), getPlugin());
        manager.registerEvents(new RerollEvent(), getPlugin());
        manager.registerEvents(new CraftEvent(), getPlugin());
        manager.registerEvents(new InteractEvent(), getPlugin());
        registerAbilities();
        registerCustomEffects();
    }

    private void registerAbilities() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new IcePower(), getPlugin());
        manager.registerEvents(new FirePower(), getPlugin());
        manager.registerEvents(new EarthPower(), getPlugin());
        manager.registerEvents(new WaterPower(), getPlugin());
        manager.registerEvents(new ElectricityPower(), getPlugin());
    }

    private void registerCustomEffects() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new Hardened(), getPlugin());
        manager.registerEvents(new Vulnerable(), getPlugin());
        manager.registerEvents(new StaticallyCharged(), getPlugin());
    }

    private void registerCommands() {
        getCommand("prism").setExecutor(new Main());
        getCommand("getrerollitem").setExecutor(new GetRerollItem());
        getCommand("givererollitem").setExecutor(new GiveRerollItem());
        getCommand("givererollall").setExecutor(new GiveRerollAll());
        getCommand("setpower").setExecutor(new SetPower());
        getCommand("reroll").setExecutor(new Reroll());
        getCommand("reloadpowers").setExecutor(new ReloadPowers());
        getCommand("clearcooldowns").setExecutor(new ClearCooldowns());
    }

    public static void sendPrefixedMessage(CommandSender sender, String message) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:dark_purple:light_purple>\uD83D\uDC8E Prism </gradient><gray>" + message
        ));
    }
}
