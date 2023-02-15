package com.github.ilja615.worldupgrade.networking.packet;

import com.github.ilja615.worldupgrade.client.ClientFogData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class FogDataSyncS2CPacket
{
    private final int fog;

    public FogDataSyncS2CPacket(int fog) {
        this.fog = fog;
    }

    public FogDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.fog = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(fog);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientFogData.set(fog);
        });
        return true;
    }
}
