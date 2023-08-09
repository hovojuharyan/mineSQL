package just.fun;

public class Result {
    private final Status status;
    private final String message;

    Result(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "[" + status.getCode() + "] " + message;
    }

    public static Result ok(String message) {
        return new Result(Status.OK, message);
    }

    public static Result error(String message) {
        return new Result(Status.ERROR, message);
    }

}
