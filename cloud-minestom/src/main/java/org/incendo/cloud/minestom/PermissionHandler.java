package org.incendo.cloud.minestom;

/**
 * A functional interface for handling permissions in Minestom command systems.
 * This interface abstracts the logic for checking if a given command sender
 * has the necessary permission to execute a specific action.
 *
 * @param <C> the type of the command sender
 */
@FunctionalInterface
public interface PermissionHandler<C> {
    boolean hasPermission(C sender, String permission);
}
