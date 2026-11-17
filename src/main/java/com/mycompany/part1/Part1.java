/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1;
import java.util.Scanner;
/**
 *
 * @author muren
 */
public class Part1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login user = new Login();
        Message msgObj = new Message();

        System.out.println("Please enter your name:");
        user.firstName = scanner.nextLine();
        System.out.println("Please enter your surname:");
        user.lastName = scanner.nextLine();
        System.out.println("Please enter your username:");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password:");
        String userPassword = scanner.nextLine();
        System.out.println("Please enter your cell phone number:");
        String userCellNumber = scanner.nextLine();

        String registrationResult = user.registerUser(userName, userPassword, userCellNumber);
        System.out.println(registrationResult);

        boolean loginSuccess = false;
        
        System.out.println("\n--- LOGIN SECTION ---");
        while (!loginSuccess) {
            System.out.println("Please enter your username to log in:");
            String loginUsername = scanner.nextLine();
            System.out.println("Please enter your password to log in:");
            String loginPassword = scanner.nextLine();

            loginSuccess = user.loginUser(loginUsername, loginPassword);
            String finalMessage = user.returnLoginStatus(loginSuccess);
            System.out.println(finalMessage);
            
            if (!loginSuccess) {
                System.out.println("Please try logging in again.\n");
            }
        }

        int maxCapacity = 100;
        String[] sentMessages = new String[maxCapacity];
        String[] disregardedMessages = new String[maxCapacity];
        String[] storedMessages = new String[maxCapacity];
        String[] messageHashes = new String[maxCapacity];
        String[] messageIDs = new String[maxCapacity];
        String[] recipients = new String[maxCapacity];
        
        int sentCount = 0;
        int disregardedCount = 0;
        int storedCount = 0;
        int totalGlobalIndex = 0;

        int choice = 0;
        do {
            System.out.println("\nPlease choose an option from the menu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.println("4) Stored Messages");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.next();
                System.out.println("Invalid input, please enter a number between 1 and 4.");
                continue;
            }

            if (choice == 1) {
                System.out.println("Please enter how many messages you wish to enter:");
                int numMessages = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");

                    System.out.println("Please enter recipient cell number (max 15 chars, starting with international code like +...):");
                    String recipient = scanner.nextLine();
                    
                    while (!msgObj.checkRecipientCell(recipient)) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again:");
                        recipient = scanner.nextLine();
                    }
                    System.out.println("Cell phone number successfully captured.");

                    System.out.println("Please enter your message (not exceeding 250 characters):");
                    String messageText = scanner.nextLine();

                    while (!msgObj.checkMessageLength(messageText)) {
                        int excess = messageText.length() - 250;
                        System.out.println("Please enter a message of less than 250 characters. (Exceeds by " + excess + ")");
                        messageText = scanner.nextLine();
                    }

                    String generatedID = msgObj.createMessageID(totalGlobalIndex);
                    String generatedHash = msgObj.createMessageHash(generatedID, i, messageText);

                    System.out.println("Choose action:");
                    System.out.println("1 - Send Message");
                    System.out.println("2 - Disregard Message (Press 0 to delete)");
                    System.out.println("3 - Store Message to send later");
                    
                    int actionOption = scanner.nextInt();
                    scanner.nextLine();

                    if (actionOption == 1) {
                        sentMessages[sentCount] = messageText;
                        messageHashes[totalGlobalIndex] = generatedHash;
                        messageIDs[totalGlobalIndex] = generatedID;
                        recipients[totalGlobalIndex] = recipient;
                        sentCount++;
                        totalGlobalIndex++;
                        System.out.println("Message successfully sent.");
                        System.out.println("\n--- Message Details Summary ---");
                        System.out.println(msgObj.printMessages(generatedID, generatedHash, recipient, messageText));
                    } else if (actionOption == 2) {
                        if (scanner.hasNextInt()) {
                            int delCheck = scanner.nextInt();
                            scanner.nextLine();
                            if (delCheck == 0) {
                                disregardedMessages[disregardedCount] = messageText;
                                disregardedCount++;
                                System.out.println("Message successfully deleted.");
                            }
                        } else {
                            scanner.next();
                        }
                    } else if (actionOption == 3) {
                        storedMessages[storedCount] = messageText;
                        messageHashes[totalGlobalIndex] = generatedHash;
                        messageIDs[totalGlobalIndex] = generatedID;
                        recipients[totalGlobalIndex] = recipient;
                        storedCount++;
                        totalGlobalIndex++;
                        System.out.println("Message successfully stored.");
                        
                        msgObj.saveMessagesToJson(sentMessages, disregardedMessages, storedMessages, messageHashes, messageIDs, "stored_messages.json");
                    } else {
                        System.out.println("Invalid option selected.");
                    }
                }

                System.out.println("\nTotal number of messages processed: " + msgObj.returnTotalMessages(sentCount + storedCount));

            } else if (choice == 2) {
                System.out.println("\n--- Recently Sent Messages ---");
                if (sentCount == 0) {
                    System.out.println("No messages sent yet.");
                } else {
                    for (int k = 0; k < sentCount; k++) {
                        System.out.println((k + 1) + ". " + sentMessages[k]);
                    }
                }
            } else if (choice == 3) {
                System.out.println("Exiting application. Goodbye!");
            } else if (choice == 4) {
                boolean subRunning = true;
                while (subRunning) {
                    System.out.println("\n=== Stored Messages Task Menu ===");
                    System.out.println("a) Display sender and recipient of all stored messages");
                    System.out.println("b) Display the longest stored message");
                    System.out.println("c) Search for a message ID and display corresponding recipient and message");
                    System.out.println("d) Search for all messages stored for a particular recipient");
                    System.out.println("e) Delete a message using the message hash");
                    System.out.println("f) Display a report listing full details of all stored messages");
                    System.out.println("g) Return to Main Menu");
                    
                    String subChoice = scanner.nextLine().trim().toLowerCase();
                    
                    switch (subChoice) {
                        case "a":
                            System.out.println("\n--- Sender & Recipient Report ---");
                            if (storedCount == 0 && sentCount == 0) {
                                System.out.println("No stored messages available.");
                            } else {
                                for (int i = 0; i < totalGlobalIndex; i++) {
                                    if (recipients[i] != null) {
                                        System.out.println("Sender: Developer (" + user.userCellNumber + ") | Recipient: " + recipients[i]);
                                    }
                                }
                            }
                            break;
                            
                        case "b":
                            System.out.println("\n--- Longest Stored Message ---");
                            String longest = "";
                            for (int i = 0; i < totalGlobalIndex; i++) {
                                if (storedMessages[i] != null && storedMessages[i].length() > longest.length()) {
                                    longest = storedMessages[i];
                                }
                                if (sentMessages[i] != null && sentMessages[i].length() > longest.length()) {
                                    longest = sentMessages[i];
                                }
                            }
                            if (longest.isEmpty()) {
                                System.out.println("No messages found.");
                            } else {
                                System.out.println("Longest Message: " + longest);
                            }
                            break;
                            
                        case "c":
                            System.out.println("Enter Message ID to search:");
                            String searchId = scanner.nextLine().trim();
                            boolean foundId = false;
                            for (int i = 0; i < totalGlobalIndex; i++) {
                                if (messageIDs[i] != null && messageIDs[i].equalsIgnoreCase(searchId)) {
                                    System.out.println("Recipient: " + recipients[i] + " | Message: " + (sentMessages[i] != null ? sentMessages[i] : storedMessages[i]));
                                    foundId = true;
                                    break;
                                }
                            }
                            if (!foundId) {
                                System.out.println("Message ID not found.");
                            }
                            break;
                            
                        case "d":
                            System.out.println("Enter recipient number to search:");
                            String searchRecipient = scanner.nextLine().trim();
                            boolean foundRec = false;
                            for (int i = 0; i < totalGlobalIndex; i++) {
                                if (recipients[i] != null && recipients[i].equals(searchRecipient)) {
                                    System.out.println("Found Message: " + (sentMessages[i] != null ? sentMessages[i] : storedMessages[i]));
                                    foundRec = true;
                                }
                            }
                            if (!foundRec) {
                                System.out.println("No messages found for recipient " + searchRecipient);
                            }
                            break;
                            
                        case "e":
                            System.out.println("Enter Message Hash to delete:");
                            String searchHash = scanner.nextLine().trim();
                            boolean deletedHash = false;
                            for (int i = 0; i < totalGlobalIndex; i++) {
                                if (messageHashes[i] != null && messageHashes[i].equalsIgnoreCase(searchHash)) {
                                    String msgText = sentMessages[i] != null ? sentMessages[i] : storedMessages[i];
                                    sentMessages[i] = null;
                                    storedMessages[i] = null;
                                    messageHashes[i] = null;
                                    messageIDs[i] = null;
                                    recipients[i] = null;
                                    deletedHash = true;
                                    System.out.println("Message: \"" + msgText + "\" successfully deleted.");
                                    break;
                                }
                            }
                            if (!deletedHash) {
                                System.out.println("Message hash not found.");
                            }
                            break;
                            
                        case "f":
                            System.out.println("\n--- Full Stored Message Report ---");
                            String jsonContent = msgObj.readJsonFileToString("stored_messages.json");
                            System.out.println(jsonContent);
                            break;
                            
                        case "g":
                            subRunning = false;
                            break;
                            
                        default:
                            System.out.println("Invalid sub-menu option.");
                    }
                }
            } else {
                System.out.println("Invalid choice.");
            }

        } while (choice != 3);

        scanner.close();
    }
}