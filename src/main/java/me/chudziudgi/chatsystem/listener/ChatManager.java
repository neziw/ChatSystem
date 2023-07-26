package me.chudziudgi.chatsystem.listener;

import me.chudziudgi.chatsystem.ChatSystem;
import me.chudziudgi.chatsystem.Utils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.*;

public class ChatManager implements Listener {
    private final Map<UUID, Long> chatCooldowns = new HashMap<>();
    private final Map<Player, String> lastMessages = new HashMap<>();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();
        String filterBlockMessage = filterBlockMessage(message);
        String filteredMessage = filteredMessage(message);
        event.setMessage(filterBlockMessage);
        if (ChatSystem.getInstance().getChatStatusValue()) {
            if (ChatSystem.getInstance().getChatPremiumStatusValue() && !player.hasPermission("system.chat.premium")) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format(ChatSystem.getInstance().getChatNoPermPremium(), false)));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            if (!message.equals(filteredMessage)) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format(ChatSystem.getInstance().getChatWrongMessage(), false)));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            if (lastMessages.containsKey(player) && lastMessages.get(player).equals(message)) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format(ChatSystem.getInstance().getChatLastMessage(), false)));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            UUID playerId = player.getUniqueId();
            long currentTime = System.currentTimeMillis();
            if (chatCooldowns.containsKey(playerId)) {
                if (player.hasPermission("system.chat")) {
                    return;
                }
                long timeRemaining = chatCooldowns.get(playerId) - currentTime;
                if (timeRemaining > 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format("&cPoczekaj &6" + String.format("%.2f", (timeRemaining / 1000.0)) + "ms &cprzed następną wiadomością!", false)));
                    player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                    event.setCancelled(true);
                    return;
                }
            }
            chatCooldowns.put(playerId, currentTime + 5000L);
            lastMessages.put(player, message);
        } else {
            event.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Utils.format(ChatSystem.getInstance().getChatManagerCurrentlyOffline(), false)));
        }
    }

    private String filterBlockMessage(String message) {
        String[] words = message.split(" ");
        StringBuilder filterBlockMessage = new StringBuilder();

        for (String word : words) {
            if (ChatSystem.getInstance().getChatBlockedWordList().contains(word.toLowerCase())) {
                filterBlockMessage.append("*".repeat(word.length()));
            } else {
                filterBlockMessage.append(word);
            }

            filterBlockMessage.append(" ");
        }

        return filterBlockMessage.toString().trim();
    }

    private String filteredMessage(String message) {
        StringBuilder filteredMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetterOrDigit(c) || isCharacter(c)) {
                filteredMessage.append(c);
            }
        }
        return filteredMessage.toString();
    }

    private boolean isCharacter(char c) {
        for (char letter : ChatSystem.getInstance().getChatCharacterList()) {
            if (c == letter) {
                return true;
            }
        }
        return false;
    }
}