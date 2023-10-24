package just.fun.domain.response;

import just.fun.domain.schema.Data;

public class ResponseWithData extends Response {

    private final Data data;

    ResponseWithData(Status status, String message, Data data) {
        super(status, message);
        this.data = data;
    }

    public static ResponseWithData fetched(Data data) {
        return new ResponseWithData(Status.OK, data.toString(), data);
    }

    public Data fetchedData() {
        return data;
    }
}
