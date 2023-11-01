package just.fun.domain.response;

public class Response {
    private final Status status;
    private final String message;
    private final Throwable throwable;

    Response(Status status, String message, Throwable throwable) {
        this.status = status;
        this.message = message;
        this.throwable = throwable;
    }

    public static Response ok(String message) {
        return new Response(Status.OK, message, null);
    }

    public static Response error(Throwable throwable) {
        return new Response(Status.ERROR, throwable.getMessage(), throwable);
    }

    public Status getStatus() {
        return status;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    @Override
    public String toString() {
        return status + "\n" + message;
    }
}
