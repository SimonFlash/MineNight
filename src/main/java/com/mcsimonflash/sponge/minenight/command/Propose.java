package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.MineNight;
import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.game.Game;
import com.mcsimonflash.sponge.minenight.game.Proposal;
import com.mcsimonflash.sponge.teslalibs.argument.Arguments;
import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;

@Aliases("propose")
@Permission("minenight.command.propose.base")
public class Propose extends Command {

    @Inject
    private Propose(Settings settings) {
        super(settings.usage(Text.of("/minenight propose <name>"))
                .elements(Arguments.string().toElement("name")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        Player player = CmdUtils.requirePlayer(src);
        Game game = CmdUtils.requireGame(player, Game.State.PROPOSAL);
        String name = args.requireOne("name");
        Character character = game.characters.stream().filter(c -> c.name.equalsIgnoreCase(name)).findFirst()
                .orElseThrow(() -> new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.propose.invalid-name", "name", name)));
        Proposal proposal = game.getCurrentNode().getCurrentProposal();
        if (proposal.owner.player != player.getUniqueId()) {
            throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.propose.not-proposing"));
        } else if (proposal.characters.contains(character)) {
            proposal.characters.remove(character);
            proposal.game.sendMessage(Text.of(character.name, " has been removed from the proposal."));
        } else if (proposal.characters.size() != proposal.node.players) {
            proposal.characters.add(character);
            proposal.game.sendMessage(Text.of(character.name, " has been added to the proposal."));
        } else {
            throw new CommandException(MineNight.getMessage(src.getLocale(), "minenight.command.propose.limit", "limit", proposal.node.players));
        }
        return CommandResult.success();
    }

}