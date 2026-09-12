package net.soldierzoom.misfortune.curse.setup;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.ICurse;
import net.soldierzoom.misfortune.network.ModNetwork;
import net.soldierzoom.misfortune.network.S2C_CurseSyncPacket;

public class CursePersistsOnDeath {
    //server side
    @SubscribeEvent
    public void onClone(PlayerEvent.Clone e) {
        // Copy old -> new
        e.getOriginal().reviveCaps();

        ICurse oldC = PlayerCurse.get(e.getOriginal());
        ICurse newC = PlayerCurse.get(e.getEntity());

        newC.set(oldC.get());

        e.getOriginal().invalidateCaps();
    }
    //sync client
    @SubscribeEvent
    public void onRespawn(PlayerEvent.PlayerRespawnEvent e) {
        if (!(e.getEntity() instanceof ServerPlayer sp)) return;

        ModNetwork.sendToClient(
                new S2C_CurseSyncPacket(PlayerCurse.get(sp).get()),
                sp
        );
    }
}
