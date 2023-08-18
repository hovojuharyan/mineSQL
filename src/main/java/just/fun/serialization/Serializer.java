package just.fun.serialization;

public interface Serializer<T extends SerialContent<T>> {
    void serialize(SerialContent<T> content);
}
