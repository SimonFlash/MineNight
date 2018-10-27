package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.game.Game;
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

@Aliases("propose")
@Permission("minenight.command.propose.base")
public class Propose extends Command {

    @Inject
    private Propose(Settings settings) {
        super(settings.usage(Text.of("/minenight propose <players>")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player);
        return CommandResult.empty(); //TODO
    }

}