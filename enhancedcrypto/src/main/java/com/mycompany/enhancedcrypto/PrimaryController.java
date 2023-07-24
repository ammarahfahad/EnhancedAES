package com.mycompany.enhancedcrypto;

import com.mycompany.enhancedcrypto.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
