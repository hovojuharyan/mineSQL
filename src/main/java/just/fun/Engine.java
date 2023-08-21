package just.fun;

import just.fun.defaults.fileio.Defaults;
import just.fun.domain.response.Response;
import just.fun.domain.usecase.Command;
import just.fun.maker.CommandMaker;

public class Engine<IT> {

    private final CommandMaker<IT> commandMaker;

    private Engine(CommandMaker<IT> commandMaker) {
        this.commandMaker = commandMaker;
    }

    public Response execute(IT inputCommand) {
        Command command = commandMaker.make(inputCommand);
        return command.run();
    }

    public static <IT> Engine<IT> of(CommandMaker<IT> commandMaker) {
        return new Engine<>(commandMaker);
    }

    public static Engine<String> withDefault() {
        return new Engine<>(Defaults.commandParser());
    }
}
