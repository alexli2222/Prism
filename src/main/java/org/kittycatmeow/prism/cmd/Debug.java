package org.kittycatmeow.prism.cmd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.kittycatmeow.prism.RayTraceHelper;

import java.util.ArrayList;
import java.util.List;

public class Debug implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        if (!(sender instanceof Player p))
            return true;
        if (args.length < 1)
            return true;
        String subc = args[0].toLowerCase();
        if (subc.equals("ray")) {
            if (args.length < 2)
                return true;
            double parsed;
            try {
                parsed = Double.parseDouble(args[1]);
            } catch (NumberFormatException e) {
                return true;
            }
            List<Entity> caught = RayTraceHelper.getAllEntitiesOnLine(
                    p.getWorld(),
                    p.getEyeLocation().toVector(),
                    p.getEyeLocation().toVector().add(p.getEyeLocation().getDirection().normalize().multiply(parsed))
            );
            for (Entity e : caught) {
                System.out.println(e.getName());
            }
        }
        return true;
    }
}
