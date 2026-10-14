/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.part1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    Message msg = new Message();

    @Test
    public void testMessageLengthSuccess() {
        boolean actual = msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertTrue(actual);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(251);
        boolean actual = msg.checkMessageLength(longMessage);
        assertFalse(actual);
    }

    @Test
    public void testRecipientCellSuccess() {
        boolean actual = msg.checkRecipientCell("+27718693");
        assertTrue(actual);
    }

    @Test
    public void testRecipientCellFailure() {
        boolean actual = msg.checkRecipientCell("0831234567");
        assertFalse(actual);
    }

    @Test
    public void testCreateMessageHash() {
        String id = "1234567890";
        int num = 0;
        String text = "Hi thanks";
        String expected = "12:0:HITHANKS";
        String actual = msg.createMessageHash(id, num, text);
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageOption1() {
        String actual = msg.sentMessage(1);
        assertEquals("Message successfully sent.", actual);
    }

    @Test
    public void testSentMessageOption2() {
        String actual = msg.sentMessage(2);
        assertEquals("Press 0 to delete the message.", actual);
    }

    @Test
    public void testSentMessageOption3() {
        String actual = msg.sentMessage(3);
        assertEquals("Message successfully stored.", actual);
    }
}