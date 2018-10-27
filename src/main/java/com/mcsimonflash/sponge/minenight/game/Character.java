package com.mcsimonflash.sponge.minenight.game;

import java.util.UUID;

public class Character {

    public enum Role {
        AGENT, HACKER
    }

    public final UUID player;
    public final Game game;
    public final String name;
    public Role role = Role.AGENT;

    public Character(UUID player, Game game, String name) {
        this.player = player;
        this.game = game;
        this.name = name;
    }

}