package net.soldierzoom.misfortune.cmds;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.soldierzoom.misfortune.Misfortune;

@Mod.EventBusSubscriber(modid = Misfortune.MOD_ID)
public class ModCmds {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CurseCommand.register(event.getDispatcher());
    }
}
