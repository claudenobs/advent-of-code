package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.AocProcessor.Part;
import ch.stormwind.aoc.AocProcessor.Type;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.stream.Stream;

import static ch.stormwind.aoc.AocProcessor.Part.PART1;
import static ch.stormwind.aoc.AocProcessor.Part.PART2;
import static ch.stormwind.aoc.AocProcessor.Type.EXAMPLE;
import static ch.stormwind.aoc.AocProcessor.Type.INPUT;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class AocTest {

    public static class ParamsProvider implements ArgumentsProvider {
        @Override
        public Stream<Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(arguments(PART1, EXAMPLE), arguments(PART1, INPUT), arguments(PART2, EXAMPLE), arguments(PART2, INPUT));
        }
    }

    @ParameterizedTest
    @ArgumentsSource(ParamsProvider.class)
    void aoc_2023_01(Part part, Type type) {
        assertTrue(new Aoc2301().run(part, type));
    }

    @ParameterizedTest
    @ArgumentsSource(ParamsProvider.class)
    void aoc_2023_02(Part part, Type type) {
        assertTrue(new Aoc2302().run(part, type));
    }
}
