package top.vrilhyc.mods.commandspy;

import net.minecraft.command.CommandSource;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.*;

public class SpyManager {
    protected Set<ServerPlayerEntity> spies;

    public SpyManager(){
        spies = new HashSet<>();
    }

    public void addSpyer(ServerPlayerEntity source){
        spies.add(source);
    }

    public void removeSpyer(ServerPlayerEntity source){
        spies.remove(source);
    }

    public boolean toggleSpy(ServerPlayerEntity source){
        if(spies.contains(source)){
            spies.remove(source);
            return false;
        }
        spies.add(source);
        return true;
    }

    public void broadcastSpies(ServerPlayerEntity serverPlayer,String message){
        String format = "§f[§cCSPY§f] §7%s ran the command /%s";
        spies.forEach(a->{
            if(a.getUuid().equals(serverPlayer.getUuid())){
                return;
            }
            Utils.displayMessage(a,format.formatted(serverPlayer.getName().getLiteralString(),message));
        });
    }

}
