package just.fun.serial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateFileSerializer implements Serializer {

    private final static String ROOT_DIR = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";
    private final Path path;

    public CreateFileSerializer(String tableName) {
        path = Path.of(ROOT_DIR + "tbl_metadata_" + tableName + ".txt");
    }

    @Override
    public void serialize(SerialContent content) {
        String textualContent = content.getTextualContent();
        try {
            Files.writeString(path, textualContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
