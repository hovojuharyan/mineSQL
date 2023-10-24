package just.fun.domain.response;

public enum Status {
    OK(200, "SUCCESS!!!"), ERROR(500, "ERROR!!!");

    private final int code;
    private final String msg;

    Status(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return code + " " + msg;
    }
}
