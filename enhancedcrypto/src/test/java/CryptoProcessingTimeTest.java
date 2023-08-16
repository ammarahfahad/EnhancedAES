import com.mycompany.enhancedcrypto.EnhancedAES;
import com.mycompany.enhancedcrypto.OriginalAES;


import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.security.InvalidAlgorithmParameterException; // Add this import
import java.util.Arrays;



import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class CryptoProcessingTimeTest {

    public static void main(String[] args) {
        try {
            // Sample data
            byte[] plaintext = new byte[1024]; // 1 KB data
            byte[] key = new byte[32]; // 256-bit key
            byte[] iv = new byte[16];  // 16 bytes IV for CBC

            // Calculate processing time for Original AES
            long startTimeOriginalAES = System.nanoTime();
            byte[] encryptedOriginalAES = OriginalAES.encrypt(plaintext, key);
            byte[] decryptedOriginalAES = OriginalAES.decrypt(encryptedOriginalAES, key);
            long endTimeOriginalAES = System.nanoTime();

            long processingTimeOriginalAES = endTimeOriginalAES - startTimeOriginalAES;

            // Calculate processing time for Enhanced AES
            long startTimeEnhancedAES = System.nanoTime();
            byte[] encryptedEnhancedAES = EnhancedAES.encrypt(plaintext, key);
            byte[] decryptedEnhancedAES = EnhancedAES.decrypt(encryptedEnhancedAES, key);
            long endTimeEnhancedAES = System.nanoTime();

            long processingTimeEnhancedAES = endTimeEnhancedAES - startTimeEnhancedAES;

            // Calculate percentage improvement
            double percentageImprovement = ((double) (processingTimeOriginalAES - processingTimeEnhancedAES) / processingTimeOriginalAES) * 100;

            // Display results
            System.out.println("Original AES Processing Time: " + processingTimeOriginalAES + " nanoseconds");
            System.out.println("Enhanced AES Processing Time: " + processingTimeEnhancedAES + " nanoseconds");
            System.out.println("Percentage Improvement: " + percentageImprovement + "%");

            // Verify correctness
            System.out.println("Original AES Result Matches: " + Arrays.equals(plaintext, decryptedOriginalAES));
            System.out.println("Enhanced AES Result Matches: " + Arrays.equals(plaintext, decryptedEnhancedAES));

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException
                | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}