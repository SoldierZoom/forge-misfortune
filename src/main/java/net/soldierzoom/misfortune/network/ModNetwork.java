package net.soldierzoom.misfortune.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.soldierzoom.misfortune.Misfortune;

public class ModNetwork {
    private static final SimpleChannel INSTANCE = NetworkRegistry.ChannelBuilder.named(
            ResourceLocation.fromNamespaceAndPath(Misfortune.MOD_ID, "main"))
            .serverAcceptedVersions(s -> true)
            .clientAcceptedVersions(s -> true)
            .networkProtocolVersion(() -> "1")
            .simpleChannel();
    private static int id = 0;

    public static void registerPackets() {
        INSTANCE.messageBuilder(S2C_CurseSyncPacket.class, id++,NetworkDirection.PLAY_TO_CLIENT)
                .encoder(S2C_CurseSyncPacket::encode)
                .decoder(S2C_CurseSyncPacket::new)
                .consumerMainThread(S2C_CurseSyncPacket::handle)
                .add();

    }

    //sends msg from server to specified player's client
    public static void sendToClient(Object msg, ServerPlayer sp) {
        INSTANCE.send(PacketDistributor.PLAYER.with(()->sp),msg);
    }
}

