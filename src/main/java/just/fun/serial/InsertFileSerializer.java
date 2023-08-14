package just.fun.serial;

import java.io.FileWriter;
import java.io.IOException;

public class InsertFileSerializer implements Serializer {
    private final static String ROOT_DIR = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";
    private final String fileName;

    public InsertFileSerializer(String tableName) {
        fileName = ROOT_DIR + "tbl_data_" + tableName + ".txt";
    }

    @Override
    public void serialize(SerialContent content) {
        try (FileWriter fileWriter = new FileWriter(fileName, true)) {
            fileWriter.append(content.getTextualContent()).append("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
