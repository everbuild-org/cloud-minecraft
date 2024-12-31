//
// MIT License
//
// Copyright (c) 2024 Incendo
//
// Permission is hereby granted, free of charge, to any person obtaining a copy
// of this software and associated documentation files (the "Software"), to deal
// in the Software without restriction, including without limitation the rights
// to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// SOFTWARE.
//
package org.incendo.cloud.examples.minestom;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.minestom.MinestomCommandManager;

import static org.incendo.cloud.parser.standard.IntegerParser.integerParser;

public final class MinestomServer {
    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();

        Instance instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        instance.setChunkSupplier(LightingChunk::new);
        instance.setGenerator(unit ->
            unit
                .modifier()
                .fillHeight(0, 64, Block.WHITE_STAINED_GLASS)
        );

        GlobalEventHandler globalEvents = MinecraftServer.getGlobalEventHandler();
        globalEvents.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            event.setSpawningInstance(instance);
            event.getPlayer().setRespawnPoint(new Pos(0, 65, 0));
            event.getPlayer().setGameMode(GameMode.CREATIVE);
        });

        MinestomCommandManager<CommandSender> commandManager = MinestomCommandManager.createNative(
            ExecutionCoordinator.simpleCoordinator(),
            (sender, permission) -> false
        );

        commandManager.command(
            commandManager.commandBuilder("command")
                .required("number", integerParser())
                .handler(context ->
                    context.sender().sendMessage("You entered " + context.get("number"))
                )
        );

        server.start("0.0.0.0", 25565);
    }
}
