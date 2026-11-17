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
        assertTrue(msg.checkMessageLength("Hi Mike, can you join us for dinner tonight?"));
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "a".repeat(251);
        assertFalse(msg.checkMessageLength(longMessage));
    }

    @Test
    public void testRecipientCellSuccess() {
        assertTrue(msg.checkRecipientCell("+2771869300"));
    }

    @Test
    public void testRecipientCellFailure() {
        assertFalse(msg.checkRecipientCell("0831234567"));
    }

    @Test
    public void testCreateMessageIDFormat() {
        String id = msg.createMessageID(0);
        assertNotNull(id);
        assertTrue(id.length() <= 10);
    }

    @Test
    public void testCreateMessageHash() {
        String id = "ID0000";
        int num = 0;
        String text = "Did you get the cake?";
        String expected = "ID:0:DIDCAKE";
        String actual = msg.createMessageHash(id, num, text);
        assertEquals(expected, actual);
    }

    @Test
    public void testSentMessageOption1() {
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testSentMessageOption2() {
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testSentMessageOption3() {
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }
}