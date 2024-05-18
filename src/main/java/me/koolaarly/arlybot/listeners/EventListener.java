package me.koolaarly.arlybot.listeners;

import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.attribute.IGuildChannelContainer;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Objects;

public class EventListener extends ListenerAdapter {

    /**
     * Overrides the event that happens when someone reacts on a message in any channel.
     * Gets all the information bounded to the reaction provided by the event.
     * @param e variable for the onMessageReactionAddEvent.
     */
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        User user = e.getUser();
        String emoji = e.getReaction().getEmoji().getName();
        String channelMention = e.getChannel().getAsMention();
        String jumpLink = e.getJumpUrl();
        String emojiChecker = e.getReaction().getEmoji().getName();
        String[] basedEmojiList = {
                "BearyMeAlive", "Beluga", "Cope", "DabMeUpR", "DogeTriPoloski",
                "GigaChadette", "Heartache", "Kek", "Kinderspiel", "Lauinator",
                "MrKrabsMoneyGainer", "PepeMods", "Pride", "Quirky", "Snakespeak",
                "Zucced", "cat_wtf", "doge_shy", "doggin", "forsenDiglett",
                "grableft", "grabright", "huh", "joe_cool", "litol_shy",
                "nerd~1", "shy_blushy_cursed", "catkiss", "your_cum"
        };


        System.out.println("Reaction found: " + emoji + "  -/-  " + emojiChecker); //Debugging

        /**
         * Creats two strings.
         * Unbased: Gets sent when the reaction emoji was not provided by the server.
         * based: Gets sent when the reaction emoji was provided by the server.
         */
        assert user != null : "user object cannot be null";
        String msgUnbased = user.getAsMention() + " ist so ein peinlicher Nuttensohn. Reacted in " + channelMention + " mit   " + emoji + ".   Drecks Reaction.";
        String msgBased = user.getAsMention() + " ist so ein geiler Typ. Er nutzt based Reactions in " + channelMention + ". MazzaFacka";
        TextChannel channel = e.getGuild().getTextChannelById("698189077916287064");

        for (int i = 0; i < basedEmojiList.length; i++) {
            // Based Reaction
            if (emojiChecker.equals(basedEmojiList[i])) {
                assert channel != null;
                channel.sendMessage(msgBased).queue(
                    success -> System.out.println("Message sent successfully"),  //Debugging
                    error -> System.out.println("Failed to send message: " + error.getMessage()) //Debugging
                );
                break;
            // Unbased Reaction
            } else if (i == basedEmojiList.length - 1) {
                assert channel != null;
                channel.sendMessage(msgUnbased).queue(
                        success -> System.out.println("Message sent successfully"),  //Debugging
                        error -> System.out.println("Failed to send message: " + error.getMessage()) //Debugging
                );
            }
        }
    }

    /**
     * Overrides the event that happens when a user is typing.
     * Grabs information about the user and the channel where the event happend.
     * @param e variable for the onUserTypingEvent.
     */
    @Override 
    public void onUserTyping(UserTypingEvent e) {
        String user = e.getUser().getEffectiveName();
        String channelWhereTypingHappend = e.getChannel().getAsMention();
        String channelDebugging = e.getChannel().getName();

        TextChannel channel = Objects.requireNonNull(e.getGuild()).getTextChannelById("859508033259438100");

        // Msg buider
        String msg = "-------------------------\n"
                + "Caught typing, idiot.\n"
                + "Idiot: " + user + "\n"
                + "Channel: " + channelWhereTypingHappend + "\n-------------------------";

        System.out.println("Found user typing: " + user + " in " + channelDebugging); // Debugging
        assert channel != null;
        channel.sendMessage(msg).queue();


    }
}


