package com.mcsimonflash.sponge.minenight.internal;

import com.google.common.collect.Maps;
import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.game.Game;

import java.util.Map;
import java.util.UUID;

public class Manager {

    private static final Map<String, Game> games = Maps.newHashMap();
    private static final Map<UUID, Character> players = Maps.newHashMap();

    public static Map<String, Game> getGames() {
        return games;
    }

    public static Map<UUID, Character> getPlayers() {
        return players;
    }

}