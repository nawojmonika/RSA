package sample;

import Algorithm.Decryption;
import Algorithm.Encryption;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringEscapeUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;


public class ProgramWindow {
    public TextArea textToEncrypt;
    public TextArea textToDecrypt;
    byte[] messageToDecrypt = null;

    Encryption encryption = new Encryption();
    String encryptedSuffix = ".encrypt";
    String decryptedSuffix = ".decrypted";
    public void handleEncrypt(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file to encrypt");
        File choosedFile = fileChooser.showOpenDialog(null);
        try {
            byte[] data = Files.readAllBytes(choosedFile.toPath());
            byte[] encryptedData = encryption.encrypt(data, 64);
            try (FileOutputStream stream = new FileOutputStream(choosedFile.getAbsolutePath() + encryptedSuffix)) {
                stream.write(encryptedData);
                stream.close();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error while loading file");
            alert.showAndWait();
        }
    }

    public void handleDecrypt(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open file to encrypt");
        File choosedFile = fileChooser.showOpenDialog(null);
        try {
            byte[] data = Files.readAllBytes(choosedFile.toPath());
            Decryption decryption = new Decryption(encryption.getD(), encryption.getPublicKey());
            byte[] decryptedData = decryption.decrypt(data, 64);

            try (FileOutputStream stream = new FileOutputStream(choosedFile.getAbsolutePath().replace(".encrypt", decryptedSuffix))) {
                stream.write(decryptedData);
                stream.close();
            }
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error while loading file");
            alert.showAndWait();
        }
    }

    public void handleEncryptFromWindow(ActionEvent actionEvent) {
        byte[] messageToEncrypt = this.textToEncrypt.getText().getBytes();
        messageToDecrypt = this.encryption.encrypt(messageToEncrypt, 64);
        this.textToDecrypt.setText(new String(messageToDecrypt));
    }

    public void handleDecryptFromWindow(ActionEvent actionEvent) {
        Decryption decryption = new Decryption(encryption.getD(), encryption.getPublicKey());
        byte[] messageToEncrypt = decryption.decrypt(messageToDecrypt, 64);
        this.textToEncrypt.setText(new String(messageToEncrypt, Charset.defaultCharset()));
    }
}
