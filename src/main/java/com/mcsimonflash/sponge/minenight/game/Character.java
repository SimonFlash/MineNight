package com.mcsimonflash.sponge.minenight.game;

import java.util.UUID;

public class Character {

    public enum Role {
        AGENT, HACKER
    }

    private final UUID player;
    private final Game game;
    private final String name;
    Role role = Role.AGENT;
    boolean proposed;

    public Character(UUID player, Game game, String name) {
        this.player = player;
        this.game = game;
        this.name = name;
    }

    public UUID getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

}