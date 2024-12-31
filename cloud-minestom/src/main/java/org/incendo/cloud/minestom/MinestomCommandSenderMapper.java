package org.incendo.cloud.minestom;

import net.minestom.server.command.CommandSender;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.incendo.cloud.SenderMapper;

/**
 * No-op implementation of the SenderMapper interface
 */
public class MinestomCommandSenderMapper implements SenderMapper<CommandSender, CommandSender> {
    @Override
    public @NonNull CommandSender map(@NonNull CommandSender base) {
        return base;
    }

    @Override
    public @NonNull CommandSender reverse(@NonNull CommandSender mapped) {
        return mapped;
    }
}
