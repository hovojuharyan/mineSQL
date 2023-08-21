package just.fun.serialization;

public interface Serializer<T extends SerialContent> {
    void serialize(T content);
}
