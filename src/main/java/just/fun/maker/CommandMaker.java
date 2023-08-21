package just.fun.maker;

import just.fun.domain.usecase.Command;

public interface CommandMaker<IT> {
    Command make(IT input);
}
