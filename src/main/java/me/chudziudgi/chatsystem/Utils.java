package me.chudziudgi.chatsystem;

import org.bukkit.ChatColor;

public class Utils {

    private static final String PREFIX = "§8[§4§l!§8] " + ChatColor.RESET;

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String addPrefix(String message) {
        return PREFIX + message;
    }

    public static String format(String message, boolean includePrefix) {
        if (includePrefix) {
            return addPrefix(colorize(message));
        } else {
            return colorize(message);
        }
    }
}
