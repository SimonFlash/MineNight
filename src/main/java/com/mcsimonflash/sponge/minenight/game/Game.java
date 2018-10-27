package com.mcsimonflash.sponge.minenight.game;

import com.google.common.collect.Lists;
import com.mcsimonflash.sponge.minenight.MineNight;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Game {

    public enum State {
        PREGAME, DISCUSSION, PROPOSAL, VOTING, SELECTION, POSTGAME
    }

    public final String name;
    public final List<Character> characters = Lists.newArrayList();
    public final Node[] nodes = new Node[] {
            new Node(this, 2),
            new Node(this, 3),
            new Node(this, 2),
            new Node(this, 3),
            new Node(this, 4)};
    public State state = State.PREGAME;
    public Task task;
    public int node = 0;
    public int owner = 0;

    public Game(String name) {
        this.name = name;
    }

    public Node getCurrentNode() {
        return nodes[node];
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
        getCurrentNode().startProposal(characters.get(owner));
        sendMessage(Text.of("Starting proposal phase."));
        startTask(() -> {
            sendMessage(Text.of("Ending proposal phase."));
            startDiscussion();
        });
    }

    public void sendMessage(Text message) {
        characters.forEach(c -> Sponge.getServer().getPlayer(c.player).ifPresent(p -> p.sendMessage(message)));
    }

    private void startTask(Runnable runnable) {
        task = Task.builder()
                .execute(runnable)
                .delay(30, TimeUnit.SECONDS)
                .submit(MineNight.getContainer());
    }

}