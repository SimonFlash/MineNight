package com.mcsimonflash.sponge.minenight;

import com.mcsimonflash.sponge.minenight.command.Base;
import com.mcsimonflash.sponge.teslalibs.command.CommandService;
import com.mcsimonflash.sponge.teslalibs.message.MessageService;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.GameReloadEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Locale;

@Plugin(id = "minenight", name = "MineNight", version = "1.0.0", authors = "Simon_Flash")
public class MineNight {

    private static MineNight instance;
    private static PluginContainer container;
    private static Logger logger;
    private static CommandService commands;
    private static MessageService messages;

    @Inject
    private MineNight(PluginContainer c) {
        instance = this;
        container = c;
        logger = c.getLogger();
        commands = CommandService.of(c);
        MessageService m;
        try {
            Path path = Sponge.getConfigManager().getPluginConfig(c).getDirectory().resolve("messages");
            Files.createDirectories(path);
            c.getAsset("messages.properties").get().copyToDirectory(path);
            m = MessageService.of(path, "messages");
        } catch (IOException e) {
            m = MessageService.of(c, "messages");
            e.printStackTrace();
        }
        messages = m;
    }

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        commands.register(Base.class);
    }

    @Listener
    public void onReload(GameReloadEvent event) {
        messages.reload();
    }

    public static MineNight getInstance() {
        return instance;
    }

    public static PluginContainer getContainer() {
        return container;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static Text getMessage(Locale locale, String key, Object... args) {
        return messages.get(key, locale).args(args).toText();
    }

    public static void sendMessage(CommandSource src, String key, Object... args) {
        src.sendMessage(getMessage(src.getLocale(), key, args));
    }

}