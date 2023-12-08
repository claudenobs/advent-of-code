package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.util.regex.Pattern;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Aoc2303 extends BaseAocProcessor<Integer> {

    private static Pattern symbol = Pattern.compile("[^A-Za-z0-9.]");
    private static Pattern number = Pattern.compile("\\d+");

    protected Aoc2303() {
        super(new Id(2023, 3));
    }

    @Override
    public Integer process(Part part, Type type) {
        var input = RESOURCES.readInput(id, part, type);
        var lines = input.split("\n");
        var result = 0;
        for (int i = 0; i < lines.length; i++) {
            var m = number.matcher(lines[i]);
            while (m.find()) {
                for (int y = max(0, i - 1); y < min(i + 2, lines.length); y++) {
                    for (int x = max(0, m.start() - 1); x < min(m.end() + 1, lines[i].length()); x++) {
                        var c = lines[y].substring(x, x + 1);
                        if (symbol.matcher(c).matches()) {
                            result += Integer.valueOf(m.group(0));
                        }
                    }
                }
            }
        }
        return result;
    }
}
