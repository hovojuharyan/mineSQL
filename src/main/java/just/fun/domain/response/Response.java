package just.fun.domain.response;

import just.fun.domain.schema.Data;

import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Response {
    Status status;
    String message;

    private Response(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public static Response ok(String message) {
        return new Response(Status.OK, message);
    }

    public static Response fetched(Data data) {
        return new Response(Status.OK, data.toString());
    }

    public static Response error(String message, Throwable throwable) {
        StringBuilder sb = new StringBuilder(message).append("\n");
        String stacktrace = Arrays.stream(throwable.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
        sb.append(stacktrace);
        return new Response(Status.ERROR, sb.toString());
    }

    @Override
    public String toString() {
        return status + "\n" + message;
    }
}
