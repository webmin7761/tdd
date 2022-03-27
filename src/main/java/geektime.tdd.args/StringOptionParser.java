package geektime.tdd.args;

import java.util.List;

class StringOptionParser implements OptionParser {

    @Override
    public Object parse(List<String> arguments, Option option) {
        int index = arguments.indexOf("-" + option.value());
        return String.valueOf(arguments.get(index + 1));
    }
}
