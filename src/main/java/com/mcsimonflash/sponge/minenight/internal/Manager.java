package com.mcsimonflash.sponge.minenight.internal;

import com.google.common.collect.Maps;
import com.mcsimonflash.sponge.minenight.game.Game;

import java.util.Map;

public class Manager {

    private static final Map<String, Game> games = Maps.newHashMap();

    public static Map<String, Game> getGames() {
        return games;
    }

}