package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.util.regex.Pattern;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Aoc2303 extends BaseAocProcessor<Integer> {

    private static Pattern symbol = Pattern.compile("[^A-Za-z0-9.]");
    private static Pattern pattern = Pattern.compile("\\d+");

    protected Aoc2303() {
        super(new Id(2023, 3));
    }

    @Override
    public Integer process(Part part, Type type) {
        var input = RESOURCES.readInput(id, part, type);
        var lines = input.split("\n");
        var result = 0;
        for (int i = 0; i < lines.length; i++) {
            var line = lines[i];
            var m = pattern.matcher(line);
            while (m.find()) {
                int n = Integer.valueOf(m.group(0));
                for (int j = max(0, m.start() - 1); j < min(m.end() + 1, line.length()); j++) {
                    for (int k = max(0, i - 1); k < min(i + 2, lines.length); k++) {
                        var c = lines[k].substring(j, j + 1);
                        if (symbol.matcher(c).matches()) {
                            result += n;
                        }
                    }
                }
            }
        }
        return result;
    }
}
