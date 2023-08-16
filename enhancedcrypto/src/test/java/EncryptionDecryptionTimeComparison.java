import com.mycompany.enhancedcrypto.EnhancedAES;
import com.mycompany.enhancedcrypto.OriginalAES;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class EncryptionDecryptionTimeComparison {
    public static void main(String[] args) {
        byte[] plaintext = "My JAVA CODE ".getBytes(StandardCharsets.UTF_8);
        //byte[] plaintext = generateRandomData(1024 * 1024); // 1 MB of data
        byte[] key = generateRandomKey();

        long originalEncryptionTime = measureEncryptionTimeOriginal(plaintext, key);
        long enhancedEncryptionTime = measureEncryptionTimeEnhanced(plaintext, key);
        double encryptionTimeImprovement = calculateTimeImprovement(originalEncryptionTime, enhancedEncryptionTime);

        long originalDecryptionTime = measureDecryptionTimeOriginal(plaintext, key);
        long enhancedDecryptionTime = measureDecryptionTimeEnhanced(plaintext, key);
        double decryptionTimeImprovement = calculateTimeImprovement(originalDecryptionTime, enhancedDecryptionTime);
        
        
        System.out.println("Encryption time for Original AES : " + originalEncryptionTime + " nanoseconds" );
        System.out.println("Encryption time for Enhanced AES : " + enhancedEncryptionTime + " nanoseconds" );
        System.out.println("Decryption time for Original AES : " + originalDecryptionTime + " nanoseconds" );
        System.out.println("Decryption time for Enhanced AES : " + enhancedDecryptionTime + " nanoseconds" );
        System.out.println("Encryption Time Improvement: " + encryptionTimeImprovement + "%");
        System.out.println("Decryption Time Improvement: " + decryptionTimeImprovement + "%");
    }

    private static byte[] generateRandomData(int size) {
        byte[] data = new byte[size];
        new Random().nextBytes(data);
        return data;
    }

    private static byte[] generateRandomKey() {
        byte[] key = new byte[32]; // 256-bit key
        new Random().nextBytes(key);
        return key;
    }

    private static long measureEncryptionTimeOriginal(byte[] plaintext, byte[] key) {
        long startTime = System.nanoTime();

        try {
            byte[] ciphertext = OriginalAES.encrypt(plaintext, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureEncryptionTimeEnhanced(byte[] plaintext, byte[] key) {
        long startTime = System.nanoTime();

        try {
            byte[] ciphertext = EnhancedAES.encrypt(plaintext, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureDecryptionTimeOriginal(byte[] ciphertext, byte[] key) {
        long startTime = System.nanoTime();

        try {
            byte[] decrypted = OriginalAES.decrypt(ciphertext, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static long measureDecryptionTimeEnhanced(byte[] ciphertext, byte[] key) {
        long startTime = System.nanoTime();

        try {
            byte[] decrypted = EnhancedAES.decrypt(ciphertext, key);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private static double calculateTimeImprovement(long originalTime, long enhancedTime) {
        return (1 - (double) enhancedTime / originalTime) * 100;
    }
}
