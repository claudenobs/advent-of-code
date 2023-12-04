package ch.stormwind.aoc;

import static ch.stormwind.aoc.AnsiEscapeCode.GREEN;
import static ch.stormwind.aoc.AnsiEscapeCode.RED;
import static ch.stormwind.aoc.AnsiEscapeCode.RESET;
import static ch.stormwind.aoc.AnsiEscapeCode.YELLOW;

public abstract class BaseAocProcessor<T> implements AocProcessor<T> {

    protected static final SimpleLogger LOGGER = new SimpleLogger(2);
    protected static final ResourceReader RESOURCES = new ResourceReader();

    protected final Id id;

    protected BaseAocProcessor(Id id) {
        this.id = id;
    }

    public void run(Part part, Type type) {
        var expected = RESOURCES.readSolution(id, part, type);
        var result = String.valueOf(process(part, type));
        var color = expected.isEmpty() ? YELLOW : expected.get().equals(result) ? GREEN : RED;
        System.out.println(color + result + RESET);
    }
}
