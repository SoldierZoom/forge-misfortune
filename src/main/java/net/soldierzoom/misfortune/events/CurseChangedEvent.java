package net.soldierzoom.misfortune.events;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.Event;
import net.soldierzoom.misfortune.curse.main.CurseType;

public class CurseChangedEvent extends Event {
    private final Player player;
    private final CurseType curse;

    public CurseChangedEvent(Player player, CurseType curse) {
        this.player = player;
        this.curse = curse;
    }

    public Player getPlayer() {
        return player;
    }

    public CurseType getCurse() {
        return curse;
    }
}
