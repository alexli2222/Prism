package org.kittycatmeow.chance;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Powers {
    public static List<String> parseDescription(String description) {
        List<String> lines = new ArrayList<>();
        String[] words = description.split(" ");
        StringBuilder currentLine = new StringBuilder();
        int visibleLength = 0;
        for (String word : words) {
            int wordVisibleLength = getVisibleLength(word);
            if (visibleLength + wordVisibleLength + (visibleLength == 0 ? 0 : 1) <= 50) {
                if (!currentLine.isEmpty()) currentLine.append(" ");
                currentLine.append(word);
                visibleLength += (visibleLength == 0 ? 0 : 1) + wordVisibleLength;
            } else {
                lines.add(currentLine.toString());
                currentLine = new StringBuilder(word);
                visibleLength = wordVisibleLength;
            }
        }
        if (!currentLine.isEmpty()) {
            lines.add(currentLine.toString());
        }
        return lines;
    }
    private static int getVisibleLength(String word) {
        return word.replaceAll("<[^>]*>", "").length();
    }
    public static void sendBenefitMessage(Player p, String message, String abilityName) {
        Chance.sendPrefixedMessage(p, "<green>"+message+" ("+abilityName+").</green>");
    }
    public static void sendHarmMessage(Player p, String message, String abilityName) {
        Chance.sendPrefixedMessage(p, "<red>"+message+" ("+abilityName+").</red>");
    }
    public static void setPower(Player p, ChanceItemLibrary.Ids id) {
        Chance.getDataHandler().set(p.getUniqueId().toString(), id.toString());
        ItemManip.replacePower(p);
    }
    public static ChanceItemLibrary.Ids randomPower(Player p) {
        ChanceItemLibrary.Ids[] values = ChanceItemLibrary.Ids.values();
        ChanceItemLibrary.Ids randomId;
        do {
            randomId = values[new Random().nextInt(values.length)];
        } while (randomId == ItemManip.getPower(p));
        setPower(p, randomId);
        return randomId;
    }
    public static void giveRerollItem(Player p, int count) {
        for (int i = 0; i < count; i++) {
            p.getInventory().addItem(Chance.getItemLibrary().REROLL);
        }
        Chance.sendPrefixedMessage(p, "You have been given " + count + " reroll item"+(count == 1 ? "" : "s"));
    }
}
