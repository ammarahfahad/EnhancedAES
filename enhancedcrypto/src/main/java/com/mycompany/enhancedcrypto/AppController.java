/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.enhancedcrypto;

/**
 *
 * @author ntu-user
 */
import javafx.fxml.FXML;

public class AppController {

    @FXML
    private App app;

    @FXML
    public void encryptButtonClicked() {
        app.encrypt();
    }

    @FXML
    public void decryptButtonClicked() {
        app.decrypt();
    }
}
