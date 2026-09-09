package net.soldierzoom.misfortune.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.world.entity.player.Player;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;

import java.util.Collection;

public class CurseCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("curse")
                        .then(Commands.literal("set")
                            .requires((source) -> source.hasPermission(2))
                            .then(Commands.argument("curse", StringArgumentType.word())
                                .executes(CurseCommand::setCurse)
                                .then(Commands.argument("players", EntityArgument.players())
                                    .executes(CurseCommand::setCurses))
                            )
                        )
        );
    }

    private static int setCurses(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        CurseType newCurse = CurseType.fromId(StringArgumentType.getString(context, "curse"));
        Collection<? extends Player> targets = EntityArgument.getPlayers(context, "players");
        for (Player player : targets) {
            PlayerCurse.get(player).set(newCurse);
        }
        return targets.size();
    }

    private static int setCurse(CommandContext<CommandSourceStack> context) {
        //gets curse type from cmd
        CurseType newCurse = CurseType.fromId(StringArgumentType.getString(context, "curse"));
        //gets player to change curse type for
        Player player = context.getSource().getPlayer();

        if(player != null) {
            PlayerCurse.get(player).set(newCurse);
            return 1;
        }
        return -1;
    }

}
