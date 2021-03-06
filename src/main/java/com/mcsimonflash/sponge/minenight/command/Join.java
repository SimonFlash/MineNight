package com.mcsimonflash.sponge.minenight.command;

import com.google.common.collect.ImmutableMap;
import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Character;
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

@Aliases("join")
@Permission("minenight.command.join.base")
public class Join extends Command {

    @Inject
    private Join(Settings settings) {
        super(settings.usage(Text.of("/minenight join <game>"))
                .elements(Arguments.choices(Manager.GAMES, ImmutableMap.of("no-choice", "No game is available with the name <arg>.")).toElement("game")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = args.requireOne("game");
        if (Manager.PLAYERS.containsKey(player.getUniqueId())) {
            throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.join.already-in-game"));
        }
        Character character = new Character(player.getUniqueId(), game, player.getName());
        Manager.PLAYERS.put(player.getUniqueId(), character);
        game.characters.add(character);
        MineNight.sendMessage(player, "minenight.command.join.success", "game", game.name);
        return CommandResult.success();
    }

}