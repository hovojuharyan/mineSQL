package just.fun;

public enum ColumnType {
    INT {
        @Override
        public boolean isValidForValue(String value) {
            try {
                Integer.parseInt(value);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    },
    STRING {
        @Override
        public boolean isValidForValue(String value) {
            return true;
        }
    },
    BOOL {
        @Override
        public boolean isValidForValue(String value) {
            return "TRUE".equalsIgnoreCase(value) || "FALSE".equalsIgnoreCase(value);
        }
    },
    NULL {
        @Override
        public boolean isValidForValue(String value) {
            return "NULL".equalsIgnoreCase(value);
        }
    };
    
    public abstract boolean isValidForValue(String value);
}
