package ch.stormwind.aoc;

import java.io.PrintStream;

import static ch.stormwind.aoc.JdkConvenience.env;
import static ch.stormwind.aoc.JdkConvenience.property;

/**
 * The most simplistic logger ever.
 */
public class SimpleLogger {

    private final int level;
    private final PrintStream out;

    public SimpleLogger(int level) {
        this.level = env("LOG_LEVEL").or(() -> property("LOG_LEVEL")).map(Integer::valueOf).orElse(level);
        this.out = System.out;
    }

    public void log(int level, int number) {
        if (this.level >= level)
            out.println(number);
    }

    public void log(int level, String message, Object... args) {
        if (this.level >= level)
            out.println(String.format(message, args));
    }
}
