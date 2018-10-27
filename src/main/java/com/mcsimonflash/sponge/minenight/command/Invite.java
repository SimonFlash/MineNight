package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.internal.Manager;
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

@Aliases("invite")
@Permission("minenight.command.invite.base")
public class Invite extends Command {

    @Inject
    private Invite(Settings settings) {
        super(settings.usage(Text.of("/minenight invite <player>"))
                .elements(Arguments.player().toElement("player")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player source = CmdUtils.requirePlayer(src);
        Player invitee = args.requireOne("player");
        Game game = CmdUtils.requireGame(source);
        if (Manager.getPlayers().containsKey(invitee.getUniqueId())) {
            throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.invite.already-in-game", "game", game.getName(), "player", invitee.getName()));
        }
        MineNight.sendMessage(source, "minenight.command.invite.success", "game", game.getName(), "player", invitee.getName());
        MineNight.sendMessage(invitee, "minenight.command.invite.message", "game", game.getName(), "player", source.getName());
        return CommandResult.success();
    }

}