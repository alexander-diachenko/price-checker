package checker.service;

import com.epam.pricecheckercore.model.inputoutput.CheckerInput;
import com.epam.pricecheckercore.model.inputoutput.CheckerOutput;
import com.epam.pricecheckercore.service.checker.Checker;
import com.epam.pricecheckercore.service.checker.PriceChecker;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.ProgressIndicator;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author Alexander Diachenko.
 */
public class MainService extends Service<Void> {

    private final String filePath;
    private final Integer urlColumn;
    private final Integer insertColumn;
    private final String savedFilePath;
    private final ProgressIndicator progressIndicator;

    public MainService(String filePath, Integer urlColumn, Integer insertColumn, String savedFilePath, ProgressIndicator progressIndicator) {
        this.filePath = filePath;
        this.urlColumn = urlColumn;
        this.insertColumn = insertColumn;
        this.savedFilePath = savedFilePath;
        this.progressIndicator = progressIndicator;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                Checker checker = new PriceChecker();
                byte[] bytes = Files.readAllBytes(Paths.get(filePath));

                CheckerInput checkerInput = CheckerInput.builder()
                        .file(bytes)
                        .urlIndex(urlColumn - 1)
                        .insertIndex(insertColumn - 1)
                        .build();

                CheckerOutput checkerOutput = checker.check(checkerInput);

                Files.write(Paths.get(savedFilePath), checkerOutput.getFile());
                return null;
            }
        };
    }
}
