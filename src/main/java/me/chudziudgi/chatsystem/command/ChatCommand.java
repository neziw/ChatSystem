package me.chudziudgi.chatsystem.command;

import me.chudziudgi.chatsystem.ChatSystem;
import me.chudziudgi.chatsystem.ColorsUtils;
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
                player.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatNoPerm()));
                return true;
            }
            if (command.getName().equalsIgnoreCase("chat")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("on")) {
                        if (ChatSystem.getInstance().getChatStatusValue()) {
                            player.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatAlreadyEnabled()));
                            return true;
                        }
                        ChatSystem.getInstance().setChatStatusValue(true);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 100; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatSetOn()));
                            all.sendMessage(ColorsUtils.format(" "));
                        }
                    } else if (args[0].equalsIgnoreCase("off")) {
                        if (!ChatSystem.getInstance().getChatStatusValue()) {
                            player.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatAlreadyDisabled()));
                            return true;
                        }
                        ChatSystem.getInstance().setChatStatusValue(false);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 10; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatSetOff()));
                            all.sendMessage(ColorsUtils.format(" "));
                        }
                    } else if (args[0].equalsIgnoreCase("clear")) {
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 100; i++) {
                                all.sendMessage(" ");
                            }
                            all.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatSetClear()));
                            all.sendMessage(ColorsUtils.format(" "));
                        }
                    } else if (args[0].equalsIgnoreCase("premium")) {
                        if (ChatSystem.getInstance().getChatPremiumStatusValue()) {
                            ChatSystem.getInstance().setChatStatusValue(true);
                            ChatSystem.getInstance().setChatPremiumStatusValue(false);
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                for (int i = 0; i < 10; i++) {
                                    all.sendMessage(" ");
                                }
                                all.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatSetPremium()));
                                all.sendMessage(ColorsUtils.format(" "));
                            }
                        } else {
                            ChatSystem.getInstance().setChatStatusValue(true);
                            ChatSystem.getInstance().setChatPremiumStatusValue(true);
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                for (int i = 0; i < 10; i++) {
                                    all.sendMessage(" ");
                                }
                                all.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatSetPremium()));
                                all.sendMessage(ColorsUtils.format(" "));
                            }
                        }
                    } else {
                        player.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatWrongUse()));
                    }
                    return true;
                } else {
                    player.sendMessage(ColorsUtils.format(ChatSystem.getInstance().getChatWrongUse()));
                }
            }
            return true;
        }
        return false;
    }
}