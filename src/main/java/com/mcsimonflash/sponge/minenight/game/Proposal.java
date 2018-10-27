package com.mcsimonflash.sponge.minenight.game;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

public class Proposal {

    public final Game game;
    public final Node node;
    public final Character owner;
    public final List<Character> characters = Lists.newArrayList();
    public final Map<Character, Boolean> votes = Maps.newHashMap();
    public boolean modifiable;

    public Proposal(Game game, Node node, Character owner) {
        this.game = game;
        this.node = node;
        this.owner = owner;
    }

}