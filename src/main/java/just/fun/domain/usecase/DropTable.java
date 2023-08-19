package just.fun.domain.usecase;

import just.fun.serialization.Dropper;

public class DropTable {
    private final String tableName;
    private final Dropper dropper;

    public DropTable(String tableName, Dropper dropper) {
        this.tableName = tableName;
        this.dropper = dropper;
    }

    public void run() {
        dropper.drop(tableName);
    }
}
