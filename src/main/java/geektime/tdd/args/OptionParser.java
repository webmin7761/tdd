package geektime.tdd.args;

import geektime.tdd.args.exceptions.TooManyArgumentsException;

import java.util.List;

interface OptionParser<T> {
    T parse(List<String> arguments, Option option) throws TooManyArgumentsException;
}
