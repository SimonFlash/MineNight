package com.mcsimonflash.sponge.minenight.game;

import org.spongepowered.api.text.Text;

import java.util.UUID;

public class Character {

    public enum Role {
        AGENT, HACKER
    }

    private final UUID player;
    private final Game game;
    private final Text codename;
    private Role role = Role.AGENT;

    public Character(UUID player, Game game, Text codename) {
        this.player = player;
        this.game = game;
        this.codename = codename;
    }

    public UUID getPlayer() {
        return player;
    }

    public Game getGame() {
        return game;
    }

    public Text getCodename() {
        return codename;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

}