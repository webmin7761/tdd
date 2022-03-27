package geektime.tdd.args;

import java.util.List;
import java.util.function.Function;

class SingleValuedOptionParser<T> implements OptionParser<T> {
    T defaultValue;
    Function<String, T> valueParse;

    public SingleValuedOptionParser(T defaultValue, Function<String, T> valueParse) {
        this.defaultValue = defaultValue;
        this.valueParse = valueParse;
    }

    @Override
    public T parse(List<String> arguments, Option option) throws TooManyArgumentsException {
        int index = arguments.indexOf("-" + option.value());

        if (index == -1) {
            return defaultValue;
        }

        if (index + 1 == arguments.size()
                || arguments.get(index + 1).startsWith("-")) {
            throw new InsufficientArgumentsException(option.value());
        }
        if (index + 2 < arguments.size()
                && !arguments.get(index + 2).startsWith("-")) {
            throw new TooManyArgumentsException(option.value());
        }
        return valueParse.apply(arguments.get(index + 1));
    }
}
