package geektime.tdd.args;

import geektime.tdd.args.exceptions.TooManyArgumentsException;

import java.util.List;

class BooleanOptionParser implements OptionParser<Boolean> {

    @Override
    public Boolean parse(List<String> arguments, Option option) throws TooManyArgumentsException {
        return SingleValuedOptionParser.values(arguments, option, 0)
                .map(it -> true).orElse(false);
    }
}
