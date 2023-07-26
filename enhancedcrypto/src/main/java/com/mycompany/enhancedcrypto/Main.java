/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enhancedcrypto;

/**
 *
 * @author ntu-user
 */


import java.nio.charset.StandardCharsets;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Test the enhanced AES encryption and decryption

            // Prepare the plaintext and key
            String plaintext = "Hello, World!";
            byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);

            // Generate a 16-byte AES key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] key = secretKey.getEncoded();

            // Encrypt the plaintext using the enhanced AES algorithm
            byte[] ciphertext = EnhancedAES.encrypt(plaintextBytes, key);
            System.out.println("Ciphertext: " + bytesToHex(ciphertext));

            // Decrypt the ciphertext using the enhanced AES algorithm
            byte[] decryptedText = EnhancedAES.decrypt(ciphertext, key);
            String decryptedString = new String(decryptedText, StandardCharsets.UTF_8);
            System.out.println("Decrypted Text: " + decryptedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to convert bytes to hexadecimal string representation
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}


