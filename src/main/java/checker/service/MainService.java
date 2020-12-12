package checker.service;

import com.epam.pricecheckercore.model.CheckerInput;
import com.epam.pricecheckercore.model.CheckerOutput;
import com.epam.pricecheckercore.service.Checker;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author Alexander Diachenko.
 */
public class MainService extends Service<Void> {

    private final Checker checker;
    private final File file;
    private final Integer urlColumn;
    private final Integer insertColumn;
    private final String savedFilePath;

    public MainService(Checker checker, File file, Integer urlColumn, Integer insertColumn, String savedFilePath) {
        this.checker = checker;
        this.file = file;
        this.urlColumn = urlColumn;
        this.insertColumn = insertColumn;
        this.savedFilePath = savedFilePath;
    }

    @Override
    protected Task<Void> createTask() {
        return new Task<>() {
            @Override
            protected Void call() throws Exception {
                CheckerInput checkerInput = CheckerInput.builder()
                        .file(FileUtils.readFileToByteArray(file))
                        .insertIndex(insertColumn)
                        .urlIndex(urlColumn)
                        .build();
                CheckerOutput checkerOutput = checker.check(checkerInput);

                File result = new File(savedFilePath);
                FileUtils.writeByteArrayToFile(result, checkerOutput.getFile());
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(128);
                SecretKey key = kgen.generateKey();
                byte[] encoded = key.getEncoded();
                IOUtils.write(encoded, new FileOutputStream(result));
                return null;
            }
        };
    }
}
