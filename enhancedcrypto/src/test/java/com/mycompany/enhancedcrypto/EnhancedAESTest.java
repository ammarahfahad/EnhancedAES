/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.enhancedcrypto;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author ammar
 */
public class EnhancedAESTest {
    
    public EnhancedAESTest() {
    }

    /**
     * Test of encrypt method, of class EnhancedAES.
     */
    @Test
    public void testEncrypt() throws Exception {
        System.out.println("encrypt");
        byte[] plaintext = null;
        byte[] key = null;
        byte[] expResult = null;
        byte[] result = EnhancedAES.encrypt(plaintext, key);
        assertArrayEquals(expResult, result);
         byte[] ciphertext = EnhancedAES.encrypt(plaintext, key);
            System.out.println("Ciphertext: " + Arrays.toString(ciphertext));

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decrypt method, of class EnhancedAES.
     */
    @Test
    public void testDecrypt() throws Exception {
        System.out.println("decrypt");
        byte[] ciphertext = null;
        byte[] key = null;
        byte[] expResult = null;
        byte[] result = EnhancedAES.decrypt(ciphertext, key);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
