package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.minenight.game.Character;
import com.mcsimonflash.sponge.minenight.internal.Manager;
import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Children;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.service.pagination.PaginationList;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Aliases({"minenight", "mn"})
@Permission("minenight.command.base")
@Children({Create.class, Invite.class, Join.class, Leave.class, Menu.class, Propose.class, Select.class, Vote.class})
public class Base extends Command {

    @Inject
    private Base(Settings settings) {
        super(settings.usage(Text.of("/minenight <subcommand>")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        if (src instanceof Player) {
            Character character = Manager.PLAYERS.get(((Player) src).getUniqueId());
            if (character != null) {
                character.game.startDiscussion();
            }
        }
        PaginationList.builder()
                .title(Text.of("Commands"))
                .padding(Text.of("="))
                .contents(Stream.concat(Stream.of(this), getChildren().stream())
                        .map(Command::getUsage)
                        .collect(Collectors.toList()))
                .sendTo(src);
        return CommandResult.success();
    }

}