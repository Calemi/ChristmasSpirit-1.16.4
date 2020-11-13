package com.tm.cspirit.packet;

import com.tm.cspirit.init.InitSounds;
import com.tm.cspirit.present.PresentConstructor;
import com.tm.cspirit.util.Location;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SPlaySoundPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketWrapPresent {

    private PresentConstructor constructor;
    private BlockPos pos;

    public PacketWrapPresent () {}

    public PacketWrapPresent (PresentConstructor constructor, BlockPos pos) {
        this.constructor = constructor;
        this.pos = pos;
    }

    public PacketWrapPresent (PacketBuffer buf) {
        constructor = PresentConstructor.fromBytes(buf);
        pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }

    public void toBytes (PacketBuffer buf) {
        constructor.toBytes(buf);
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public void handle (Supplier<NetworkEvent.Context> ctx) {

        ctx.get().enqueueWork(() -> {

            ServerPlayerEntity player = ctx.get().getSender();

            if (player != null) {
                Location location = new Location(player.world, pos);
                constructor.toBlock(location);
                player.connection.sendPacket(new SPlaySoundPacket(InitSounds.PRESENT_WRAP.get().getName(), SoundCategory.AMBIENT, player.getPositionVec(), 1, 1));
            }
        });

        ctx.get().setPacketHandled(true);
    }
}
