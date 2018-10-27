package com.mcsimonflash.sponge.minenight.game;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.UUID;

public class Game {

    public enum State {
        PREGAME, DISCUSSION, PROPOSAL, VOTING, SELECTION, POSTGAME
    }

    private final String name;
    private final Map<UUID, Character> characters = Maps.newHashMap();
    private final Node[] nodes = new Node[5];
    private State state = State.PREGAME;
    private int node = 0;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<UUID, Character> getCharacters() {
        return characters;
    }

    public State getState() {
        return state;
    }

    public void setState(final State state) {
        this.state = state;
    }

}