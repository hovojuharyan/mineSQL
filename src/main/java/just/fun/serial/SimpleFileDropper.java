package just.fun.serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimpleFileDropper implements Dropper {

    private final static String ROOT_DIR = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";
    private final Path path;

    public SimpleFileDropper(String tableName) {
        path = Path.of(ROOT_DIR + "tbl_metadata_" + tableName + ".txt");
    }

    @Override
    public void drop() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
