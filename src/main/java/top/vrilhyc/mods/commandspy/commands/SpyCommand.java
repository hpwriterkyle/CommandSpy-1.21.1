package top.vrilhyc.mods.commandspy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import top.vrilhyc.mods.commandspy.Commandspy;
import top.vrilhyc.mods.commandspy.Utils;

import java.util.UUID;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class SpyCommand {
    private static final String MESSAGE_KEY = "message";

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> builder = literal("commandspy");
        builder.executes(a->{
            String channelName = "staff";
            if (!(a.getSource().getPlayer() instanceof ServerPlayerEntity serverPlayer)) {
                return 1;
            }
            if(!isSomeGroup(serverPlayer.getUuid(),"toggle")){
                return 1;
            }
            if(Commandspy.spyManager.toggleSpy(serverPlayer)) {
                Utils.displayMessage(serverPlayer, "Enabled Commandspy");
                return 1;
            }
            Utils.displayMessage(serverPlayer, "Disabled Commandspy");
            return 1;
        });

        dispatcher.register(builder);
    }

    public static boolean isSomeGroup(UUID who,String group) {
//        try {
//            return LuckPermsProvider.get().getUserManager().loadUser(who)
//                    .thenApplyAsync(user -> {
//                        Collection<Group> inheritedGroups = user.getInheritedGroups(user.getQueryOptions());
//                        return inheritedGroups.stream().anyMatch(g -> g.getName().equals(group));
//                    }).get()
//
//
//                    ;
//        } catch (Exception ignored) {
//        return true;
        try {
            return Permissions.check(who, "commandspy."+group).get();
        }catch (Exception|Error ex){
            return true;
        }
//        }
    }
}
