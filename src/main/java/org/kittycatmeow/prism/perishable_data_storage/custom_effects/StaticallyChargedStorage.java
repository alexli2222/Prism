package org.kittycatmeow.chance.perishable_data_storage.custom_effects;

import org.kittycatmeow.chance.perishable_data_storage.CustomEffectStorage;

import java.util.HashSet;
import java.util.UUID;

public class StaticallyChargedStorage extends CustomEffectStorage {
    private final HashSet<UUID> targets = new HashSet<>();
    @Override
    public boolean hasTarget(UUID target) {
        return targets.contains(target);
    }
    @Override
    public void addTarget(UUID target) {
        targets.add(target);
    }
    @Override
    public void removeTarget(UUID target) {
        targets.remove(target);
    }
}
