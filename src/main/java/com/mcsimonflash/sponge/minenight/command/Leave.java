package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.internal.Manager;
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

@Aliases("leave")
@Permission("minenight.command.leave.base")
public class Leave extends Command {

    @Inject
    private Leave(Settings settings) {
        super(settings.usage(Text.of("/minenight leave")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player, null);
        game.getCharacters().remove(player.getUniqueId());
        if (game.getCharacters().isEmpty()) {
            Manager.getGames().remove(game.getName());
        }
        Manager.getPlayers().remove(player.getUniqueId());
        MineNight.sendMessage(player, "minenight.command.leave.success", "game", game.getName());
        return CommandResult.success();
    }

}