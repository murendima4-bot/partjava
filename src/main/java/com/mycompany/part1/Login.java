/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1;

/**
 *
 * @author muren
 */
public class Login {
    public String userName;
    public String userPassword;
    public String userCellNumber;
    public String firstName;
    public String lastName;
    
    public boolean  checkUserName(String userName){
        if (userName.contains("_") && userName.length() <=5) {
            return true;
        } else {
            return false;
        }
    }
    
    public boolean checkPasswordComplexity( String userPassword) {
        if (userPassword != null && userPassword.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")) {

            return true;
    } else {
            return false;
            }
    }
    
    public boolean checkCellPhoneNumber(String userCellNumber) {  
     
        if (userCellNumber.matches("^\\+\\d+") && userCellNumber.length() <= 13) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public String registerUser(String userName, String userPassword, String userCellNumber) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userCellNumber = userCellNumber;
        
        if (checkUserName(userName) == false) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        if (checkPasswordComplexity(userPassword) == false) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
       if (checkCellPhoneNumber(userCellNumber) == false) {
            return "Cell number is not correctly formatted or does not contain an international code,please correct the number and try again.";
        }
        
        return "Username successfully captured";

        
            
    
    }    
    
    public boolean loginUser(String enteredUsername, String enteredPassword) {
    
    // Compares the entered inputs against your userName and userPassword variables
    if (enteredUsername.equals(userName) && enteredPassword.equals(userPassword)) {
        return true;
    } else {
        return false;
    }
    
}

    


    public String returnLoginStatus(boolean isLoggedIn) {
    
    if (isLoggedIn == true) {
        return "Welcome " +  firstName + ", " + lastName + "it is great to see you again";
    } else {
        return "Username or password incorrect";
    }
    }
    }