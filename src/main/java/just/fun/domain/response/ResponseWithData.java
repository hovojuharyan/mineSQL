package just.fun.domain.response;

import just.fun.domain.schema.Data;

public class ResponseWithData extends Response {

    private final Data data;

    ResponseWithData(Status status, String message, Throwable throwable, Data data) {
        super(status, message, throwable);
        this.data = data;
    }

    public static ResponseWithData fetched(Data data) {
        return new ResponseWithData(Status.OK, data.toString(), null, data);
    }

    public Data fetchedData() {
        return data;
    }
}
