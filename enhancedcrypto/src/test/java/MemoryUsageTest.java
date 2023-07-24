/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ntu-user
 */
import com.mycompany.enhancedcrypto.EnhancedAES;
import com.mycompany.enhancedcrypto.OriginalAES;
import java.security.SecureRandom;

public class MemoryUsageTest {
    public static void main(String[] args) {
        try {
            byte[] plaintext = generateRandomData(1024 * 1024); // 1MB plaintext
            byte[] key = generateRandomKey();

            long beforeMemoryOriginal = getMemoryUsedByOriginalAES(plaintext, key);
            long beforeMemoryEnhanced = getMemoryUsedByEnhancedAES(plaintext, key);

            System.out.println("Memory Usage - Original AES: " + beforeMemoryOriginal + " bytes");
            System.out.println("Memory Usage - Enhanced AES: " + beforeMemoryEnhanced + " bytes");

            long memoryImprovement = beforeMemoryOriginal - beforeMemoryEnhanced;
            double improvementPercentage = (double) memoryImprovement / beforeMemoryOriginal * 100;

            System.out.println("Memory Usage Improvement: " + improvementPercentage + "%");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static long getMemoryUsedByOriginalAES(byte[] plaintext, byte[] key) throws Exception {
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        byte[] ciphertext = OriginalAES.encrypt(plaintext, key);

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsed = afterMemory - beforeMemory;

        return memoryUsed;
    }

    private static long getMemoryUsedByEnhancedAES(byte[] plaintext, byte[] key) throws Exception {
        long beforeMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        byte[] ciphertext = EnhancedAES.encrypt(plaintext, key);

        long afterMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long memoryUsed = afterMemory - beforeMemory;

        return memoryUsed;
    }

    private static byte[] generateRandomData(int size) {
        byte[] data = new byte[size];
        new SecureRandom().nextBytes(data);
        return data;
    }

    private static byte[] generateRandomKey() {
        byte[] key = new byte[32]; // 256-bit key
        new SecureRandom().nextBytes(key);
        return key;
    }
}
