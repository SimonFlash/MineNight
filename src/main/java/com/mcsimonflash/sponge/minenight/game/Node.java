package com.mcsimonflash.sponge.minenight.game;

public class Node {

    public final Game game;
    public final int players;
    public final Proposal[] proposals = new Proposal[5];
    public int proposal = -1;

    public Node(Game game, int players) {
        this.game = game;
        this.players = players;
    }

    public Proposal getCurrentProposal() {
        return proposals[proposal];
    }

    public Proposal startProposal(Character owner) {
        proposals[++proposal] = new Proposal(game, this, owner);
        return getCurrentProposal();
    }

}