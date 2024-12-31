package org.incendo.cloud.minestom;

import net.minestom.server.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.CommandManager;
import org.incendo.cloud.SenderMapper;
import org.incendo.cloud.execution.ExecutionCoordinator;

public class MinestomCommandManager<C> extends CommandManager<C> {
    @NonNull
    private final PermissionHandler<C> permissionHandler;
    @NonNull
    private final SenderMapper<CommandSender, C> senderMapper;

    /**
     * Create a new minestom command manager instance.
     *
     * @param executionCoordinator       Execution coordinator instance. When choosing the appropriate coordinator for your
     *                                   project, be sure to consider any limitations noted by the platform documentation.
     * @param permissionHandler          Permission handler instance. You'll need to implement this yourself since Minestom
     *                                   doesn't include a permission api
     */
    public MinestomCommandManager(@NonNull ExecutionCoordinator<C> executionCoordinator, @NonNull PermissionHandler<C> permissionHandler, @NonNull SenderMapper<CommandSender, C> senderMapper) {
        super(executionCoordinator, new MinestomRegistrationHandler<C>());
        this.permissionHandler = permissionHandler;
        this.senderMapper = senderMapper;
    }

    @Override
    public boolean hasPermission(@NonNull C sender, @NonNull String permission) {
        return permissionHandler.hasPermission(sender, permission);
    }

    public static MinestomCommandManager<CommandSender> createNative(
        @NonNull ExecutionCoordinator<CommandSender> executionCoordinator,
        @NonNull PermissionHandler<CommandSender> permissionHandler
    ) {
        return new MinestomCommandManager<>(
            executionCoordinator,
            permissionHandler,
            new MinestomCommandSenderMapper()
        );
    }
}
