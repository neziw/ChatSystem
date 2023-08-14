package me.chudziudgi.chatsystem.command;

import me.chudziudgi.chatsystem.Config;
import me.chudziudgi.chatsystem.util.ColorsUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChatCommand implements TabExecutor {

    private final Config config;

    public ChatCommand(final Config config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command c, final String label, final String[] args) {
        if (sender.hasPermission("system.chat")) {
            if (args.length != 1) {
                this.sendMessage(sender, this.config.getChatWrongUse());
                return true;
            }
            if (args[0].equals("on")) {
                if (this.config.isChatStatusValue()) {
                    this.sendMessage(sender, this.config.getChatAlreadyEnabled());
                } else {
                    this.config.setChatStatusValue(true);
                    Bukkit.getOnlinePlayers().forEach(player -> this.sendMessage(player, this.config.getChatSetOn()));
                }
            } else if (args[0].equals("off")) {
                if (!this.config.isChatStatusValue()) {
                    this.sendMessage(sender, this.config.getChatAlreadyDisabled());
                } else {
                    this.config.setChatStatusValue(false);
                    Bukkit.getOnlinePlayers().forEach(player -> this.sendMessage(player, this.config.getChatSetOff()));
                }
            } else if (args[0].equals("clear")) {
                Bukkit.getOnlinePlayers().forEach(player -> {
                    for (int i = 0; i < 100; i++) {
                        this.sendMessage(player, " ");
                    }
                    this.sendMessage(player, this.config.getChatSetClear());
                    this.sendMessage(player, " ");
                });
            } else if (args[0].equals("premium")) {
                if (this.config.isChatPremiumStatusValue()) {
                    this.config.setChatStatusValue(true);
                    this.config.setChatPremiumStatusValue(false);
                    Bukkit.getOnlinePlayers().forEach(player -> this.sendMessage(player, this.config.getChatSetPremium()));
                } else {
                    this.config.setChatStatusValue(true);
                    this.config.setChatPremiumStatusValue(true);
                    Bukkit.getOnlinePlayers().forEach(player -> this.sendMessage(player, this.config.getChatSetPremium()));
                }
            } else {
                this.sendMessage(sender, this.config.getChatWrongUse());
            }
            return true;
        }
        this.sendMessage(sender, this.config.getChatNoPerm());
        return true;
    }

    @Override
    public List<String> onTabComplete(final CommandSender sender, final Command c, final String label, final String[] args) {
        if (args.length != 1 || !sender.hasPermission("system.chat")) return Collections.emptyList();
        final List<String> matches = new ArrayList<>();
        final String search = args[0].toLowerCase(Locale.ROOT);
        if ("on".startsWith(search)) {
            matches.add("on");
        }
        if ("off".startsWith(search)) {
            matches.add("off");
        }
        if ("clear".startsWith(search)) {
            matches.add("clear");
        }
        if ("premium".startsWith(search)) {
            matches.add("premium");
        }
        return matches;
    }

    private void sendMessage(final CommandSender sender, final String message) {
        sender.sendMessage(ColorsUtil.colorize(message));
    }
}