package me.chudziudgi.chatsystem;

import eu.okaeri.configs.OkaeriConfig;

import java.util.List;

public class Config extends OkaeriConfig {

    private boolean chatStatusValue = true;
    private boolean chatPremiumStatusValue = false;
    private boolean chatPrefixStatusValue = true;
    private String chatPrefix = "§8[§4§l!§8] ";
    private String chatNoPerm = "&7Nie posiadasz uprawnienia &8(&6system.chat&8)";
    private String chatAlreadyEnabled = "&7Chat jest już &awłączony";
    private String chatAlreadyDisabled = "&7Chat jest już &cwyłączony";
    private String chatWrongUse = "&7Poprawne użycie  /chat on/off/clear/premium";
    private String chatSetOff = "          &8*&7*&8*  &7Chat został &c&nwyłączony&8  *&7*&8*          ";
    private String chatSetOn = "          &8*&7*&8* &7Chat został &a&nwłączony&8  *&7*&8*          ";
    private String chatSetClear = "          &8*&7*&8*  &7Chat został &6wyczyszczony&8  *&7*&8*          ";
    private String chatSetPremium = "          &8*&7*&8*  &7Chat wyłączonie dla &8(&dpremium&8)&8 *&7*&8*          ";
    private String chatManagerCurrentlyOffline = "&7Chat aktualnie jest dostępny wyłącznie dla &8(&dpremium&8)!";
    private String chatOffline = "&7Chat aktualnie jest &c&nwyłączony&c!";
    private String chatLastMessage = "&cNie wysyłaj tej samej wiadomości pod rząd!";
    private String chatWrongMessage = "&cTwoja wiadomość zawiera niedozwolone znaki!";
    private String chatNoPermPremium = "&7Chat aktualnie jest dostępny wyłącznie dla &8(&dpremium&8)!";
    private List<Character> chatCharacterList = List.of(
            'ą', 'ć', 'ę', 'ł', 'ń', 'ó', 'ś', 'ź', 'ż',
            'Ą', 'Ć', 'Ę', 'Ł', 'Ń', 'Ó', 'Ś', 'Ź', 'Ż',
            'ć', 'ę', 'ł', 'ń', 'ó', 'ś', 'ź', 'ż',
            'Ą', 'Ć', 'Ę', 'Ł', 'Ń', 'Ó', 'Ś', 'Ź', 'Ż',
            '$', '^', ';', '.', '/', '(', ')'
    );
    private List<String> chatBlockedWordList = List.of(
            "fuck", "kurwa", "jebac", "chuj", "chujnia", "jebany", "pierdole", "huj"
    );

    public boolean isChatStatusValue() {
        return this.chatStatusValue;
    }

    public void setChatStatusValue(final boolean chatStatusValue) {
        this.chatStatusValue = chatStatusValue;
    }

    public boolean isChatPremiumStatusValue() {
        return this.chatPremiumStatusValue;
    }

    public void setChatPremiumStatusValue(final boolean chatPremiumStatusValue) {
        this.chatPremiumStatusValue = chatPremiumStatusValue;
    }

    public boolean isChatPrefixStatusValue() {
        return this.chatPrefixStatusValue;
    }

    public void setChatPrefixStatusValue(final boolean chatPrefixStatusValue) {
        this.chatPrefixStatusValue = chatPrefixStatusValue;
    }

    public String getChatPrefix() {
        return this.chatPrefix;
    }

    public void setChatPrefix(final String chatPrefix) {
        this.chatPrefix = chatPrefix;
    }

    public String getChatNoPerm() {
        return this.chatNoPerm;
    }

    public void setChatNoPerm(final String chatNoPerm) {
        this.chatNoPerm = chatNoPerm;
    }

    public String getChatAlreadyEnabled() {
        return this.chatAlreadyEnabled;
    }

    public void setChatAlreadyEnabled(final String chatAlreadyEnabled) {
        this.chatAlreadyEnabled = chatAlreadyEnabled;
    }

    public String getChatAlreadyDisabled() {
        return this.chatAlreadyDisabled;
    }

    public void setChatAlreadyDisabled(final String chatAlreadyDisabled) {
        this.chatAlreadyDisabled = chatAlreadyDisabled;
    }

    public String getChatWrongUse() {
        return this.chatWrongUse;
    }

    public void setChatWrongUse(final String chatWrongUse) {
        this.chatWrongUse = chatWrongUse;
    }

    public String getChatSetOff() {
        return this.chatSetOff;
    }

    public void setChatSetOff(final String chatSetOff) {
        this.chatSetOff = chatSetOff;
    }

    public String getChatSetOn() {
        return this.chatSetOn;
    }

    public void setChatSetOn(final String chatSetOn) {
        this.chatSetOn = chatSetOn;
    }

    public String getChatSetClear() {
        return this.chatSetClear;
    }

    public void setChatSetClear(final String chatSetClear) {
        this.chatSetClear = chatSetClear;
    }

    public String getChatSetPremium() {
        return this.chatSetPremium;
    }

    public void setChatSetPremium(final String chatSetPremium) {
        this.chatSetPremium = chatSetPremium;
    }

    public String getChatManagerCurrentlyOffline() {
        return this.chatManagerCurrentlyOffline;
    }

    public void setChatManagerCurrentlyOffline(final String chatManagerCurrentlyOffline) {
        this.chatManagerCurrentlyOffline = chatManagerCurrentlyOffline;
    }

    public String getChatOffline() {
        return this.chatOffline;
    }

    public void setChatOffline(final String chatOffline) {
        this.chatOffline = chatOffline;
    }

    public String getChatLastMessage() {
        return this.chatLastMessage;
    }

    public void setChatLastMessage(final String chatLastMessage) {
        this.chatLastMessage = chatLastMessage;
    }

    public String getChatWrongMessage() {
        return this.chatWrongMessage;
    }

    public void setChatWrongMessage(final String chatWrongMessage) {
        this.chatWrongMessage = chatWrongMessage;
    }

    public String getChatNoPermPremium() {
        return this.chatNoPermPremium;
    }

    public void setChatNoPermPremium(final String chatNoPermPremium) {
        this.chatNoPermPremium = chatNoPermPremium;
    }

    public List<Character> getChatCharacterList() {
        return this.chatCharacterList;
    }

    public List<String> getChatBlockedWordList() {
        return this.chatBlockedWordList;
    }
}