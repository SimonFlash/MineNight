package com.mcsimonflash.sponge.minenight.game;

import org.spongepowered.api.text.Text;

import java.util.UUID;

public class Character {

    public enum Role {
        AGENT, HACKER
    }

    private final UUID player;
    private final Text codename;
    Role role = Role.AGENT;

    public Character(UUID player, Text codename) {
        this.player = player;
        this.codename = codename;
    }

    public UUID getPlayer() {
        return player;
    }

    public Text getCodename() {
        return codename;
    }

    public Role getRole() {
        return role;
    }

}