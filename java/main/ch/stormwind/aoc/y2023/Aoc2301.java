package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public class Aoc2301 extends BaseAocProcessor<Integer> {

    private static List<String> digits = List.of("zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
    private static Pattern pattern = Pattern.compile("\\d|" + digits.stream().collect(joining("|")));

    public Aoc2301() {
        super(new Id(2023, 1));
    }

    public Integer process(Part part, Type type) {
        var input = RESOURCES.readInput(id, part, type);
        var s = new Scanner(new ByteArrayInputStream(input.getBytes()));
        int result = 0;
        for (int i = 0; s.hasNext(); i++) {
            var n = processLine(s.nextLine(), part);
            LOGGER.log(1, n);
            result += n;
        }
        return result;
    }

    private int processLine(String l, Part part) {
        switch (part) {
            case PART1 -> {
                l = l.replaceAll("\\D", "");
            }
            case PART2 -> {
                var m = pattern.matcher(l);
                l = "";
                while (m.find()) {
                    var g = m.group(0);
                    var d = g.length() == 1 ? g : digits.indexOf(g);
                    m.region(m.toMatchResult().start() + 1, m.regionEnd());
                    l = l + d;
                }
            }
            default -> {
                // basic solution using only string methods
                var r = "";
                while (l.length() > 0) {
                    for (var p : digits)
                        if (l.startsWith(p))
                            r += digits.indexOf(p);
                    for (Integer p = 0; p < digits.size(); p++)
                        if (l.startsWith(p.toString()))
                            r += p;
                    l = l.substring(1);
                }
                l = r;
            }
        }
        LOGGER.log(2, l);
        return Integer.valueOf("" + l.charAt(0) + l.charAt(l.length() - 1));
    }
}
