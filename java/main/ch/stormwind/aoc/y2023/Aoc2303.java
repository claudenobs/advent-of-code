package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ch.stormwind.aoc.AocProcessor.Part.PART1;
import static java.lang.Math.max;
import static java.lang.Math.min;

public class Aoc2303 extends BaseAocProcessor<Integer> {

    private static Pattern symbol = Pattern.compile("[^A-Za-z0-9.]");
    private static Pattern number = Pattern.compile("\\d+");
    private static Pattern gear = Pattern.compile("\\*");

    protected Aoc2303() {
        super(new Id(2023, 3));
    }

    @Override
    public Integer process(Part part, Type type) {
        var input = RESOURCES.readInput(id, part, type);
        var lines = input.split("\n");
        var result = 0;
        for (int i = 0; i < lines.length; i++) {
            var m = (part == PART1 ? number : gear).matcher(lines[i]);
            while (m.find()) {
                result += part == PART1 ? part1(lines, i, m) : part2(lines, i, m);
            }
        }
        return result;
    }

    private int part1(String[] lines, int i, Matcher m) {
        for (int y = max(0, i - 1); y < min(i + 2, lines.length); y++) {
            for (int x = max(0, m.start() - 1); x < min(m.end() + 1, lines[i].length()); x++) {
                var c = lines[y].substring(x, x + 1);
                if (symbol.matcher(c).matches()) {
                    return Integer.valueOf(m.group(0));
                }
            }
        }
        return 0;
    }

    private int part2(String[] lines, int i, Matcher m) {
        var ns = new ArrayList<Integer>();
        for (int y = max(0, i - 1); y < min(i + 2, lines.length); y++) {
            var n = number.matcher(lines[y]);
            while (n.find()) {
                if (n.start() <= m.start() + 1 && n.end() >= m.start())
                    ns.add(Integer.valueOf(n.group(0)));
            }
        }
        return ns.size() == 2 ? ns.get(0) * ns.get(1) : 0;
    }
}
