package org.kittycatmeow.chance;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleHelper {
    public static class Dust {
        public static void DrawCircle(Location location, Color color, float size, double radius, int stepsPerRing, int stepsPerCircle, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);

            for (int ring = 0; ring < stepsPerCircle; ring++) {
                double currentRadius = radius * ((double) ring / stepsPerCircle);
                int adjustedSteps = (int) (stepsPerRing * (1 + ring * 0.5));
                DrawRing(location, builder, currentRadius, adjustedSteps);
            }
        }

        public static void DrawRing(Location location, Color color, float size, double radius, int stepsPerRing, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawRing(location, builder, radius, stepsPerRing);
        }

        private static void DrawRing(Location location, ParticleBuilder builder, double radius, int stepsPerRing) {
            double angleIncrement = 2 * Math.PI / stepsPerRing;
            for (int step = 0; step < stepsPerRing; step++) {
                double angle = step * angleIncrement;
                builder.location(location.clone().add(
                        radius * Math.cos(angle),
                        0,
                        radius * Math.sin(angle)
                )).spawn();
            }
        }

        public static void DrawCylinder(Location location, Color color, float size, double radius, double height, int stepsPerRing, int stepsPerCircle, int stepsPerCylinder, int countPerDisplay, boolean fill) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);

            DrawCircle(location, color, size, radius, stepsPerRing, stepsPerCircle, countPerDisplay);

            double ringSpacing = height / (stepsPerCylinder - 1);
            Location layerLoc = location.clone();

            for (int i = 1; i < stepsPerCylinder - 1; i++) {
                layerLoc.add(0, ringSpacing, 0);
                if (!fill) {
                    DrawRing(layerLoc, builder, radius, (int) (stepsPerRing*radius*2));
                } else {
                    DrawCircle(layerLoc, color, size, radius, stepsPerRing, stepsPerCircle, countPerDisplay);
                }
            }

            DrawCircle(location.clone().add(0, height, 0), color, size, radius, stepsPerRing, stepsPerCircle, countPerDisplay);
        }
        public static void DrawLine(Location start, Location end, Color color, float size, int steps, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            double deltaX = (end.getX() - start.getX()) / steps;
            double deltaY = (end.getY() - start.getY()) / steps;
            double deltaZ = (end.getZ() - start.getZ()) / steps;
            Location current = start.clone();
            for (int i = 0; i <= steps; i++) {
                builder.location(current).spawn();
                current.add(deltaX, deltaY, deltaZ);
            }
        }

        public static void DrawCone(Location location, Color color, float size, double radius, double height, int stepsPerRing, int stepsPerCircle, int stepsPerCone, int countPerDisplay, boolean fill, boolean flip) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);

            double heightSpacing = height / stepsPerCone;
            double radiusDecrement = radius / stepsPerCone;
            Location layerLoc = location.clone();

            for (int i = 0; i < stepsPerCone; i++) {
                double currentRadius = flip ?
                        radiusDecrement * i :
                        radius - (radiusDecrement * i);
                int adjustedStepsPerRing = (int) (stepsPerRing * (currentRadius / radius));
                int adjustedStepsPerCircle = (int) (stepsPerCircle * (currentRadius / radius));
                if (!fill) {
                    DrawRing(layerLoc, builder, currentRadius, adjustedStepsPerRing);
                } else {
                    DrawCircle(layerLoc, color, size, currentRadius, adjustedStepsPerRing, adjustedStepsPerCircle, countPerDisplay);
                }
                layerLoc.add(0, heightSpacing, 0);
            }

            builder.location(layerLoc).spawn();
        }

        public static void DrawSphere(Location location, Color color, float size, double radius, int stepsPerRing, int stepsPerCircle, int stepsPerSphere, int countPerDisplay, boolean fill) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);

            double angleIncrement = Math.PI / stepsPerSphere;
            for (int i = 0; i <= stepsPerSphere; i++) {
                double phi = i * angleIncrement;
                double currentRadius = radius * Math.sin(phi);
                double y = radius * Math.cos(phi);
                Location layerLoc = location.clone().add(0, y, 0);

                if (!fill) {
                    DrawRing(layerLoc, builder, currentRadius, (int) (stepsPerRing * currentRadius * 2));
                } else {
                    int adjustedStepsPerRing = (int) (stepsPerRing * (currentRadius / radius));
                    int adjustedStepsPerCircle = (int) (stepsPerCircle * (currentRadius / radius));
                    DrawCircle(layerLoc, color, size, currentRadius, adjustedStepsPerRing, adjustedStepsPerCircle, countPerDisplay);
                }
            }
        }

    }

    public static void DrawRing(Location location, Particle particle, double radius, int stepsPerRing, int countPerDisplay) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(countPerDisplay);
        DrawRing(location, builder, radius, stepsPerRing);
    }

    private static void DrawRing(Location location, ParticleBuilder builder, double radius, int stepsPerRing) {
        double angleIncrement = 2 * Math.PI / stepsPerRing;
        for (int step = 0; step < stepsPerRing; step++) {
            double angle = step * angleIncrement;
            builder.location(location.clone().add(
                    radius * Math.cos(angle),
                    0,
                    radius * Math.sin(angle)
            )).spawn();
        }
    }

    public static void DrawLine(Location start, Location end, Particle particle, int steps, int countPerDisplay) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(countPerDisplay);
        double deltaX = (end.getX() - start.getX()) / steps;
        double deltaY = (end.getY() - start.getY()) / steps;
        double deltaZ = (end.getZ() - start.getZ()) / steps;
        Location current = start.clone();
        for (int i = 0; i <= steps; i++) {
            builder.location(current).spawn();
            current.add(deltaX, deltaY, deltaZ);
        }
    }
}