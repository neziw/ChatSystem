package me.chudziudgi.chatsystem;

import me.chudziudgi.chatsystem.command.ChatCommand;
import me.chudziudgi.chatsystem.listener.ChatManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public class ChatSystem extends JavaPlugin {
    private static ChatSystem instance;

    public static ChatSystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("chat").setExecutor(new ChatCommand());
        getServer().getPluginManager().registerEvents(new ChatManager(), this);
    }
    public String getChatNoPermPremium() {
        return getConfig().getString("chatNoPermPremium");
    }
    public String getChatManagerCurrentlyOffline() {
        return getConfig().getString("chatManagerCurrentlyOffline");
    }
    public String getChatWrongMessage() {
        return getConfig().getString("chatWrongMessage");
    }
    public String getChatLastMessage() {
        return getConfig().getString("chatLastMessage");
    }
    public String getChatNoPerm() {
        return getConfig().getString("chatNoPerm");
    }

    public String getChatAlreadyEnabled() {
        return getConfig().getString("chatAlreadyEnabled");
    }

    public String getChatAlreadyDisabled() {
        return getConfig().getString("chatAlreadyDisabled");
    }
    public String getChatSetOff() {
        return getConfig().getString("chatSetOff");
    }
    public String getChatSetOn() {
        return getConfig().getString("chatSetOn");
    }
    public String getChatSetClear() {
        return getConfig().getString("chatSetClear");
    }
    public String getChatSetPremium() {
        return getConfig().getString("chatSetPremium");
    }
    public String getChatWrongUse() {
        return getConfig().getString("chatWrongUse");
    }
    public List<Character> getChatCharacterList() {
        return getConfig().getCharacterList("chatCharacterList");
    }

    public List<String> getChatBlockedWordList() {
        return getConfig().getStringList("getChatBlockedWordList");
    }

    public boolean getChatPremiumStatusValue() {
        return getConfig().getBoolean("chatPremiumStatusValue", true);
    }

    public void setChatPremiumStatusValue(boolean value) {
        getConfig().set("chatPremiumStatusValue", value);
        saveConfig();
    }

    public boolean getChatStatusValue() {
        return getConfig().getBoolean("chatStatusValue", true);
    }

    public void setChatStatusValue(boolean value) {
        getConfig().set("chatStatusValue", value);
        saveConfig();
    }
}