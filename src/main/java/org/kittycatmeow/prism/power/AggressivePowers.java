package org.kittycatmeow.chance.power;

import org.kittycatmeow.chance.CustomEffectHandler;
import org.kittycatmeow.chance.Powers;
import org.kittycatmeow.chance.ChanceItemLibrary;

import java.util.List;

public enum AggressivePowers {
    PERMAFROST (
            ChanceItemLibrary.Ids.ICE,
            AggressivePowerTypes.INTERACTPLAYER,
            "<aqua>Permafrost</aqua>",
            "Damages the clicked player by 20, then apply to them slowness 5 for 5 seconds, hunger 2 and weakness 1 for 20 seconds, and wither 10 and blindness for 3 seconds.",
            45000L
    ),
    NETHERS_BLESSING (
            ChanceItemLibrary.Ids.FIRE,
            AggressivePowerTypes.INTERACT,
            "<red>Nether's Blessing</red>",
            "Gain strength 3 for 8 seconds. Remove fire resistance from all players in radius 10, then set them on fire for 30 seconds.",
            60000L
    ),
    MOUNTAINS_WEIGHT (
            ChanceItemLibrary.Ids.EARTH,
            AggressivePowerTypes.INTERACTPLAYER,
            "<dark_gray>Mountains' Weight</dark_gray>",
            "Applies slowness 3 and weakness 2 to the clicked player for 5 seconds, then apply the "+CustomEffectHandler.CustomEffects.VULNERABLE.name+" effect to them for 15 seconds. "+CustomEffectHandler.CustomEffects.VULNERABLE.getDescription(),
            45000L
    ),
    WHIRLPOOL (
            ChanceItemLibrary.Ids.WATER,
            AggressivePowerTypes.INTERACTPLAYER,
            "<blue>Whirlpool</blue>",
            "Traps the clicked player in a whirlpool for 3 seconds, then damage them for 15 + their current health",
            45000L
    ),
    CURSE_OF_ZEUS (
            ChanceItemLibrary.Ids.ELECTRICITY,
            AggressivePowerTypes.INTERACT,
            "<dark_purple>Curse of Zeus</dark_purple>",
            "Strikes lightning, dealing 15 immediate damage to any living entity within 8 blocks while applying blindness, nausea, and weakness 2 to them for 5 seconds. Then, damage the living entities by 5 every 0.25 seconds for 2 seconds",
            40000L
    )
    ;
    public enum AggressivePowerTypes {
        INTERACT (
                "<yellow><bold>RIGHT CLICK</bold></yellow>"
        ),
        INTERACTPLAYER (
                "<yellow><bold>RIGHT CLICK ON PLAYER</bold></yellow>"
        )
        ;
        public final String actionName;
        AggressivePowerTypes(String actionName) {
            this.actionName = actionName;
        }
    }
    public final ChanceItemLibrary.Ids id;
    public final AggressivePowerTypes type;
    public final String name;
    public final String description;
    public final long cooldown;
    AggressivePowers(ChanceItemLibrary.Ids id, AggressivePowerTypes type, String name, String description, long cooldown) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }
    public List<String> descriptionParsed() {
        return Powers.parseDescription(description);
    }
}
