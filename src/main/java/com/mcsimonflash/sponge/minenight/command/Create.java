package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;

@Aliases("create")
@Permission("minenight.command.create.base")
public class Create extends Command {

    @Inject
    private Create(Settings settings) {
        super(settings.usage(Text.of("/minenight create <name>")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        return CommandResult.empty(); //TODO
    }

}