package org.kittycatmeow.prism;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class RayTraceHelper {
    private static final double ACCEPT = 0.25;
    public static List<Entity> getAllEntitiesOnLine(World world, Vector start, Vector end) {
        HashSet<Entity> found = new HashSet<>();

        double dist = start.distance(end);
        int steps = (int) Math.ceil(dist / 0.5);
        Vector step = end.clone().subtract(start).multiply(1.0 / steps);
        Vector temp = start.clone();

        for (int i = 0; i < steps; i++) {
            temp.add(step);
            Location location = temp.toLocation(world);

            // Check for entities at this point
            Collection<Entity> entities = world.getNearbyEntities(location, ACCEPT, ACCEPT, ACCEPT);

            found.addAll(entities);
        }
        return found.stream().toList();
    }
    public static List<LivingEntity> getAllLivingEntitiesOnLine(World world, Vector start, Vector end) {
        HashSet<LivingEntity> found = new HashSet<>();

        double dist = start.distance(end);
        int steps = (int) Math.ceil(dist / 0.5);
        Vector step = end.clone().subtract(start).multiply(1.0 / steps);
        Vector temp = start.clone();

        for (int i = 0; i < steps; i++) {
            temp.add(step);
            Location location = temp.toLocation(world);
            Collection<LivingEntity> entities = world.getNearbyLivingEntities(location, ACCEPT, ACCEPT, ACCEPT);

            found.addAll(entities);
        }
        return found.stream().toList();
    }
    public static List<Player> getAllPlayersOnLine(World world, Vector start, Vector end) {
        HashSet<Player> found = new HashSet<>();

        double dist = start.distance(end);
        int steps = (int) Math.ceil(dist / 0.5);
        Vector step = end.clone().subtract(start).multiply(1.0 / steps);
        Vector temp = start.clone();

        for (int i = 0; i < steps; i++) {
            temp.add(step);
            Location location = temp.toLocation(world);
            Collection<Player> players = world.getNearbyPlayers(location, ACCEPT, ACCEPT, ACCEPT);

            found.addAll(players);
        }
        return found.stream().toList();
    }
}
