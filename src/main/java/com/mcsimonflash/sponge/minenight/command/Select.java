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

@Aliases("select")
@Permission("minenight.command.select.base")
public class Select extends Command {

    @Inject
    private Select(Settings settings) {
        super(settings.usage(Text.of("/minenight select <secure/hack>"))
                .elements(Arguments.choices(ImmutableMap.of("secure", true, "hack", false), ImmutableMap.of("no-choice", "Available choices are secure or hack.")).toElement("selection")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player);
        boolean secure = args.requireOne("selection");
        return CommandResult.empty(); //TODO
    }

}