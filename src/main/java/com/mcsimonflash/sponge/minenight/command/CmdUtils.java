package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.internal.Manager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;

import javax.annotation.Nullable;

public class CmdUtils {

    public static Player requirePlayer(CommandSource src) throws CommandException {
        if (src instanceof Player) {
            return (Player) src;
        }
        throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.player-only"));
    }

    public static Game requireGame(Player player, @Nullable Game.State state) throws CommandException {
        Character character = Manager.PLAYERS.get(player.getUniqueId());
        if (character == null) {
            throw new CommandException(MineNight.getMessage(player.getLocale(), "minenight.command.requires-game"));
        } else if (state != null && character.game.state != state) {
            throw new CommandException(MineNight.getMessage(player.getLocale(), "minenight.command.requires-state", "state", state));
        }
        return character.game;
    }

}