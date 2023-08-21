package just.fun.defaults.fileio;

public final class Defaults {

    static final String ROOT_PATH = "C:\\Users\\hovo\\IdeaProjects\\mineSQL\\dataf\\";

    private Defaults() {
    }

    public static FileDataSerializer dataSerializer() {
        return new FileDataSerializer(metadataSerializer());
    }


    public static FileMetadataSerializer metadataSerializer() {
        return new FileMetadataSerializer();
    }

    public static FileRowSerializer rowSerializer() {
        return new FileRowSerializer();
    }

    public static FileDropper dropper() {
        return new FileDropper();
    }

    public static ConsoleCommandMaker commandParser() {
        return new ConsoleCommandMaker(metadataSerializer(), dataSerializer(), rowSerializer(), dropper());
    }
}
