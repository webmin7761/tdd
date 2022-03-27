package geektime.tdd.args;

import java.util.List;
import java.util.function.Function;

class SingleValuedOptionParser<T> implements OptionParser<T> {

    Function<String, T> valueParse;

    public SingleValuedOptionParser(Function<String, T> valueParse) {
        this.valueParse = valueParse;
    }

    @Override
    public T parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        String value = arguments.get(index + 1);
        return valueParse.apply(value);
    }
}
