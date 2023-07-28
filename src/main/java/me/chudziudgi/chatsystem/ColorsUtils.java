package me.chudziudgi.chatsystem;

import org.bukkit.ChatColor;

public class ColorsUtils {

    private static final String PREFIX = ChatSystem.getInstance().getChatPrefix() + ChatColor.RESET;

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String addPrefix(String message) {
        return PREFIX + message;
    }

    public static String format(String message) {
        if (ChatSystem.getInstance().getChatPrefixStatusValue()) {
            return addPrefix(colorize(message));
        } else {
            return colorize(message);
        }
    }
}
