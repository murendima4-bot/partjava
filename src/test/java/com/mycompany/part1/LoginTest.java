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

    // Instantiate your Login object
    Login login = new Login();

    // -------------------------------------------------------------
    // USERNAME TESTS
    // -------------------------------------------------------------

    @Test
    public void testCheckUserNameCorrectlyFormatted() {
        // Test a username with an underscore and <= 5 characters
        boolean actual = login.checkUserName("kyl_1");
        assertTrue(actual);
    }

    @Test
    public void testCheckUserNameIncorrectlyFormatted() {
        // Test a username missing an underscore or > 5 characters
        boolean actual = login.checkUserName("kyle!!!!!!");
        assertFalse(actual);
    }

    // -------------------------------------------------------------
    // PASSWORD TESTS
    // -------------------------------------------------------------

    @Test
    public void testCheckPasswordSuccess() {
        // Test a password meeting all complexity rules
        boolean actual = login.checkPasswordComplexity("Chse&1971");
        assertTrue(actual);
    }

    @Test
    public void testCheckPasswordFailure() {
        // Test a password missing required complexity (e.g., no special char or number)
        boolean actual = login.checkPasswordComplexity("password");
        assertFalse(actual);
    }

    // -------------------------------------------------------------
    // CELL PHONE TESTS
    // -------------------------------------------------------------

    @Test
    public void testCheckCellPhoneNumberSuccess() {
        // Test a valid international cell phone number
        boolean actual = login.checkCellPhoneNumber("+27831234567");
        assertTrue(actual);
    }

    @Test
    public void testCheckCellPhoneNumberFailure() {
        // Test an invalid cell phone number
        boolean actual = login.checkCellPhoneNumber("0831234567");
        assertFalse(actual);
    }

    // -------------------------------------------------------------
    // LOGIN VERIFICATION TESTS
    // -------------------------------------------------------------

    @Test
    public void testLoginUserSuccess() {
        // Register credentials first
        login.registerUser("kyl_1", "Chse&1971", "+27831234567");
        
        // Test correct login inputs
        boolean actual = login.loginUser("kyl_1", "Chse&1971");
        assertTrue(actual);
    }

    @Test
    public void testLoginUserFailure() {
        // Register credentials first
        login.registerUser("kyl_1", "Chse&1971", "+27831234567");
        
        // Test incorrect login inputs
        boolean actual = login.loginUser("wrong_user", "wrong_pass");
        assertFalse(actual);
    }
}
