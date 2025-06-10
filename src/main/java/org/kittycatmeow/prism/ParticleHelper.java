package org.kittycatmeow.prism;

import com.destroystokyo.paper.ParticleBuilder;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;

public class ParticleHelper {
    public static class Dust {
        public static void DrawCircle(Location location, Color color, float size, double radius, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawCircle(location, builder, radius, false);
        }

        private static void DrawCircle(Location location, ParticleBuilder builder, double radius, boolean cull) {
            //five rings per block
            double total = radius * ((cull) ? 2 : 10);
            double increment = radius / total;
            //loops for each ring
            for (double r = 0; r < radius; r += increment) {
                //here r is the current radius that is being drawn
                DrawRing(location, builder, r, true);
            }
        }

        public static void DrawRing(Location location, Color color, float size, double radius, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawRing(location, builder, radius, false);
        }

        private static void DrawRing(Location location, ParticleBuilder builder, double radius, boolean cull) {
            //if r is 0 just draw a point
            if (radius == 0) {
                builder.location(location).spawn();
                return;
            }
            //circumference scale linearly with radius, for unit circle circumference is ~3 blocks, so using 20 particles per increase in radius
            double total = radius * ((cull) ? 5 : 20);
            double increment = (2 * Math.PI) / total;
            //loops until creates a full circle
            for (double angle = 0; angle < total; angle += increment) {
                //use trig to determine point
                Location newloc = location.clone().add(radius * Math.sin(angle), 0, radius * Math.cos(angle));
                //spawn particles
                builder.location(newloc).spawn();
            }
        }

        public static void DrawCylinder(Location location, Color color, float size, double radius, double height, int countPerDisplay, boolean fill) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawCylinder(location, builder, radius, height, fill);
        }

        private static void DrawCylinder(Location location, ParticleBuilder builder, double radius, double height, boolean fill) {
            //five circles/rings per block of height
            int total = (int) (height * 5);
            double increment = height / total;
            //start with 1 circle at bottom
            DrawCircle(location, builder, radius, false);
            location.add(0, increment, 0);
            //loops for each circle/ring, depending on fill, ignores first and last as they are always circle
            for (int i = 1; i < total - 1; i++) {
                if (fill) {
                    DrawCircle(location, builder, radius, false);
                } else {
                    DrawRing(location, builder, radius, false);
                }
                location.add(0, increment, 0);
            }
            DrawCircle(location, builder, radius, false);
        }

        public static void DrawCone(Location location, Color color, float size, double radius, double height, int countPerDisplay, boolean fill, boolean filltop, boolean flip) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawCone(location, builder, radius, height, fill, filltop, flip);
        }

        private static void DrawCone(Location location, ParticleBuilder builder, double radius, double height, boolean fill, boolean filltop, boolean flip) {
            //too weird wtf
            if (fill && !filltop) return;
            //five rings/circle per block, depending on fill
            double total = height * 10;
            double inch = height / total * (flip ? -1 : 1); //if flip, multiply height increment by negative 1 to make it go down instead of up
            double incr = radius / total;
            //starts at vertex, goes up
            for (double currentr = 0; currentr < height - incr /*saves last, could be circle if filltop*/; currentr += incr) {
                //draws circle or ring depending on fill
                if (fill) {
                    DrawCircle(location, builder, currentr, false);
                } else {
                    DrawRing(location, builder, currentr, false);
                }
                //moves up in terms of height
                location.add(0, inch, 0);
            }
            //draw last circle or ring
            if (filltop) {
                DrawCircle(location, builder, radius, false);
            } else {
                DrawRing(location, builder, radius, false);
            }
        }

        public static void DrawSphere(Location location, Color color, float size, double radius, int countPerDisplay, boolean fill) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            DrawSphere(location, builder, radius, fill);
        }

        private static void DrawSphere(Location location, ParticleBuilder builder, double radius, boolean fill) {
            //i dont want to explain this but basically turn the equation of a sphere into circle equation by fixing y (stacking circles on vertical axis)
            double total = radius * 5; // per hemisphere
            double increment = radius / total;
            //top hemisphere
            for (int i = 0; i < total; i++) {
                double dy = i * increment;
                double r = Math.sqrt(radius * radius - dy * dy);
                if (fill) {
                    DrawCircle(location, builder, r, false);
                } else {
                    DrawRing(location, builder, r, false);
                }
                location.add(0, increment, 0);
            }
            location.subtract(0, radius, 0);
            //bottom hemisphere
            for (int i = 0; i < total; i++) {
                double dy = i * increment;
                double r = Math.sqrt(radius * radius - dy * dy);
                if (fill) {
                    DrawCircle(location, builder, r, false);
                } else {
                    DrawRing(location, builder, r, false);
                }
                location.subtract(0, increment, 0);
            }
        }

        public static void DrawLine(Location start, Location end, Color color, float size, int countPerDisplay) {
            ParticleBuilder builder = new ParticleBuilder(Particle.DUST)
                    .color(color, size)
                    .count(countPerDisplay);
            double steps = start.distance(end) * 10;
            double deltaX = (end.getX() - start.getX()) / steps;
            double deltaY = (end.getY() - start.getY()) / steps;
            double deltaZ = (end.getZ() - start.getZ()) / steps;
            for (int i = 0; i <= steps; i++) {
                builder.location(start).spawn();
                start.add(deltaX, deltaY, deltaZ);
            }
        }
    }

    public static void DrawCircle(Location location, Particle particle, double radius, int countPerDisplay) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(countPerDisplay);
        Dust.DrawCircle(location, builder, radius, false);
    }

    public static void DrawCulledCircle(Location location, Particle particle, double radius) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(1);
        Dust.DrawCircle(location, builder, radius, true);
    }

    public static void DrawRing(Location location, Particle particle, double radius, int countPerDisplay) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(countPerDisplay);
        Dust.DrawRing(location, builder, radius, false);
    }

    public static void DrawLine(Location start, Location end, Particle particle, int countPerDisplay) {
        ParticleBuilder builder = new ParticleBuilder(particle)
                .count(countPerDisplay);
        double steps = start.distance(end);
        double deltaX = (end.getX() - start.getX()) / steps;
        double deltaY = (end.getY() - start.getY()) / steps;
        double deltaZ = (end.getZ() - start.getZ()) / steps;
        for (int i = 0; i <= steps; i++) {
            builder.location(start).spawn();
            start.add(deltaX, deltaY, deltaZ);
        }
    }
}