/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enhancedcrypto;

/**
 *
 * @author ntu-user
 */
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class OriginalAES {
    private static final String AES_ALGORITHM = "AES";
    private static final String ENCRYPTION_MODE = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE = 256;

    public static byte[] encrypt(byte[] plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] ciphertext = cipher.doFinal(plaintext);
        return ciphertext;
    }

    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decrypted = cipher.doFinal(ciphertext);
        return decrypted;
    }
}

