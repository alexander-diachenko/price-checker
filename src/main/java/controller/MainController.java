package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * @author Alexander Diachenko.
 */
public class MainController {

    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private Spinner<Integer> urlColumn;
    @FXML
    private Spinner<Integer> insertColumn;
    @FXML
    private GridPane gridPane;
    @FXML
    private Label filePath;
    private File file;

    public void fileAction() {
        FileChooser fileFromChooser = new FileChooser();
        file = fileFromChooser.showOpenDialog(getStage());
        if (file != null) {
            filePath.setText(file.getAbsolutePath());
        } else {
            filePath.setText("");
        }
    }

    private Stage getStage() {
        return (Stage) gridPane.getScene().getWindow();
    }

    public void checkAction() {
        progressIndicator.setProgress(-1);
        MainService service = new MainService(file, urlColumn, insertColumn, progressIndicator);
        service.restart();
        service.setOnSucceeded(event -> setComplete());
        service.setOnFailed(event -> setFailed(service.getException()));
    }

    private void setComplete() {
        progressIndicator.visibleProperty().unbind();
        progressIndicator.setProgress(1);
    }

    private void setFailed(Throwable exception) {
        exception.printStackTrace();
    }
}
