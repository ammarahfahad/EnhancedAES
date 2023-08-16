package com.mycompany.enhancedcrypto;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class OriginalAES {
    private static final String AES_ALGORITHM = "AES";
    private static final String ENCRYPTION_MODE = "AES/CBC/PKCS5Padding"; // Use CBC mode with PKCS5 padding
    private static final int KEY_SIZE = 256;

    public static byte[] encrypt(byte[] plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
        byte[] iv = new byte[16]; // Generate or provide a secure IV here
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        byte[] ciphertext = cipher.doFinal(plaintext);
        return ciphertext;
    }

    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);

        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
        byte[] iv = new byte[16]; // Use the same IV used during encryption
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);

        byte[] decrypted = cipher.doFinal(ciphertext);
        return decrypted;
    }
}
// commented to inlcude iv (report encryption decryption times with this code
//  public static byte[] encrypt(byte[] plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//        byte[] ciphertext = cipher.doFinal(plaintext);
//        return ciphertext;
//    }
//
//    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//        byte[] decrypted = cipher.doFinal(ciphertext);
//        return decrypted;
//    }
//}





//commented to check the modified code all previous results with this code ///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.enhancedcrypto;
//
///**
// *
// * @author ntu-user
// */
//import javax.crypto.*;
//import javax.crypto.spec.*;
//import java.security.*;
//
//public class OriginalAES {
//    private static final String AES_ALGORITHM = "AES";
//    private static final String ENCRYPTION_MODE = "AES/ECB/PKCS5Padding";
//    private static final int KEY_SIZE = 256;
//
//    public static byte[] encrypt(byte[] plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
//        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
//
//        byte[] ciphertext = cipher.doFinal(plaintext);
//        return ciphertext;
//    }
//
//    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
//        SecretKeySpec secretKey = new SecretKeySpec(key, AES_ALGORITHM);
//
//        Cipher cipher = Cipher.getInstance(ENCRYPTION_MODE);
//        cipher.init(Cipher.DECRYPT_MODE, secretKey);
//
//        byte[] decrypted = cipher.doFinal(ciphertext);
//        return decrypted;
//    }
//}
//
