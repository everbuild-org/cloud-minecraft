package org.incendo.cloud.minestom;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.Command;
import org.incendo.cloud.internal.CommandRegistrationHandler;

public class MinestomRegistrationHandler<C> implements CommandRegistrationHandler<C> {
    @Override
    public boolean registerCommand(@NonNull Command<C> command) {
        return false;
    }
}
