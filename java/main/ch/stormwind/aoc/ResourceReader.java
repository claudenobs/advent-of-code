package ch.stormwind.aoc;

import ch.stormwind.aoc.AocProcessor.Id;
import ch.stormwind.aoc.AocProcessor.Part;
import ch.stormwind.aoc.AocProcessor.Type;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.DOTALL;
import static java.util.regex.Pattern.MULTILINE;

/**
 * The most simplistic resource reader ever.
 */
public class ResourceReader {

    private static final Pattern EXAMPLE_INPUT = Pattern.compile("``\n(.+?)```\n", MULTILINE | DOTALL);
    private static final Pattern EXAMPLE_SOLUTION = Pattern.compile("[*]{2}`(.+?)`[*]{2}", MULTILINE | DOTALL);

    /**
     * Reads given input data from resource files.
     */
    public String readInput(Id id, Part part, Type type) {
        return readAocResource(id, part, type, EXAMPLE_INPUT, "input.data");
    }

    /**
     * Reads given solution from resource files.
     */
    public Optional<String> readSolution(Id id, Part part, Type type) {
        try {
            return Optional.of(readAocResource(id, part, type, EXAMPLE_SOLUTION, "solution.data"));
        } catch (Throwable e) {
            return Optional.empty();
        }
    }

    private String readAocResource(Id id, Part part, Type type, Pattern pattern, String fileName) {
        String path = String.format("%d/%02d/part%d/", id.year(), id.day(), part.ordinal() + 1);
        switch (type) {
            case INPUT:
                return read(path + fileName);
            case EXAMPLE:
                String task = read(path + "task.md");
                var m = pattern.matcher(task);
                if (m.find() && m.groupCount() > 0) {
                    return m.group(1);
                }
        }
        throw new IllegalStateException("Unable to read example!");
    }

    /**
     * Reads given resource file as a string.
     */
    public String read(String fileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(fileName)) {
            try (InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
