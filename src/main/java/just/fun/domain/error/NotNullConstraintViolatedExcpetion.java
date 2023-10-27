package just.fun.domain.error;

public class NotNullConstraintViolatedExcpetion extends RuntimeException {

    public NotNullConstraintViolatedExcpetion(String columnName) {
        super("Attempting to put null into a not-null column: " + columnName);
    }
}
