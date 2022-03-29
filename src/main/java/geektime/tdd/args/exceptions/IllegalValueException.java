package geektime.tdd.args.exceptions;

public class IllegalValueException extends RuntimeException {
    String option;
    String value;

    public IllegalValueException(String option, String value) {
        this.option = option;
        this.value = value;
    }
}
