package com.mcsimonflash.sponge.minenight.game;

import com.google.common.collect.Maps;
import com.mcsimonflash.sponge.minenight.MineNight;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Game {

    public enum State {
        PREGAME, DISCUSSION, PROPOSAL, VOTING, SELECTION, POSTGAME
    }

    private final String name;
    private final Map<String, Character> characters = Maps.newHashMap();
    private final Node[] nodes = new Node[5];
    private State state = State.PREGAME;
    private int node = 0;
    private int proposed;
    private Task task;

    public Game(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Character> getCharacters() {
        return characters;
    }

    public State getState() {
        return state;
    }

    public Proposal getCurrentProposal() {
        return nodes[node].getProposal();
    }

    public void startDiscussion() {
        state = State.DISCUSSION;
        sendMessage(Text.of("Starting discussion phase."));
        startTask(() -> {
            sendMessage(Text.of("Ending discussion phase."));
            startProposal();
        });
    }

    public void startProposal() {
        state = State.PROPOSAL;
        sendMessage(Text.of("Starting proposal phase."));
        startTask(() -> {
            sendMessage(Text.of("Ending proposal phase."));
            startDiscussion();
        });
    }

    public void propose(Character character) {
        if (character.proposed = !character.proposed) {
            sendMessage(Text.of(character.getName(), " has been added to the proposal."));
        } else {
            sendMessage(Text.of(character.getName(), " has been removed from the proposal."));
        }
    }

    private void startTask(Runnable runnable) {
        task = Task.builder()
                .execute(runnable)
                .delay(30, TimeUnit.SECONDS)
                .submit(MineNight.getContainer());
    }

    private void sendMessage(Text message) {
        getCharacters().keySet().forEach(k -> Sponge.getServer().getPlayer(k).ifPresent(p -> p.sendMessage(message)));
    }

}