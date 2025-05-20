package top.vrilhyc.mods.commandspy.mixin;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.impl.networking.server.ServerNetworkingImpl;
import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.network.packet.c2s.play.ChatCommandSignedC2SPacket;
import net.minecraft.network.packet.c2s.play.ChatMessageC2SPacket;
import net.minecraft.network.packet.c2s.play.ClientCommandC2SPacket;
import net.minecraft.server.network.ServerCommonNetworkHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.vrilhyc.mods.commandspy.Commandspy;
import top.vrilhyc.mods.commandspy.commands.SpyCommand;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class MixinServerPlayNetworkHandler {
	@Shadow
	public ServerPlayerEntity player;

	// Injection for player chatting
	@Inject(
			method = "executeCommand",
			at = @At(value = "HEAD")
	)
	private void onChatMessage(String command, CallbackInfo ci) {
		if(SpyCommand.isSomeGroup(player.getUuid(),"admin")){
			return;
		}
		Commandspy.spyManager.broadcastSpies(player,command);
	}
}