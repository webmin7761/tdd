package geektime.tdd.args;

import geektime.tdd.args.exceptions.IllegalOptionException;
import geektime.tdd.args.exceptions.TooManyArgumentsException;

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

            Object[] values = Arrays.stream(constructor.getParameters()).map(it -> {
                try {
                    return parseOption(arguments, it);
                } catch (TooManyArgumentsException e) {
                    throw new RuntimeException(e);
                }
            }).toArray();

            return (T) constructor.newInstance(values);
        } catch (IllegalOptionException e) {
            throw e;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Object parseOption(List<String> arguments, Parameter parameter) throws TooManyArgumentsException {
        if (!parameter.isAnnotationPresent(Option.class)) {
            throw new IllegalOptionException(parameter.getName());
        }
        return PARSERS.get(parameter.getType()).parse(arguments, parameter.getAnnotation(Option.class));
    }

    private static Map<Class<?>, OptionParser<?>> PARSERS = Map.of(boolean.class, new BooleanOptionParser(),
            int.class, new SingleValuedOptionParser<>(0, Integer::parseInt),
            String.class, new SingleValuedOptionParser<>("", String::valueOf));

}
