package just.fun.domain.usecase;

import just.fun.domain.response.Response;
import just.fun.serialization.Dropper;

public class DropTable implements Command {
    private final String tableName;
    private final Dropper dropper;

    public DropTable(String tableName, Dropper dropper) {
        this.tableName = tableName;
        this.dropper = dropper;
    }

    @Override
    public Response run() {
        dropper.drop(tableName);
        return Response.ok("DROPPED TABLE " + tableName);
    }
}
