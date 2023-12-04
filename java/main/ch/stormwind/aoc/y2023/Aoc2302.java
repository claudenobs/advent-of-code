package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static ch.stormwind.aoc.AocProcessor.Part.PART1;
import static ch.stormwind.aoc.AocProcessor.Part.PART2;

public class Aoc2302 extends BaseAocProcessor<Integer> {

    private static final List<String> COLORS = List.of("red", "green", "blue");
    private static final int[] MAX_SIZE = {12, 13, 14};

    protected Aoc2302() {
        super(new Id(2023, 2));
    }

    @Override
    public Integer process(Part part, Type type) {
        var input = RESOURCES.readInput(id, part, type);
        var s = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = 0;
        for (int i = 1; s.hasNext(); i++) {
            var l = s.nextLine().split(": ")[1];
            if (part == PART1 && part1(l)) {
                LOGGER.log(1, i);
                result += i;
            }
            if (part == PART2) {
                result += part2(l);
            }
        }
        return result;
    }

    private boolean part1(String l) {
        for (String game : l.split("; ")) {
            for (String[] color : Arrays.stream(game.split(", ")).map(c -> c.split(" ")).toList()) {
                LOGGER.log(2, color[0] + " " + color[1]);
                int max = MAX_SIZE[COLORS.indexOf(color[1])];
                if (Integer.valueOf(color[0]) > max)
                    return false;
            }
        }
        return true;
    }

    private int part2(String l) {
        int[] min = new int[3];
        for (String game : l.split("; ")) {
            for (String[] color : Arrays.stream(game.split(", ")).map(c -> c.split(" ")).toList()) {
                LOGGER.log(2, color[0] + " " + color[1]);
                int count = Integer.valueOf(color[0]);
                int index = COLORS.indexOf(color[1]);
                if (count > min[index])
                    min[index] = count;
            }
        }
        return min[0] * min[1] * min[2];
    }
}
