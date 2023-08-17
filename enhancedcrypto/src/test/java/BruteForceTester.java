import com.mycompany.enhancedcrypto.EnhancedAES;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;

public class BruteForceTester {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Sample plaintext to encrypt
        byte[] plaintext = "This is a test message".getBytes();

        // Original ciphertext to compare with
        byte[] originalCiphertext = EnhancedAES.encrypt(plaintext, getOriginalKey());

        // Generate all possible keys (2^32 combinations)
 byte[] key = new byte[16];
long totalKeys = (1L << 32); // 2^32 combinations
long progressInterval = totalKeys / 100; // Print progress for every 1% completion

for (long i = 0; i < totalKeys; i++) {
    // Convert long value to byte array representing the key
    for (int j = 0; j < 16; j++) {
        key[j] = (byte) ((i >> (j * 8)) & 0xFF);
    }
            byte[] bruteForceCiphertext = EnhancedAES.encrypt(plaintext, key);

            // Compare brute-force ciphertext with original ciphertext
            if (MessageDigest.isEqual(originalCiphertext, bruteForceCiphertext)) {
                System.out.println("Brute-force key found: " + byteArrayToHexString(key));
                break;
            }

            // Print progress for every 1% completion
            if (i % progressInterval == 0) {
                int percentage = (int) ((i * 100) / totalKeys);
                System.out.println("Progress: " + percentage + "%");
            }
        }
    }

    // Implement this method to get the original key used for encryption
    private static byte[] getOriginalKey() {
        byte[] originalKey = new byte[] {
            (byte) 0x61, (byte) 0x62, (byte) 0x63, (byte) 0x64,
            (byte) 0x65, (byte) 0x66, (byte) 0x67, (byte) 0x68,
            (byte) 0x69, (byte) 0x6A, (byte) 0x6B, (byte) 0x6C,
            (byte) 0x6D, (byte) 0x6E, (byte) 0x6F, (byte) 0x70
        };
        return originalKey;
    }

    // Utility method to convert byte array to hexadecimal string
    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

