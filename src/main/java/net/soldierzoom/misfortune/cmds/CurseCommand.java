package net.soldierzoom.misfortune.cmds;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.soldierzoom.misfortune.curse.capability.PlayerCurse;
import net.soldierzoom.misfortune.curse.main.CurseType;

import java.util.Collection;
import java.util.Objects;

public class CurseCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("curse")
                        .then(Commands.literal("set")
                                .requires((source) -> source.hasPermission(4))
                                .then(Commands.argument("curse", StringArgumentType.word())
                                        .executes(CurseCommand::setCurse)
                                        .suggests(CURSE_TYPES)
                                        .then(Commands.argument("players", EntityArgument.players())
                                                .executes(CurseCommand::setCurses)
                                        )
                                )
                        )
                        .then(Commands.literal("get")
                                .executes(CurseCommand::getCurse)
                                .then(Commands.argument("player", EntityArgument.player())
                                        .requires((source) -> source.hasPermission(2))
                                        .executes(CurseCommand::getPlayerCurse)
                                )
                        )
        );
    }
    //suggestions
    private static final SuggestionProvider<CommandSourceStack> CURSE_TYPES =
            (context, builder) -> {
                for (CurseType curse : CurseType.values()) {
                    builder.suggest(curse.id());
                }
                return builder.buildFuture();
            };;

    //command funcs
    private static int getPlayerCurse(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, "player");
        CurseType curse = PlayerCurse.get(player).get();
        player.sendSystemMessage(Component.literal("Your curse is: " + curse.id()));
        return 1;
    }

    private static int getCurse(CommandContext<CommandSourceStack> context) {
        //gets player who executed cmd
        ServerPlayer player = Objects.requireNonNull(context.getSource().getPlayer());

        CurseType curse = PlayerCurse.get(player).get();
        player.sendSystemMessage(Component.literal("Your curse is: " + curse.id()));
        return 1;
    }

    private static int setCurses(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String curseId = StringArgumentType.getString(context, "curse");
        CurseType newCurse;
        try {
            newCurse = CurseType.valueOf(curseId.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS
                    .literalIncorrect().create(curseId);
        }

        Collection<? extends ServerPlayer> targets = EntityArgument.getPlayers(context, "players");
        for (ServerPlayer player : targets) {
            PlayerCurse.get(player).set(newCurse);
        }
        return targets.size();
    }

    private static int setCurse(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String curseId = StringArgumentType.getString(context, "curse");//gets curseid from arg
        CurseType newCurse;
        try {
            //trys to convert id to curseType
            newCurse = CurseType.valueOf(curseId.toUpperCase());
        } catch (IllegalArgumentException e) {
            //if it does not match throws arg error
            throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.dispatcherUnknownArgument().create();
        }
        //gets player to change curse type for
        ServerPlayer player = Objects.requireNonNull(context.getSource().getPlayer());
        PlayerCurse.get(player).set(newCurse);
        return 1;
    }

}
