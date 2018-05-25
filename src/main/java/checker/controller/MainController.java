package checker.controller;

import checker.component.Modal;
import checker.service.MainService;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Spinner;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Alexander Diachenko.
 */
public class MainController implements Initializable {

    private final static Logger logger = Logger.getLogger(MainController.class);

    @FXML
    private Button check;
    @FXML
    private Label saveDirectoryPath;
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
        disableAll(true);
        progressIndicator.setProgress(-1);
        MainService service = new MainService(file.getPath(), urlColumn.getValue(), insertColumn.getValue(), saveDirectoryPath.getText(), progressIndicator);
        service.restart();
        service.setOnSucceeded(event -> setComplete());
        service.setOnFailed(event -> setFailed(service.getException()));
    }

    private void setComplete() {
        disableAll(false);
        progressIndicator.setProgress(1);
    }

    private void setFailed(Throwable exception) {
        logger.error(exception.getMessage(), exception);
        disableAll(false);
        progressIndicator.setProgress(0);
        Modal.openModal(getStage(), exception);
    }

    public void directoryAction() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File saveDirectory = directoryChooser.showDialog(getStage());
        if (saveDirectory != null) {
            saveDirectoryPath.setText(saveDirectory.getAbsolutePath());
        } else {
            saveDirectoryPath.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        check.disableProperty().bind(getBooleanBinding());
    }

    private BooleanBinding getBooleanBinding() {
        return filePath.textProperty().isEmpty()
                .or(saveDirectoryPath.textProperty().isEmpty());
    }

    private void disableAll(boolean value) {
        gridPane.setDisable(value);
    }
}
