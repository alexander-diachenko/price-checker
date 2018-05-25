package checker.controller;

import checker.Main;
import checker.component.Alert;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import checker.util.AppProperty;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * @author Alexander Diachenko.
 */
public class MenuController implements Initializable {

    private final static Logger logger = Logger.getLogger(MenuController.class);

    @FXML
    private MenuBar menu;
    @FXML
    private Menu languages;
    @FXML
    private Menu styles;
    private ResourceBundle bundle;
    private Properties properties;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        init(resources);
    }

    public void languageEnAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("language", "en");
            reload(getStage());
        }
    }

    public void languageRuAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("language", "ru");
            reload(getStage());
        }
    }

    public void languageUaAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("language", "ua");
            reload(getStage());
        }
    }

    public void styleDefaultAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("style", "light");
            reload(getStage());
        }
    }

    public void styleDarkAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("style", "dark");
            reload(getStage());
        }
    }

    public void styleBlueAction() {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        if (result.get() == ButtonType.OK) {
            setProperty("style", "blue");
            reload(getStage());
        }
    }

    private void init(ResourceBundle bundle) {
        this.bundle = bundle;
        this.properties = AppProperty.getProperty();
        disableCurrentLanguage();
        disableCurrentStyle();
    }

    private void disableCurrentStyle() {
        String style = properties.getProperty("style");
        ObservableList<MenuItem> items = styles.getItems();
        for (MenuItem item : items) {
            String id = item.getId();
            if (style.equals(id)) {
                item.setDisable(true);
                break;
            }
        }
    }

    private void disableCurrentLanguage() {
        String language = properties.getProperty("language");
        ObservableList<MenuItem> items = languages.getItems();
        for (MenuItem item : items) {
            String id = item.getId();
            if(language.equals(id)) {
                item.setDisable(true);
                break;
            }
        }
    }

    private Stage getStage() {
        return (Stage) menu.getScene().getWindow();
    }

    private void setProperty(String key, String value) {
        properties.setProperty(key, value);
        AppProperty.setProperties(properties);
    }

    private void reload(Stage primaryStage) {
        primaryStage.close();
        Platform.runLater(() -> {
            try {
                new Main().start(new Stage());
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
                e.printStackTrace();
            }
        });
    }
}
