package just.fun.domain.schema;

import just.fun.serialization.SerialContent;

import java.util.List;

public class Data implements SerialContent<Data> {

    List<Row> rows;

    @Override
    public String serialForm() {
        return null;
    }

    @Override
    public Data pojoForm() {
        return null;
    }
}
