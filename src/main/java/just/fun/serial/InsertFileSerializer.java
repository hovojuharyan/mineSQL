package just.fun.serial;

import java.io.FileWriter;
import java.io.IOException;

public class InsertFileSerializer implements Serializer {
    private final static String ROOT_DIR = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";
    private final FileWriter fileWriter;

    public InsertFileSerializer(String tableName) {
        try {
            String fileName = ROOT_DIR + "tbl_data_" + tableName + ".txt";
            fileWriter = new FileWriter(fileName, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void serialize(SerialContent content) {
        try (fileWriter) {
            fileWriter.append(content.getTextualContent()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
