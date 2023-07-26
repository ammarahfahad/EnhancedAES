import com.mycompany.enhancedcrypto.EnhancedAES;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;


import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SideChannelAttack {

    private static final int NUM_BYTES_KEY = 16; // Assuming AES-128 (16 bytes key)
    private static final int MAX_BYTE_VALUE = 256; // Maximum byte value (0 to 255)

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidKeyException, InvalidKeyException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException, IllegalBlockSizeException {
        byte[] key = new byte[NUM_BYTES_KEY];
        byte[] recoveredKey = new byte[NUM_BYTES_KEY];

        for (int i = 0; i < NUM_BYTES_KEY; i++) {
            long[] times = new long[MAX_BYTE_VALUE];
            for (int guess = 0; guess < MAX_BYTE_VALUE; guess++) {
                key[i] = (byte) guess;
                // Perform encryption multiple times to get more accurate timings
                for (int k = 0; k < 100; k++) {
                    long startTime = System.nanoTime();
                    try {
                        EnhancedAES.encrypt(new byte[16], key); // Perform encryption (can be modified based on method parameters)
                    } catch (IllegalBlockSizeException ex) {
                        ex.printStackTrace();
                    } catch (BadPaddingException ex) {
                        ex.printStackTrace();
                    } catch (InvalidAlgorithmParameterException ex) {
                        ex.printStackTrace();
                    }
                    long endTime = System.nanoTime();
                    times[guess] += endTime - startTime;
                }
            }

            // Find the byte value that took the longest time (most likely the correct value)
            long maxTime = 0;
            for (int guess = 0; guess < MAX_BYTE_VALUE; guess++) {
                if (times[guess] > maxTime) {
                    maxTime = times[guess];
                    recoveredKey[i] = (byte) guess;
                }
            }
        }

        // Check if the recovered key matches the original key
        System.out.println("Recovered Key: " + Arrays.toString(recoveredKey));
        System.out.println("Original Key: " + Arrays.toString(key));
        System.out.println("Keys Match: " + Arrays.equals(key, recoveredKey));
    }
}