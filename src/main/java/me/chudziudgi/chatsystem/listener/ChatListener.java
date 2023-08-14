package me.chudziudgi.chatsystem.listener;

import me.chudziudgi.chatsystem.Config;
import me.chudziudgi.chatsystem.util.ChatUtil;
import me.chudziudgi.chatsystem.util.ColorsUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatListener implements Listener {

    private final Config config;
    private final Map<UUID, Long> chatCooldowns;
    private final Map<Player, String> lastMessages;

    public ChatListener(final Config config) {
        this.config = config;
        this.chatCooldowns = new HashMap<>();
        this.lastMessages = new HashMap<>();
    }

    @EventHandler
    public void onChat(final AsyncPlayerChatEvent event) {
        final Player player = event.getPlayer();
        final String message = event.getMessage();
        final String filterBlockMessage = ChatUtil.filterBlockMessage(message);
        final String filteredMessage = ChatUtil.filteredMessage(message);
        event.setMessage(filterBlockMessage);
        if (this.config.isChatStatusValue()) {
            if (this.config.isChatPremiumStatusValue() && !player.hasPermission("system.chat.premium")) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorsUtil.format(this.config.getChatNoPermPremium())));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            if (!message.equals(filteredMessage)) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorsUtil.format(this.config.getChatWrongMessage())));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            if (this.lastMessages.containsKey(player) && this.lastMessages.get(player).equals(message)) {
                event.setCancelled(true);
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorsUtil.format(this.config.getChatLastMessage())));
                player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                return;
            }
            final UUID uniqueId = player.getUniqueId();
            final long currentTime = System.currentTimeMillis();
            if (this.chatCooldowns.containsKey(uniqueId)) {
                if (player.hasPermission("system.chat")) {
                    return;
                }
                final long timeRemaining = this.chatCooldowns.get(uniqueId) - currentTime;
                if (timeRemaining > 0) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorsUtil.format("&cPoczekaj &6" + String.format("%.2f", (timeRemaining / 1000.0)) + "ms &cprzed następną wiadomością!")));
                    player.playSound(player.getLocation(), Sound.ENTITY_RABBIT_ATTACK, 1.0F, 1.0F);
                    event.setCancelled(true);
                    return;
                }
            }
            this.chatCooldowns.put(uniqueId, currentTime + 5000L);
            this.lastMessages.put(player, message);
        } else {
            event.setCancelled(true);
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorsUtil.format(this.config.getChatManagerCurrentlyOffline())));
        }
    }
}