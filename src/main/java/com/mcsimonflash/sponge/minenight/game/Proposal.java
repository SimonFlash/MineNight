package com.mcsimonflash.sponge.minenight.game;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Proposal {

    private final Node node;
    private final List<Character> players = Lists.newArrayList();
    private final Map<UUID, Boolean> votes = Maps.newHashMap();

    public Proposal(Node node) {
        this.node = node;
    }

    public List<Character> getPlayers() {
        return players;
    }

    public Map<UUID, Boolean> getVotes() {
        return votes;
    }

}