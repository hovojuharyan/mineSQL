package just.fun;

import just.fun.defaults.fileio.Defaults;
import just.fun.domain.response.Response;
import just.fun.domain.usecase.Command;
import just.fun.maker.CommandMaker;

public class MineSQL<IT> {

    private final CommandMaker<IT> commandMaker;

    private MineSQL(CommandMaker<IT> commandMaker) {
        this.commandMaker = commandMaker;
    }

    public Response execute(IT inputCommand) {
        Command command = commandMaker.make(inputCommand);
        return command.run();
    }

    public static <IT> MineSQL<IT> of(CommandMaker<IT> commandMaker) {
        return new MineSQL<>(commandMaker);
    }

    public static MineSQL<String> withDefaults() {
        return new MineSQL<>(Defaults.commandParser());
    }
}
