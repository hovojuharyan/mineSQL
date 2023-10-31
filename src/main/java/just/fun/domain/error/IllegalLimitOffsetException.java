package just.fun.domain.error;

public class IllegalLimitOffsetException extends RuntimeException{
    public IllegalLimitOffsetException(int limit, int offset) {
        super("Out Of Bounds --- limit: " + limit + ", offset: " + offset);
    }
}
