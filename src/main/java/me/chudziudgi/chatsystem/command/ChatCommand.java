package me.chudziudgi.chatsystem.command;

import me.chudziudgi.chatsystem.ChatSystem;
import me.chudziudgi.chatsystem.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ChatCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) {
            if (!player.hasPermission("system.chat")) {
                player.sendMessage(Utils.format(ChatSystem.getInstance().getChatNoPerm(), false));
                return true;
            }
            if (player.hasPermission("system.chat")) {
                return true;
            }
            if (command.getName().equalsIgnoreCase("chat")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        if (ChatSystem.getInstance().getChatStatusValue()) {
                            player.sendMessage(Utils.format(ChatSystem.getInstance().getChatAlreadyEnabled(), false));
                            return true;
                        }
                        ChatSystem.getInstance().setChatStatusValue(true);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 100; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(Utils.format(ChatSystem.getInstance().getChatSetOn(), false));
                            all.sendMessage(Utils.format(" ", false));
                        }
                    } else if (args[0].equalsIgnoreCase("off")) {
                        if (!ChatSystem.getInstance().getChatStatusValue()) {
                            player.sendMessage(Utils.format(ChatSystem.getInstance().getChatAlreadyDisabled(), false));
                            return true;
                        }
                        ChatSystem.getInstance().setChatStatusValue(false);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 10; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(Utils.format(ChatSystem.getInstance().getChatSetOff(), false));
                            all.sendMessage(Utils.format(" ", false));
                        }
                    } else if (args[0].equalsIgnoreCase("clear")) {
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 100; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(Utils.format(ChatSystem.getInstance().getChatSetClear(), false));
                            all.sendMessage(Utils.format(" ", false));
                        }
                    } else if (args[0].equalsIgnoreCase("premium")) {
                        if (ChatSystem.getInstance().getChatPremiumStatusValue()) {
                            ChatSystem.getInstance().setChatStatusValue(true);
                            ChatSystem.getInstance().setChatPremiumStatusValue(false);
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                for (int i = 0; i < 10; i++) {
                                    all.sendMessage(" ");
                                }
                                all.sendMessage(Utils.format(ChatSystem.getInstance().getChatSetPremium(), false));
                                all.sendMessage(Utils.format(" ", false));
                            }
                        } else {
                            ChatSystem.getInstance().setChatStatusValue(true);
                            ChatSystem.getInstance().setChatPremiumStatusValue(true);
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                for (int i = 0; i < 10; i++) {
                                    all.sendMessage(" ");
                                }
                                all.sendMessage(Utils.format(ChatSystem.getInstance().getChatSetPremium(), false));
                                all.sendMessage(Utils.format(" ", false));
                            }
                        }
                    } else {
                        player.sendMessage(Utils.format(ChatSystem.getInstance().getChatWrongUse(), false));
                    }
                    return true;
                } else {
                    player.sendMessage(Utils.format(ChatSystem.getInstance().getChatWrongUse(), false));
                }
            }
            return true;
        }
        return false;
    }
}