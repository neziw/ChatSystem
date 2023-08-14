package me.chudziudgi.chatsystem.util;

import me.chudziudgi.chatsystem.ChatSystem;
import me.chudziudgi.chatsystem.Config;

public final class ChatUtil {

    private static final Config CONFIG = ChatSystem.getInstance().getConfiguration();

    public static String filteredMessage(final String message) {
        final StringBuilder filteredMessage = new StringBuilder();
        for (final char c : message.toCharArray()) {
            if (Character.isLetterOrDigit(c) || isCharacter(c)) {
                filteredMessage.append(c);
            }
        }
        return filteredMessage.toString();
    }

    public static boolean isCharacter(final char c) {
        for (final char letter : CONFIG.getChatCharacterList()) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }

    public static String filterBlockMessage(final String message) {
        final String[] words = message.split(" ");
        final StringBuilder filterBlockMessage = new StringBuilder();
        for (final String word : words) {
            if (CONFIG.getChatBlockedWordList().contains(word.toLowerCase())) {
                filterBlockMessage.append("*".repeat(word.length()));
            } else {
                filterBlockMessage.append(word);
            }
            filterBlockMessage.append(" ");
        }
        return filterBlockMessage.toString().trim();
    }
}