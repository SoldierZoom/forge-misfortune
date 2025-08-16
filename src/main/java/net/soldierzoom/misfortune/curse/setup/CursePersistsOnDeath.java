package net.soldierzoom.misfortune.curse.setup;

import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.ICurse;

public class CursePersistsOnDeath {
    @SubscribeEvent
    public void onClone(PlayerEvent.Clone e) {
        // Copy old -> new
        ICurse oldC = PlayerCurse.get(e.getOriginal());
        ICurse newC = PlayerCurse.get(e.getEntity());
        newC.set(oldC.get());
    }
}
