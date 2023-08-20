package just.fun.command;

import just.fun.serialization.Dropper;

import java.util.Objects;

public final class DropTableCommand {
    private String tableName;
    private Dropper dropper;

    private DropTableCommand() {

    }

    public String tableName() {
        return tableName;
    }

    public Dropper dropper() {
        return dropper;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (DropTableCommand) obj;
        return Objects.equals(this.tableName, that.tableName) &&
                Objects.equals(this.dropper, that.dropper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tableName, dropper);
    }

    @Override
    public String toString() {
        return "DropTableCommand[" +
                "tableName=" + tableName + ", " +
                "dropper=" + dropper + ']';
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder implements CommandBuilder<DropTableCommand> {
        private final DropTableCommand command;

        private Builder() {
            this.command = new DropTableCommand();
        }

        public Builder tableName(String tableName) {
            command.tableName = tableName;
            return this;
        }

        public Builder dropper(Dropper dropper) {
            command.dropper = dropper;
            return this;
        }

        @Override
        public DropTableCommand build() {
            return command;
        }
    }
}
