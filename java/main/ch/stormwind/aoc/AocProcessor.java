package ch.stormwind.aoc;

public interface AocProcessor<T> {

    enum Part {PART1, PART2}

    enum Type {INPUT, EXAMPLE}

    record Id(int year, int day) {
    }

    T process(Part part, Type type);
}
