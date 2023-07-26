import com.mycompany.enhancedcrypto.EnhancedAES;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LargeDatasetCreator {

    public static void main(String[] args) {
        int datasetSize = 10000; // Change this value to set the size of the dataset

        // Create a large dataset of random text input
        String[] dataset = new String[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            dataset[i] = generateRandomText();
        }

        // Encrypt the dataset using Enhanced AES and convert to binary
        StringBuilder binaryData = new StringBuilder();
        try {
            SecretKeySpec secretKey = generateRandomKey();
    for (String plaintext : dataset) {
        byte[] ciphertext = EnhancedAES.encrypt(plaintext.getBytes(), secretKey.getEncoded());
        String ciphertextHex = bytesToHex(ciphertext);
        String ciphertextBinary = hexToBinary(ciphertextHex);
        binaryData.append(ciphertextBinary).append("\n");
            }
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        

        // Write the binary data to a file
        String outputFileName = "encrypted_dataset.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            writer.write(binaryData.toString());
            System.out.println("Encrypted dataset has been written to " + outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static SecretKeySpec generateRandomKey() throws NoSuchAlgorithmException {
        byte[] keyBytes = new byte[16]; // 128-bit key
        new SecureRandom().nextBytes(keyBytes);
        return new SecretKeySpec(keyBytes, "AES");
    }
    private static String bytesToHex(byte[] bytes) {
    StringBuilder hexStringBuilder = new StringBuilder();
    for (byte b : bytes) {
        String hex = Integer.toHexString(0xFF & b);
        if (hex.length() == 1) {
            hexStringBuilder.append('0');
        }
        hexStringBuilder.append(hex);
    }
    return hexStringBuilder.toString();
}
    private static String generateRandomText() {
        int textLength = 100; // Change this value to set the length of the random text
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomText = new StringBuilder();
        for (int i = 0; i < textLength; i++) {
            int randomIndex = new SecureRandom().nextInt(characters.length());
            randomText.append(characters.charAt(randomIndex));
        }
        return randomText.toString();
    }

  private static String hexToBinary(String hexString) {
    StringBuilder binaryStringBuilder = new StringBuilder();
    for (char c : hexString.toCharArray()) {
        int decimalValue = Character.digit(c, 16);
        String binary = Integer.toBinaryString(decimalValue);
        int numLeadingZeros = 4 - binary.length();
        for (int i = 0; i < numLeadingZeros; i++) {
            binaryStringBuilder.append("0");
        }
        binaryStringBuilder.append(binary);
    }
    return binaryStringBuilder.toString();
}

}
