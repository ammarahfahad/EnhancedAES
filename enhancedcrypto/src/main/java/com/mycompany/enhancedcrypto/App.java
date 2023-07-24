package com.mycompany.enhancedcrypto;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App extends Application {

    private static Scene scene;

    @FXML
    private TextArea plaintextArea;

    @FXML
    private TextArea ciphertextArea;

    @FXML
    private TextArea keyArea;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("app"), 400, 300);
        stage.setScene(scene);
        stage.show();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setController(new AppController());
        return fxmlLoader.load();
    }

    public void encrypt() {
        String plaintext = plaintextArea.getText();
        String key = keyArea.getText();

        // Perform encryption using EnhancedAES.encrypt() method
        try {
            byte[] ciphertext = EnhancedAES.encrypt(plaintext.getBytes(), key.getBytes());
            ciphertextArea.setText(new String(ciphertext));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    public void decrypt() {
        String ciphertext = ciphertextArea.getText();
        String key = keyArea.getText();

        // Perform decryption using EnhancedAES.decrypt() method
        try {
            byte[] plaintext = EnhancedAES.decrypt(ciphertext.getBytes(), key.getBytes());
            plaintextArea.setText(new String(plaintext));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }
}
