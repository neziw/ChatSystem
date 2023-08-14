package me.chudziudgi.chatsystem.util;

import me.chudziudgi.chatsystem.ChatSystem;
import me.chudziudgi.chatsystem.Config;
import org.bukkit.ChatColor;

public final class ColorsUtil {

    private static final Config CONFIG = ChatSystem.getInstance().getConfiguration();;
    private static final String PREFIX = CONFIG.getChatPrefix() + ChatColor.RESET;

    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String addPrefix(final String message) {
        return PREFIX + message;
    }

    public static String format(final String message) {
        if (CONFIG.isChatPrefixStatusValue()) {
            return addPrefix(colorize(message));
        }
        return colorize(message);
    }
}