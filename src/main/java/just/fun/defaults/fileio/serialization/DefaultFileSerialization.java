package just.fun.defaults.fileio.serialization;

public final class DefaultFileSerialization {

    static final String ROOT_PATH = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";

    private DefaultFileSerialization() {
    }

    public static FileDataSerializer dataSerializer(String tableName) {
        return new FileDataSerializer(tableName, metadataSerializer(tableName));
    }


    public static FileMetadataSerializer metadataSerializer(String tableName) {
        return new FileMetadataSerializer(tableName);
    }


    public static FileRowSerializer rowSerializer(String tableName) {
        return new FileRowSerializer(tableName);
    }
}
