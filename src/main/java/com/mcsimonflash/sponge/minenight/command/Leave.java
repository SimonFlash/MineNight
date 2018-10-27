package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;

@Aliases("leave")
@Permission("minenight.command.leave.base")
public class Leave extends Command {

    @Inject
    private Leave(Settings settings) {
        super(settings.usage(Text.of("/minenight leave")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        return CommandResult.empty(); //TODO
    }

}