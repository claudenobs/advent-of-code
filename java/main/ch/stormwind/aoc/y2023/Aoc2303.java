package ch.stormwind.aoc.y2023;

import ch.stormwind.aoc.BaseAocProcessor;

import java.util.ArrayList;
import java.util.regex.Pattern;

import static ch.stormwind.aoc.AocProcessor.Part.PART1;
import static ch.stormwind.aoc.AocProcessor.Part.PART2;
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
                var ns = new ArrayList<Integer>(2);
                for (int y = max(0, i - 1); y < min(i + 2, lines.length); y++) {
                    var om = (part == PART1 ? symbol : number).matcher(lines[y]);
                    while (om.find()) {
                        if (part == PART1 && om.end() <= cm.end() + 1 && om.start() >= cm.start() - 1)
                            result += Integer.valueOf(cm.group());
                        if (part == PART2 && om.start() <= cm.start() + 1 && om.end() >= cm.end() - 1)
                            ns.add(Integer.valueOf(om.group()));
                    }
                }
                if (part == PART2) result += ns.size() == 2 ? ns.get(0) * ns.get(1) : 0;
            }
        }
        return result;
    }
}
