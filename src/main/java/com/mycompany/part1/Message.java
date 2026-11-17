/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;
/**
 *
 * @author muren
 */
public class Message {

    public boolean checkMessageID(String messageID) {
        if (messageID != null && messageID.length() <= 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkRecipientCell(String recipient) {
        if (recipient != null && recipient.startsWith("+") && recipient.length() <= 15) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMessageLength(String message) {
        if (message != null && message.length() <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public String createMessageID(int messageCounter) {
        String baseNum = String.format("%04d", messageCounter);
        String idText = "ID" + baseNum;
        if (idText.length() > 10) {
            idText = idText.substring(0, 10);
        }
        return idText;
    }

    public String createMessageHash(String messageID, int messageNumber, String message) {
        String twoDigitsID = messageID.length() >= 2 ? messageID.substring(0, 2) : messageID;
        String cleanedMessage = message.replaceAll("[^a-zA-Z0-9\\s]", "");
        String[] words = cleanedMessage.trim().split("\\s+");
        
        String firstWord = (words.length > 0 && !words[0].isEmpty()) ? words[0] : "";
        String lastWord = (words.length > 1 && !words[words.length - 1].isEmpty()) ? words[words.length - 1] : firstWord;
        
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

    public void saveMessagesToJson(String[] sentMsgs, String[] dispMsgs, String[] storedMsgs, String[] hashes, String[] ids, String filename) {
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < storedMsgs.length; i++) {
            if (storedMsgs[i] != null) {
                JSONObject obj = new JSONObject();
                obj.put("messageID", ids[i] != null ? ids[i] : "");
                obj.put("messageHash", hashes[i] != null ? hashes[i] : "");
                obj.put("message", storedMsgs[i]);
                jsonArray.put(obj);
            }
        }
        try (FileWriter file = new FileWriter(filename)) {
            file.write(jsonArray.toString(4));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readJsonFileToString(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            return "[]";
        }
    }
}
