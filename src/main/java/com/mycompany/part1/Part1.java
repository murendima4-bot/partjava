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

        // --- REGISTRATION SECTION ---
        System.out.println("Please enter your name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your surname:");
        String lastName = scanner.nextLine();
        System.out.println("Please enter your username:");
        String userName = scanner.nextLine();
        System.out.println("Please enter your password:");
        String userPassword = scanner.nextLine();
        System.out.println("Please enter your cell phone number:");
        String userCellNumber = scanner.nextLine();

        String registrationResult = user.registerUser(userName, userPassword, userCellNumber);
        System.out.println(registrationResult);

        // --- LOGIN SECTION ---
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

        // --- PART 2: QUICKCHAT MENU & MESSAGING ---
        System.out.println("\nWelcome to QuickChat.");

        int choice = 0;
        do {
            System.out.println("\nPlease choose an option from the menu:");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages – Coming Soon.");
            System.out.println("3) Quit");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                scanner.next(); // clear invalid input
                System.out.println("Invalid input, please enter a number between 1 and 3.");
                continue;
            }

            if (choice == 1) {
                System.out.println("Please enter how many messages you wish to enter:");
                int numMessages = scanner.nextInt();
                scanner.nextLine();

                int sentCount = 0;

                for (int i = 0; i < numMessages; i++) {
                    System.out.println("\n--- Entering Message " + (i + 1) + " of " + numMessages + " ---");

                    // Recipient cell number validation
                    System.out.println("Please enter recipient cell number (max 10 chars, starting with international code like +...):");
                    String recipient = scanner.nextLine();
                    
                    while (!msgObj.checkRecipientCell(recipient)) {
                        System.out.println("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again:");
                        recipient = scanner.nextLine();
                    }
                    System.out.println("Cell phone number successfully captured.");

                    // Message text length validation
                    System.out.println("Please enter your message (not exceeding 250 characters):");
                    String messageText = scanner.nextLine();

                    while (!msgObj.checkMessageLength(messageText)) {
                        int excess = messageText.length() - 250;
                        System.out.println("Please enter a message of less than 250 characters. (Exceeds by " + excess + ")");
                        messageText = scanner.nextLine();
                    }
                    System.out.println("Message sent");

                    // ID and Hash generation
                    String generatedID = msgObj.createMessageID();
                    String generatedHash = msgObj.createMessageHash(generatedID, i, messageText);

                    // Action choice
                    System.out.println("Choose action:");
                    System.out.println("1 - Send Message");
                    System.out.println("2 - Disregard Message (Press 0 to delete)");
                    System.out.println("3 - Store Message to send later");
                    
                    int actionOption = scanner.nextInt();
                    scanner.nextLine();

                    String statusResult = msgObj.sentMessage(actionOption);
                    System.out.println(statusResult);

                    if (actionOption == 1 || actionOption == 3) {
                        sentCount++;
                    }

                    // Display final details
                    System.out.println("\n--- Message Details Summary ---");
                    System.out.println(msgObj.printMessages(generatedID, generatedHash, recipient, messageText));
                }

                System.out.println("\nTotal number of messages processed: " + msgObj.returnTotalMessages(sentCount));

            } else if (choice == 2) {
                System.out.println("Coming Soon.");
            } else if (choice == 3) {
                System.out.println("Exiting application. Goodbye!");
            } else {
                System.out.println("Invalid choice, please select 1, 2, or 3.");
            }

        } while (choice != 3);

        scanner.close();
    }
}