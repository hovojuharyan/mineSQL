package just.fun;

public enum Status {
    OK(0), ERROR(1);

    private final int code;

    Status(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
