package net.soldierzoom.misfortune.cmds;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class ModCmds {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CurseCommand.register(event.getDispatcher());
    }
}
