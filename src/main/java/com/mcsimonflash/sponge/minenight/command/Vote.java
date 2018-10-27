package com.mcsimonflash.sponge.minenight.command;

import com.google.common.collect.ImmutableMap;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.teslalibs.argument.Arguments;
import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;

@Aliases("vote")
@Permission("minenight.command.vote.base")
public class Vote extends Command {

    @Inject
    private Vote(Settings settings) {
        super(settings.usage(Text.of("/minenight vote <accept/reject>"))
                .elements(Arguments.choices(ImmutableMap.of("approve", true, "reject", false), ImmutableMap.of("no-choice", "Available choices are approve or reject.")).toElement("vote")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player);
        boolean approve = args.requireOne("vote");
        return CommandResult.empty(); //TODO
    }

}