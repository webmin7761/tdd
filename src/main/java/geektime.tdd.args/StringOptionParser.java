package geektime.tdd.args;

import java.util.List;

class StringOptionParser extends IntegerOptionParser {

    @Override
    protected Object parseValue(List<String> arguments, int index) {
        return String.valueOf(arguments.get(index + 1));
    }
}
