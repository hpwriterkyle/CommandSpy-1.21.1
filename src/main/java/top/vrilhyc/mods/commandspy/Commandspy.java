package top.vrilhyc.mods.commandspy;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import top.vrilhyc.mods.commandspy.commands.SpyCommand;

public class Commandspy implements ModInitializer {
    public static SpyManager spyManager;

    @Override
    public void onInitialize() {
        spyManager = new SpyManager();
        CommandRegistrationCallback.EVENT.register((dispatcher, context, selection) -> {
            SpyCommand.register(dispatcher);
        });
    }
}
