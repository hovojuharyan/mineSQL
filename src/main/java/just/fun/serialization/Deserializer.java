package just.fun.serialization;

public interface Deserializer<T> {
    T deserialize(String tableName);
}
