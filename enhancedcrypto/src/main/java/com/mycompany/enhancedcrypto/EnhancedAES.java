
package com.mycompany.enhancedcrypto;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class EnhancedAES {

  private static final int NUM_ROUNDS = 4;  // Number of rounds in the SPN construction
    private static final int S_BOX_SIZE = 256; // Size of the S-box

    private static final byte[] SUBSTITUTION_TABLE = createSubstitutionTable();
    private static final byte[] PERMUTATION_TABLE = createPermutationTable(S_BOX_SIZE);

    // Key expansion
    private static SecretKey expandKey(byte[] key) throws NoSuchAlgorithmException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }

    // Substitution operation
    private static void substitute(byte[] input) {
        for (int i = 0; i < input.length; i++) {
            input[i] = SUBSTITUTION_TABLE[input[i] & 0xFF];
        }
    }

    // Permutation operation
    private static void permute(byte[] input) {
        byte[] permuted = new byte[input.length];

        for (int i = 0; i < input.length; i++) {
            permuted[i] = input[PERMUTATION_TABLE[i % PERMUTATION_TABLE.length] & 0xFF];
        }

        System.arraycopy(permuted, 0, input, 0, input.length);
    }

    // Create enhanced substitution table
    private static byte[] createSubstitutionTable() {
        byte[] substitutionTable = new byte[S_BOX_SIZE];

        for (int i = 0; i < S_BOX_SIZE; i++) {
            substitutionTable[i] = (byte) ((i << 4) | (i >> 4));
        }

        return substitutionTable;
    }

    // Create fixed permutation table
    private static byte[] createPermutationTable(int length) {
        byte[] permutationTable = new byte[length];

        for (int i = 0; i < length; i++) {
            permutationTable[i] = (byte) i;
        }

        return permutationTable;
    }

    public static byte[] encrypt(byte[] plaintext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        SecretKey secretKey = expandKey(key);

        // Apply substitution and permutation before encryption
        substitute(plaintext);
        permute(plaintext);

        // Use AES/GCM for encryption
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        byte[] iv = new byte[12]; // 12 bytes IV for GCM
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); // 128-bit authentication tag length
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        byte[] ciphertext = cipher.doFinal(plaintext);

        return ciphertext;
    }
//substitue first and then permute 
    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
    SecretKey secretKey = expandKey(key);

    // Use AES/GCM for decryption
    Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
    byte[] iv = new byte[12]; // 12 bytes IV for GCM
    GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); // 128-bit authentication tag length
    cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);

    // Reverse the order of operations: first substitute, then permute
    byte[] decrypted = cipher.doFinal(ciphertext);
    substitute(decrypted);
    permute(decrypted);

    return decrypted;
}

//=========================================================================================
//    public static byte[] decrypt(byte[] ciphertext, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
//        SecretKey secretKey = expandKey(key);
//
//        // Use AES/GCM for decryption
//        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
//        byte[] iv = new byte[12]; // 12 bytes IV for GCM
//        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, iv); // 128-bit authentication tag length
//        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
//
//        byte[] decrypted = cipher.doFinal(ciphertext);
//
//        // Apply permutation and substitution after decryption
//        permute(decrypted);
//        substitute(decrypted);
//
//        return decrypted;
//    }
}
