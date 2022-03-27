package geektime.tdd.args;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static geektime.tdd.args.BooleanOptionParserTest.option;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingleValuedOptionParserTest {
    @Test
    public void should_not_accept_extra_argument_for_single_valued_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class, () -> new SingleValuedOptionParser<>(0, Integer::parseInt).parse(asList("-p", "8080", "8081"), option("p")));
        assertEquals("p", e.getOption());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-p -l", "-p"})
    public void should_not_accept_insufficient_argument_for_single_valued_option(String arguments) {
        InsufficientArgumentsException e = assertThrows(InsufficientArgumentsException.class, () -> new SingleValuedOptionParser<>(0, Integer::parseInt).parse(asList(arguments.split(" ")), option("p")));
        assertEquals("p", e.getOption());
    }

    @Test
    public void should_set_default_value_to_0_for_int_option() {
        assertEquals(0, new SingleValuedOptionParser<>(0, Integer::parseInt).parse(asList(""), option("p")));
    }

    @Test
    public void should_not_accept_extra_argument_for_string_single_valued_option() {
        TooManyArgumentsException e = assertThrows(TooManyArgumentsException.class, () ->
                new SingleValuedOptionParser<>("", String::valueOf).parse(asList("-d", "/usr/logs", "/var/logs"), option("d")));
        assertEquals("d", e.getOption());
    }
}
