package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

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
            var cm = (part == PART1 ? number : gear).matcher(lines[i]);
            while (cm.find()) {
                int first = -1; // only used for PART2
                for (int y = max(0, i - 1); y < min(i + 2, lines.length); y++) {
                    var om = (part == PART1 ? symbol : number).matcher(lines[y]);
                    while (om.find()) {
                        var c = part == PART1 ? cm : om; var o = part == PART1 ? om : cm;
                        if (o.end() <= c.end() + 1 && o.start() >= c.start() - 1) {
                            var n = Integer.parseInt(c.group());
                            if (part == PART1) { result += n; }
                            else if (first < 0) { first = n; }
                            else { result += first * n; first = -1; }
                        }
                    }
                }
            }
        }
        return result;
    }
}
