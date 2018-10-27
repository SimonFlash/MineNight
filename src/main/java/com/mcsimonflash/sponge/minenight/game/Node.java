package com.mcsimonflash.sponge.minenight.game;

public class Node {

    private final int players;
    private final int hackers;
    private final Proposal[] proposals = new Proposal[5];

    public Node(int players, int hackers) {
        this.players = players;
        this.hackers = hackers;
    }

    public int getPlayers() {
        return players;
    }

    public int getHackers() {
        return hackers;
    }

}