package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.AocProcessor;
import ch.stormwind.aoc.AocProcessor.Part;
import ch.stormwind.aoc.AocProcessor.Type;
import ch.stormwind.aoc.BaseAocProcessor;
import ch.stormwind.aoc.ResourceReader;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ClassInfo;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.EnumSet;
import java.util.stream.Stream;

import static ch.stormwind.aoc.AnsiEscapeCode.GREEN;
import static ch.stormwind.aoc.AnsiEscapeCode.RED;
import static ch.stormwind.aoc.AnsiEscapeCode.RESET;
import static ch.stormwind.aoc.AnsiEscapeCode.YELLOW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * The most stupid unit-test ever!
 */
class AocTest {

    public static class ParamsProvider implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            var packageNames = "ch.stormwind.aoc.y2023";
            try (var sr = new ClassGraph().acceptPackages(packageNames).enableClassInfo().scan()) {
                var ps = sr.getSubclasses(BaseAocProcessor.class).stream().map(ClassInfo::loadClass).toList();
                var types = EnumSet.allOf(AocProcessor.Type.class);
                var parts = EnumSet.allOf(AocProcessor.Part.class);
                return ps.stream().flatMap(c -> parts.stream().flatMap(p -> types.stream().map(t -> arguments(c, p, t))));
            }
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParamsProvider.class)
        // given
    void aoc_2023(Class<AocProcessor<?>> clazz, Part part, Type type) throws Throwable {
        var processor = clazz.getDeclaredConstructor().newInstance();
        var expected = new ResourceReader().readSolution(processor.id(), part, type);
        // when
        var result = String.valueOf(processor.process(part, type));
        // then
        var color = expected.map(s -> s.equals(result) ? GREEN : RED).orElse(YELLOW);
        System.out.println(color + result + RESET);
        assertEquals(GREEN, color);
    }
}
