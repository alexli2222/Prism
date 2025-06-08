package org.kittycatmeow.chance;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.kittycatmeow.chance.cmd.*;
import org.kittycatmeow.chance.custom_effects.*;
import org.kittycatmeow.chance.events.*;
import org.kittycatmeow.chance.events.power.*;

import java.io.IOException;

public final class Chance extends JavaPlugin {

    private static Chance instance;
    private static DataHandler dataHandler;
    private static ChanceItemLibrary itemLibrary;

    public static final String VERSION = "1.0.0";

    @Override
    public void onEnable() {
        instance = this;
        try {
            dataHandler = new DataHandler();
        } catch (IOException e) {
            getServer().getPluginManager().disablePlugin(this);
            throw new RuntimeException(e);
        }
        itemLibrary = new ChanceItemLibrary();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        dataHandler.save();
    }

    public static Chance getPlugin() {
        return instance;
    }

    public static DataHandler getDataHandler() {
        return dataHandler;
    }

    public static ChanceItemLibrary getItemLibrary() {
        return itemLibrary;
    }

    private void registerListeners() {
        PluginManager manager = getServer().getPluginManager();
        manager.registerEvents(new LoginEvent(), getPlugin());
        manager.registerEvents(new DeathEvent(), getPlugin());
        manager.registerEvents(new DropEvent(), getPlugin());
        manager.registerEvents(new InventoryEvent(), getPlugin());
        manager.registerEvents(new RerollEvent(), getPlugin());
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
        getCommand("chance").setExecutor(new Main());
        getCommand("getrerollitem").setExecutor(new GetRerollItem());
        getCommand("setpower").setExecutor(new SetPower());
        getCommand("reroll").setExecutor(new Reroll());
        getCommand("reloadpowers").setExecutor(new ReloadPowers());
        getCommand("clearcooldowns").setExecutor(new ClearCooldowns());
    }

    public static void sendPrefixedMessage(CommandSender sender, String message) {
        sender.sendMessage(MiniMessage.miniMessage().deserialize(
                "<gradient:green:dark_green>\uD83C\uDFB2 Chance </gradient><gray>" + message
        ));
    }
}
