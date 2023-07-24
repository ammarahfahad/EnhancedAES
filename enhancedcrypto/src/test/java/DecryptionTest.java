import com.mycompany.enhancedcrypto.EnhancedAES;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DecryptionTest {
    public static void main(String[] args) {
        // Prepare the plaintext and key
        String plaintext = "Hello, World!";
        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] key = "ThisIsASecretKey".getBytes(StandardCharsets.UTF_8);

        try {
            // Encrypt the plaintext
            byte[] ciphertext = EnhancedAES.encrypt(plaintextBytes, key);
            System.out.println("Ciphertext: " + Arrays.toString(ciphertext));

            // Decrypt the ciphertext
            byte[] decryptedText = EnhancedAES.decrypt(ciphertext, key);
            String decryptedString = new String(decryptedText, StandardCharsets.UTF_8);
            System.out.println("Decrypted Text: " + decryptedString);

            // Verify if the decryption is successful
            boolean isDecryptionSuccessful = plaintext.equals(decryptedString);
            System.out.println("Decryption Successful: " + isDecryptionSuccessful);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
