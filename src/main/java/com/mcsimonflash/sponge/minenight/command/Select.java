package com.mcsimonflash.sponge.minenight.command;

import com.mcsimonflash.sponge.teslalibs.command.Aliases;
import com.mcsimonflash.sponge.teslalibs.command.Command;
import com.mcsimonflash.sponge.teslalibs.command.Permission;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;

@Aliases("select")
@Permission("minenight.command.select.base")
public class Select extends Command {

    @Inject
    private Select(Settings settings) {
        super(settings.usage(Text.of("/minenight select <secure/hack>")));
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) {
        return CommandResult.empty(); //TODO
    }

}