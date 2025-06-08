package org.kittycatmeow.chance.perishable_data_storage;

import java.util.UUID;

public abstract class CustomEffectStorage {
    public abstract boolean hasTarget(UUID target);
    public abstract void addTarget(UUID target);
    public abstract void removeTarget(UUID target);
}
