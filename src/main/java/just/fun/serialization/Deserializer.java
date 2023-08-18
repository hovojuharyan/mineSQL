package just.fun.serialization;

public interface Deserializer<T extends SerialContent<T>> {
    SerialContent<T> deserialize();
}
