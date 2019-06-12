package checker.model.magazine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * @author Alexander Diachenko
 */
public class DocumentCreator {

    private String url = "";

    public Document createDocumentFromFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        try {
            Document document = Jsoup.parse(file, "UTF-8", url);
            return document;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
