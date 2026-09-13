/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1;
import java.util.Scanner;
/**
 *
 * @author muren
 */
public class Part1{

    public static void main(String[] args) {
         Scanner scanner = new Scanner(System.in);
         
         System.out.println("Please enter your name:");
         String firstName = scanner.nextLine();
         System.out.println("Please enter your Surname:");
         String lastName = scanner.nextLine();
         
         System.out.println("Please enter your username:");
         String userName = scanner.nextLine();
         
          System.out.println("Please enter your password:");
         String userPassword = scanner.nextLine();
         
          System.out.println("Please enter your cell phone number:");
         String userCellNumber = scanner.nextLine();
         
         
           Login user = new Login();
          String result = user.registerUser(userName, userPassword, userCellNumber);
           System.out.println(result);
    
         
           //   LOGIN SECTION 


System.out.println("Please enter your username to log in:");
String loginUsername = scanner.nextLine();

System.out.println("Please enter your password to log in:");
String loginPassword = scanner.nextLine();

boolean loginResult = user.loginUser(loginUsername, loginPassword);

String finalMessage = user.returnLoginStatus(loginResult);

System.out.println(finalMessage);

         
         
         
         
         
         
         
         
         
    }
    
    
    
    
    
    
    
    
}
