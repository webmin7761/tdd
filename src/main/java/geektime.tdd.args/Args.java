package geektime.tdd.args;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Args {
    public static <T> T parse(Class<T> optionsClass, String... args) {
        try {
            List<String> arguments = Arrays.asList(args);

            Constructor<?> constructor = optionsClass.getDeclaredConstructors()[0];
            Parameter parameter = constructor.getParameters()[0];

            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> {
                try {
                    return parseOption(arguments, it);
                } catch (TooManyArgumentsException e) {
                    throw new RuntimeException(e);
                }
            }).toArray();

            return (T) constructor.newInstance(values);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Object parseOption(List<String> arguments, Parameter parameter) throws TooManyArgumentsException {
        Option option = parameter.getAnnotation(Option.class);

        return PARSERS.get(parameter.getType()).parse(arguments, option);
    }

    private static Map<Class<?>, OptionParser> PARSERS = Map.of(boolean.class, new BooleanOptionParser(),
            int.class, new SingleValuedOptionParser<>(Integer::parseInt),
            String.class, new SingleValuedOptionParser<>(String::valueOf));

}
