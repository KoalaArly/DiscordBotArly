package me.koolaarly.arlybot.listeners;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.attribute.IGuildChannelContainer;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.user.UserTypingEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EventListener extends ListenerAdapter {

    /**
     * Overrides the event that happens when someone reacts on a message in any channel.
     * Gets all the information bounded to the reaction provided by the event.
     * @param e variable for the onMessageReactionAddEvent.
     */
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        User user = e.getUser();                                    // gets the user that reacted as a User
        String emoji = e.getReaction().getEmoji().getName();        // gets the emoji from the reaction as a String
        String channelMention = e.getChannel().getAsMention();      // gets the channel where the reaction happend as a Mention
        String jumpLink = e.getJumpUrl();                           // gets the jumpLink from the message that got reacted on as a String

        // List of all the emojis that have
        List<String> basedEmojiList = Arrays.asList("BearyMeAlive", "Beluga", "Cope", "DabMeUpR", "DogeTriPoloski",
                "GigaChadette", "Heartache", "Kek", "Kinderspiel", "Lauinator",
                "MrKrabsMoneyGainer", "PepeMods", "Pride", "Quirky", "Snakespeak",
                "Zucced", "cat_wtf", "doge_shy", "doggin", "forsenDiglett",
                "grableft", "grabright", "huh", "joe_cool", "litol_shy",
                "nerd~1", "your_cum", "catkiss", "shy_blushy_cursed");



        System.out.println("Reaction found: " + user + "  -/-  " + emoji);                          //Debugging

        // Creates two strings.
        // Unbased: Gets sent when the reaction emoji was not provided by the server.
        // Based: Gets sent when the reaction emoji was provided by the server.
        assert user != null : "user object cannot be null";
        String msgUnbased = user.getAsMention() + " ist so ein peinlicher Nuttensohn. Reacted in " + channelMention + " mit   " + emoji + ".   Drecks Reaction.";
        String msgBased = user.getAsMention() + " ist so ein geiler Typ. Er nutzt based Reactions in " + channelMention + ". MazzaFacka";
        TextChannel channel = (TextChannel) e.getChannel();

        // Based Reaction
        if (basedEmojiList.contains(emoji)) {
            assert channel != null;
            channel.sendMessage(msgBased).queue(
                    success -> System.out.println("Message sent successfully"),                     //Debugging
                    error -> System.out.println("Failed to send message: " + error.getMessage())    //Debugging
            );

        } else if (user.getId().equals("1240870403585474570")) {
            System.out.printf("\nReaction ignored: is by ArlyBot");
        }
        else {
            // Unbased Reaction
            assert channel != null;
            channel.sendMessage(msgUnbased).queue(
                    success -> System.out.println("Message sent successfully"),                     //Debugging
                    error -> System.out.println("Failed to send message: " + error.getMessage()));  //Debugging
        }
    }


//    /**
//     * Overrides the event that happens when a user is typing.
//     * Grabs information about the user and the channel where the event happend.
//     * @param e variable for the onUserTypingEvent.
//     */
//    @Override
//    public void onUserTyping(UserTypingEvent e) {
//        String user = e.getUser().getEffectiveName();                                                               // gets the user that reacted as a String
//        String channelWhereTypingHappend = e.getChannel().getAsMention();                                           // gets the channel where the typing happend as a Mention
//        String channelDebugging = e.getChannel().getName();                                                         // gets the channel where the typing happend as a String for debugging
//        TextChannel channel = Objects.requireNonNull(e.getGuild()).getTextChannelById("859508033259438100");        // gets the channel where the message will be sent to as a TextChannel
//
//        // Msg buider
//        String msg = "-------------------------\n"
//                + "Caught typing, idiot.\n"
//                + "Idiot: " + user + "\n"
//                + "Channel: " + channelWhereTypingHappend + "\n-------------------------";
//
//        System.out.println("Found user typing: " + user + " in " + channelDebugging); // Debugging
//        assert channel != null;
//        channel.sendMessage(msg).queue();
//
//    }

    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String user = e.getAuthor().getEffectiveName();                            // gets author of the message as a String
        String channelWhereMessageHappend = e.getChannel().getAsMention();         // gets the channel where the message was sent as a String
        String channelDebugging = e.getChannel().getName();                        // gets the channel where the message was sent as a String for debugging
        String msgText = e.getMessage().getContentRaw();                           // gets the raw textual structure of the message as a String
        String[] wordsInMsg = msgText.split(" ");                            // splits the textual structure of the message with spaces in a String[]
        ArrayList<String> wordsInMsgWithoutSpaces = new ArrayList<>();             // stores the String[] after deleting the spaces in an ArrayList<String>

        for (String s : wordsInMsg) {
            if (s.isEmpty()) {
                continue;
            }
            wordsInMsgWithoutSpaces.add(s);
        }
        Message.suppressContentIntentWarning();                                    // Suppresses GatewayIntent.MESSAGE_CONTENT warning

        System.out.println("\n" + "Message found by user: " + user + "\n"
                        + "In channel: " + channelDebugging + "\n"
                        + "Message value: " + wordsInMsgWithoutSpaces.size());

        if (wordsInMsgWithoutSpaces.size() >= 35) {
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F1FE")).queue();         // adds Y as Reaction
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F1E6")).queue();         // adds A as Reaction
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F1F5")).queue();         // adds P as Reaction
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F17F U+FE0F")).queue();  // add  P (parking symbol) as Reaction
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F1EA")).queue();         // adds E as Reaction
            e.getMessage().addReaction(Emoji.fromUnicode("U+1F1F7")).queue();         // adds R as Reaction
            e.getChannel().sendMessage("What is bro wafflin about").queue();
        }

    }
}


