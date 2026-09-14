package net.soldierzoom.misfortune.network;

import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.network.NetworkEvent;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;
import net.soldierzoom.misfortune.events.CurseChangedEvent;

import java.util.function.Supplier;

public class S2C_CurseSyncPacket {
    private final CurseType curse;

    public S2C_CurseSyncPacket(CurseType curse) {
        this.curse = curse;
    }

    public S2C_CurseSyncPacket(FriendlyByteBuf buffer) {
        this(buffer.readEnum(CurseType.class));
    }

    public void encode(FriendlyByteBuf buffer) {
        buffer.writeEnum(curse);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        // Get the client-side player
        LocalPlayer player = net.minecraft.client.Minecraft.getInstance().player;
        if (player != null) {
            PlayerCurse.get(player).set(curse);
        }

        MinecraftForge.EVENT_BUS.post(
                new CurseChangedEvent(player, curse)
        );

        supplier.get().setPacketHandled(true);
    }
}
