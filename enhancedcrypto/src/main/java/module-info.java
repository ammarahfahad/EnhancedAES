module com.mycompany.encryptedcrypto {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.enhancedcrypto to javafx.fxml;
    exports com.mycompany.enhancedcrypto;
}
