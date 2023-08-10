package just.fun;

import just.fun.serial.Dropper;

public class DropOperation implements Operation {

    private final Dropper dropper;
    private final String tableName;

    public DropOperation(Dropper dropper, String tableName) {
        this.dropper = dropper;
        this.tableName = tableName;
    }

    @Override
    public Result operate() {
        try {
            dropper.drop();
            return Result.ok("DROPPED TABLE "  + tableName);
        } catch (Exception e) {
            return Result.error("FAILED TO DROP TABLE " + tableName, e);
        }
    }

    @Override
    public String getTableName() {
        return tableName;
    }
}
