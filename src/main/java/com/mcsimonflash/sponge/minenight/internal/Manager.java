package com.mcsimonflash.sponge.minenight.internal;

import com.google.common.collect.Maps;
import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.game.Game;

import java.util.Map;
import java.util.UUID;

public class Manager {

    public static final Map<String, Game> GAMES = Maps.newHashMap();
    public static final Map<UUID, Character> PLAYERS = Maps.newHashMap();

}