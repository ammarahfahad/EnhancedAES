/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enhancedcrypto;

/**
 *
 * @author ntu-user
 */
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class EnhancedAESApp extends Application {

    private TextArea plaintextArea;
    private TextArea ciphertextArea;
    private TextArea keyArea;
    private TextArea decryptedArea;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Enhanced AES Encryption/Decryption");

        // Create UI components
        Label plaintextLabel = new Label("Plaintext:");
        Label ciphertextLabel = new Label("Ciphertext:");
        Label keyLabel = new Label("Key:");
        Label decryptedLabel = new Label("Decrypted:");
        plaintextArea = new TextArea();
        ciphertextArea = new TextArea();
        keyArea = new TextArea();
        decryptedArea = new TextArea();

        Button encryptButton = new Button("Encrypt");
        encryptButton.setOnAction(e -> encrypt());

        Button decryptButton = new Button("Decrypt");
        decryptButton.setOnAction(e -> {
            try {
                decrypt();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (NoSuchPaddingException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (IllegalBlockSizeException ex) {
                ex.printStackTrace();
            } catch (BadPaddingException ex) {
                ex.printStackTrace();
            } catch (InvalidAlgorithmParameterException ex) {
                ex.printStackTrace();
            }
        });

        // Set the layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        gridPane.add(plaintextLabel, 0, 0);
        gridPane.add(plaintextArea, 1, 0, 2, 1);
        gridPane.add(ciphertextLabel, 0, 1);
        gridPane.add(ciphertextArea, 1, 1, 2, 1);
        gridPane.add(keyLabel, 0, 2);
        gridPane.add(keyArea, 1, 2, 2, 1);
        gridPane.add(decryptedLabel, 0, 3);
        gridPane.add(decryptedArea, 1, 3, 2, 1);

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(encryptButton, decryptButton);
        gridPane.add(buttonBox, 0, 4, 4, 1);

        // Create a root container
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(gridPane);

        // Create a scene
        Scene scene = new Scene(root, 400, 300);

        // Set the scene on the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void encrypt() {
        String plaintext = plaintextArea.getText();
        String key = keyArea.getText();

        // Perform encryption using EnhancedAES.encrypt() method
        try {
            byte[] ciphertext = EnhancedAES.encrypt(plaintext.getBytes(), key.getBytes());
            ciphertextArea.setText(new String(ciphertext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//decryption
private void decrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
     String plaintext = plaintextArea.getText();
    String key = keyArea.getText();
    byte[] ciphertext = EnhancedAES.encrypt(plaintext.getBytes(), key.getBytes());

    try {
        // Retrieve the ciphertext from the EnhancedAES.decrypt() method
        byte[] decryptedBytes = EnhancedAES.decrypt(ciphertext, key.getBytes());
      String decryptedtext = new String(decryptedBytes);

        // Set the plaintext in the plaintextArea
        decryptedArea.setText(decryptedtext);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}





