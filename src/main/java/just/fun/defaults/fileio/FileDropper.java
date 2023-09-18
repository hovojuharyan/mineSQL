package just.fun.defaults.fileio;

import just.fun.serialization.Dropper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static just.fun.defaults.fileio.Defaults.ROOT_PATH;

public class FileDropper implements Dropper {
    @Override
    public void drop(String tableName) {
        try {
            Files.delete(Path.of(ROOT_PATH + "tbl_metadata_" + tableName + ".txt"));
            Files.deleteIfExists(Path.of(ROOT_PATH + "tbl_data_" + tableName + ".txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
