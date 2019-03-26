package RSA;

import javafx.geometry.HPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class ProgramWindow {
    public static Stage mainStage = null;
    public static Group root = null;
    public static Scene scene = null;
    public static BorderPane borderPane = null;
    public static GridPane inputsGrid = null;

    private static MenuBar mainMenu = null;
    private static Menu openFromFile = null;
    private static MenuItem openEncryptedFile = null;
    private static MenuItem openDecryptedFile = null;
    private static MenuItem saveEncryptedFile = null;
    private static MenuItem saveDecryptedFile = null;

    private static Menu cryptography = null;
    private static MenuItem encrypt = null;
    private static MenuItem decrypt = null;

    private static TextField key = null;

    private static TextArea textToEncryption = null;
    private static TextArea encryptedText = null;


    ProgramWindow(Stage mainStage) {
        this.mainStage = mainStage;
        mainStage.setTitle("DES program");
        root = new Group();
        scene = new Scene(root, 1200, 250);
        borderPane = new BorderPane();

        setupMenu();
        setupInputs();

        mainStage.setScene(scene);
        mainStage.show();
    }

    public void setupInputs() {
        inputsGrid = new GridPane();

        Label labelTextToEncryption = new Label("Text to encryption");
        Label labelEncryptedText = new Label("Encrypted text");
        Label labelKeyInput = new Label("Key input");
        inputsGrid.setHalignment(labelKeyInput, HPos.CENTER);

        key = new TextField();
        textToEncryption = new TextArea();
        encryptedText = new TextArea();


        inputsGrid.add(labelKeyInput, 1, 1);
        inputsGrid.add(key, 2, 1);

        inputsGrid.add(labelTextToEncryption, 1, 2);
        inputsGrid.add(textToEncryption, 1, 3);

        inputsGrid.add(labelEncryptedText, 2, 2);
        inputsGrid.add(encryptedText, 2, 3);

        borderPane.setCenter(inputsGrid);
    }

    public void setupMenu() {
        mainMenu = new MenuBar();
        openFromFile = new Menu("File");
        openEncryptedFile = new MenuItem("Open encrypted");
        openDecryptedFile = new MenuItem("Open decrypted");

        saveEncryptedFile = new MenuItem("Save encrypted");
        saveDecryptedFile = new MenuItem("Save decrypted");

        openEncryptedFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                String wholeFileInText = lines.stream().map(String::toString).collect(Collectors.joining(System.lineSeparator()));
                encryptedText.setText(wholeFileInText);
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(e.toString());
            }
        });

        openDecryptedFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            File selectedFile = fileChooser.showOpenDialog(null);

            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                String wholeFileInText = lines.stream().collect(Collectors.joining(System.lineSeparator()));
                textToEncryption.setText(wholeFileInText);
            } catch (Exception e) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText(e.toString());
            }

        });

        saveEncryptedFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(mainStage);

            if(file != null){
                SaveFile(encryptedText.getText(), file);
            }
        });

        saveDecryptedFile.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(mainStage);

            if(file != null){
                SaveFile(textToEncryption.getText(), file);
            }
        });

        cryptography = new Menu("Cryptography");
        encrypt = new MenuItem("Encrypt") ;
        decrypt = new MenuItem("Decrypt") ;

        encrypt.setOnAction(event -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Tutaj dodać obsługę enkrypcji");
            a.show();
        });

        decrypt.setOnAction(event -> {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Tutaj dodać obsługę dekrypcji");
            a.show();
        });


        cryptography.getItems().addAll(encrypt, decrypt);
        openFromFile.getItems().addAll(openEncryptedFile, openDecryptedFile, saveEncryptedFile, saveDecryptedFile);

        mainMenu.getMenus().addAll(openFromFile, cryptography);

        borderPane.setTop(mainMenu);
        root.getChildren().add(borderPane);
    }

    private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter = null;

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
        }

    }
}
