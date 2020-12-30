package checker.controller;

import checker.PriceCheckerFX;
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
//import org.apache.log4j.Logger;
import checker.util.AppProperty;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Logger;

/**
 * @author Alexander Diachenko.
 */
public class MenuController implements Initializable {

//    private static final Logger logger = Logger.getLogger(MenuController.class);

    private static final String LANGUAGE = "language";
    private static final String STYLE = "style";

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
        callButtonAction(LANGUAGE, "en");
    }

    public void languageRuAction() {
        callButtonAction(LANGUAGE, "ru");
    }

    public void languageUaAction() {
        callButtonAction(LANGUAGE, "ua");
    }

    public void styleDefaultAction() {
        callButtonAction(STYLE, "light");
    }

    public void styleDarkAction() {
        callButtonAction(STYLE, "dark");
    }

    public void styleBlueAction() {
        callButtonAction(STYLE, "blue");
    }

    private void callButtonAction(String key, String value) {
        Optional<ButtonType> result = Alert.openConfirmation(properties, bundle).showAndWait();
        result.ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                setProperty(key, value);
                reload(getStage());
            }
        });
    }

    private void init(ResourceBundle bundle) {
        this.bundle = bundle;
        this.properties = AppProperty.getProperty();
        disableCurrentLanguage();
        disableCurrentStyle();
    }

    private void disableCurrentStyle() {
        disableMenuItem(styles, properties.getProperty(STYLE));
    }

    private void disableCurrentLanguage() {
        disableMenuItem(languages, properties.getProperty(LANGUAGE));
    }

    private void disableMenuItem(Menu menu, String value) {
        ObservableList<MenuItem> items = menu.getItems();
        for (MenuItem item : items) {
            String id = item.getId();
            if (value.equals(id)) {
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
                new PriceCheckerFX().start(new Stage());
            } catch (IOException e) {
//                logger.error(e.getMessage(), e);
            }
        });
    }
}
