package com.mcsimonflash.sponge.minenight.command;

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

@Aliases("create")
@Permission("minenight.command.create.base")
public class Create extends Command {

    @Inject
    private Create(Settings settings) {
        super(settings.usage(Text.of("/minenight create <name>"))
                .elements(Arguments.string().toElement("name")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        String name = args.requireOne("name");
        if (Manager.getGames().containsKey(name)) {
            throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.create.already-exists", "name", name));
        }
        Game game = new Game(name);
        Character character = new Character(player.getUniqueId(), game, player.getName());
        Manager.getGames().put(game.getName().toLowerCase(), game);
        Manager.getPlayers().put(player.getUniqueId(), character);
        game.getCharacters().put(character.getName(), character);
        MineNight.sendMessage(player, "minenight.command.create.success", "game", game.getName());
        return CommandResult.success();
    }

}