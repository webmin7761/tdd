package geektime.tdd.args;

public class InsufficientArgumentsException extends RuntimeException {
    private String option;

    public InsufficientArgumentsException(String value) {
        this.option = value;
    }

    public String getOption() {
        return option;
    }
}
