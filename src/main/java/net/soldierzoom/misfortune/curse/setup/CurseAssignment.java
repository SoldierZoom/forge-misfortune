package net.soldierzoom.misfortune.curse.setup;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;

public class CurseAssignment {
    private static final CurseType[] POOL = {
            CurseType.BLINDNESS,
            CurseType.DEAFNESS,
            CurseType.CANT_FEEL
    };

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent e) {
        if (!(e.getEntity() instanceof ServerPlayer sp)) return;

        if (!PlayerCurse.isAssigned(sp)) {
            CurseType picked = pickRandom(sp.getRandom());
            PlayerCurse.get(sp).set(picked);
            // Step 5 later: send a sync packet to this client.
        }
    }

    private CurseType pickRandom(RandomSource rng) {
        return POOL[rng.nextInt(POOL.length)];
    }
}