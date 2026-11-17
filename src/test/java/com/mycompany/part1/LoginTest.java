/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1; 
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author muren
 */
public class LoginTest {

    Login login = new Login();

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserNameIncorrectlyFormatted() {
        assertFalse(login.checkUserName("kyle!!!!!!"));
    }

    @Test
    public void testCheckPasswordSuccess() {
        assertTrue(login.checkPasswordComplexity("Chse&1971"));
    }

    @Test
    public void testCheckPasswordFailure() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumberSuccess() {
        assertTrue(login.checkCellPhoneNumber("+27831234567"));
    }
    
    @Test
    public void testCheckInternationalCellNumberSuccess() {
        assertTrue(login.checkCellPhoneNumber("+27718693002"));
    }

    @Test
    public void testCheckCellPhoneNumberFailure() {
        assertFalse(login.checkCellPhoneNumber("0831234567"));
    }

    @Test
    public void testLoginUserSuccess() {
        login.registerUser("kyl_1", "Chse&1971", "+27831234567");
        assertTrue(login.loginUser("kyl_1", "Chse&1971"));
    }

    @Test
    public void testLoginUserFailure() {
        login.registerUser("kyl_1", "Chse&1971", "+27831234567");
        assertFalse(login.loginUser("wrong_user", "wrong_pass"));
    }
}