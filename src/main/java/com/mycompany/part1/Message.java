/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;
import java.util.Random;
/**
 *
 * @author muren
 */
public class Message {

    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private int messageNumber;

    public boolean checkMessageID(String messageID) {
        if (messageID != null && messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRecipientCell(String recipient) {
        // Ensures it starts with "+" and is no more than 10 characters total
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMessageLength(String message) {
        // Ensures message does not exceed 250 characters
        if (message != null && message.length() <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public String createMessageID() {
        Random rand = new Random();
        long id = 1000000000L + rand.longs(1, 9000000000L).findFirst().getAsLong();
        return String.valueOf(id);
    }

    public String createMessageHash(String messageID, int messageNumber, String message) {
        String twoDigitsID = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        
        String[] words = message.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        return twoDigitsID + ":" + messageNumber + ":" + (firstWord + lastWord).toUpperCase();
    }

    public String sentMessage(int option) {
        if (option == 1) {
            return "Message successfully sent.";
        } else if (option == 2) {
            return "Press 0 to delete the message.";
        } else if (option == 3) {
            return "Message successfully stored.";
        } else {
            return "Invalid option selected.";
        }
    }

    public String printMessages(String messageID, String messageHash, String recipient, String message) {
        return "Message ID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + message;
    }

    public int returnTotalMessages(int totalSent) {
        return totalSent;
    }
}
