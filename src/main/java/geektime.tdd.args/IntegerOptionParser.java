package geektime.tdd.args;

import java.util.List;

class IntegerOptionParser implements OptionParser {

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return parseValue(arguments, index);
    }

    protected Object parseValue(List<String> arguments, int index) {
        return Integer.parseInt(arguments.get(index + 1));
    }
}
