import com.mycompany.enhancedcrypto.EnhancedAES;
import com.mycompany.enhancedcrypto.OriginalAES;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import java.util.Arrays;

public class AESComparisonTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Test data
        //byte[] plaintext = "£$$%%^$^£$^£^£".getBytes();
         byte[] plaintext = generateRandomData(1024 * 1024);
         byte[] key = generateRandomKey();

        // Encrypt with EnhancedAES
        byte[] enhancedCiphertext = EnhancedAES.encrypt(plaintext, key);

        // Encrypt with OriginalAES
        byte[] originalCiphertext = OriginalAES.encrypt(plaintext, key);

        // Calculate avalanche effect
        double enhancedAvalancheEffect = calculateAvalancheEffect(plaintext, enhancedCiphertext);
        double originalAvalancheEffect = calculateAvalancheEffect(plaintext, originalCiphertext);

        // Calculate Shannon's entropy index
        double enhancedEntropyIndex = calculateEntropyIndex(enhancedCiphertext);
        double originalEntropyIndex = calculateEntropyIndex(originalCiphertext);

        // Print the results
        System.out.println("Avalanche Effect:");
        System.out.println("EnhancedAES: " + enhancedAvalancheEffect + "%");
        System.out.println("OriginalAES: " + originalAvalancheEffect + "%");
        System.out.println();
        System.out.println("Shannon's Entropy Index:");
        System.out.println("EnhancedAES: " + enhancedEntropyIndex + "%");
        System.out.println("OriginalAES: " + originalEntropyIndex + "%");
    }

    private static byte[] generateRandomKey() throws NoSuchAlgorithmException {
       // byte[] key = new byte[32];
        // Generate a random key
        // Replace this with your own key generation logic
        // For demonstration purposes, a random key is used here
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] key = secretKey.getEncoded();
        return key;
    }
private static byte[] generateRandomData(int size) {
        byte[] data = new byte[size];
        new SecureRandom().nextBytes(data);
        return data;
    }
    private static double calculateAvalancheEffect(byte[] plaintext, byte[] ciphertext) {
        int count = plaintext.length * 8;
        int differentBits = 0;

        for (int i = 0; i < plaintext.length; i++) {
            byte p = plaintext[i];
            byte c = ciphertext[i];

            for (int j = 0; j < 8; j++) {
                if (((p >> j) & 1) != ((c >> j) & 1)) {
                    differentBits++;
                }
            }
        }

        return (differentBits * 100.0) / count;
    }

    private static double calculateEntropyIndex(byte[] data) {
        int[] frequencies = new int[256];
        int totalCount = data.length;

        for (byte b : data) {
            frequencies[b & 0xFF]++;
        }

        double entropy = 0.0;
        for (int frequency : frequencies) {
            if (frequency != 0) {
                double probability = (double) frequency / totalCount;
                entropy -= probability * (Math.log(probability) / Math.log(2));
            }
        }

        double maxEntropy = Math.log(256) / Math.log(2);
        return (entropy / maxEntropy) * 100;
    }
}
