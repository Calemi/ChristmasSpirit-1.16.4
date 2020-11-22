package com.tm.cspirit.packet;

import com.tm.cspirit.entity.EntityReindeer;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketReindeerJump {

    private boolean jump;

    public PacketReindeerJump () {}

    public PacketReindeerJump (boolean jump) {
        this.jump = jump;
    }

    public PacketReindeerJump (PacketBuffer buf) {
        jump = buf.readBoolean();
    }

    public void toBytes (PacketBuffer buf) {
        buf.writeBoolean(jump);
    }

    public void handle (Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayerEntity player = ctx.get().getSender();

            if (player != null && player.getRidingEntity() instanceof EntityReindeer) {
                EntityReindeer reindeer = (EntityReindeer) player.getRidingEntity();
                reindeer.getDataManager().set(EntityReindeer.JUMP_KEY, jump);
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
