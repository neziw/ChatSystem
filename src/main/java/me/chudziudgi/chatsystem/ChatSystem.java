package me.chudziudgi.chatsystem;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import me.chudziudgi.chatsystem.command.ChatCommand;
import me.chudziudgi.chatsystem.listener.ChatListener;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ChatSystem extends JavaPlugin {

    private static ChatSystem instance;
    private Config configuration;

    @Override
    public void onEnable() {
        instance = this;
        this.configuration = ConfigManager.create(Config.class, (it -> {
            it.withConfigurer(new YamlBukkitConfigurer());
            it.withBindFile(new File(this.getDataFolder(), "config.yml"));
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        }));
        final ChatCommand command = new ChatCommand(this.configuration);
        this.getCommand("chat").setExecutor(command);
        this.getCommand("chat").setTabCompleter(command);
        this.getServer().getPluginManager().registerEvents(new ChatListener(this.configuration), this);
    }

    @Override
    public void onDisable() {
        instance = null;
        HandlerList.unregisterAll(this);
    }

    public static ChatSystem getInstance() {
        return instance;
    }

    public Config getConfiguration() {
        return this.configuration;
    }
}