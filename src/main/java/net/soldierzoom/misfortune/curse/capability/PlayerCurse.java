package net.soldierzoom.misfortune.curse.capability;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.soldierzoom.misfortune.curse.main.CurseType;
import net.soldierzoom.misfortune.curse.main.ICurse;
import net.soldierzoom.misfortune.network.ModNetwork;
import net.soldierzoom.misfortune.network.S2C_CurseSyncPacket;

public class PlayerCurse {
    public static ICurse get(Player p) {
        return p.getCapability(CurseCapability.CURSE).orElseThrow(
                () -> new IllegalStateException("Curse capability missing on player!")
        );
    }

    public static boolean isAssigned(Player p) {
        return get(p).isAssigned();
    }

    public static void setAndSync(CurseType curse, ServerPlayer sp) {
        get(sp).set(curse);//set curse server side

        //sync with client
        ModNetwork.sendToClient(
                new S2C_CurseSyncPacket(curse),
                sp
        );

    }
}
