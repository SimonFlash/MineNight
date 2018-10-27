package com.mcsimonflash.sponge.minenight.command;

import com.google.common.collect.ImmutableMap;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.internal.Inventory;
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

@Aliases("menu")
@Permission("minenight.command.menu.base")
public class Menu extends Command {

    @Inject
    private Menu(Settings settings) {
        super(settings.usage(Text.of("/minenight menu")));
    }

    @Override
    public CommandResult execute(final CommandSource src, final CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player, null);
        Inventory.gameMenu(Manager.PLAYERS.get(player.getUniqueId()), game).open(player);
        return CommandResult.success();
    }

}