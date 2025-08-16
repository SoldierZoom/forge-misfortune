package net.soldierzoom.misfortune.curse.capability;

import net.minecraft.world.entity.player.Player;
import net.soldierzoom.misfortune.curse.main.ICurse;

public class PlayerCurse {
    public static ICurse get(Player p) {
        return p.getCapability(CurseCapability.CURSE).orElseThrow(
                () -> new IllegalStateException("Curse capability missing on player!")
        );
    }

    public static boolean isAssigned(Player p) {
        return get(p).isAssigned();
    }
}
