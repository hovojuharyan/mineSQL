package just.fun.domain.usecase;

import just.fun.serialization.Dropper;

public class DropTable {
    String tableName;
    Dropper dropper;

    public void run() {
        dropper.drop(tableName);
    }
}
