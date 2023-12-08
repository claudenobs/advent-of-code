package ch.stormwind.aoc;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.List;
import java.util.Set;

import static java.lang.Integer.parseInt;

public class AocInitializr {

    private static final Set<PosixFilePermission> DATTR = PosixFilePermissions.fromString("rwxr-xr-x");
    private static final Set<PosixFilePermission> FATTR = PosixFilePermissions.fromString("rw-r--r--");

    public static void main(String[] args) throws IOException {
        int year = 2023, day = 1;
        if (args.length == 1) { args = args[0].split("-"); }
        if (args.length == 2) { year = parseInt(args[0]); day = parseInt(args[1]); }

        var root = Path.of(".", String.valueOf(year), p2(day));

        for (String dirName : List.of("part1", "part2")) {
            Files.createDirectories(root.resolve(dirName), PosixFilePermissions.asFileAttribute(DATTR));
            for (String fileName : List.of("task.md", "input.data", "solution.data")) {
                var file = root.resolve(dirName).resolve(fileName);
                if (!Files.exists(file)) Files.createFile(file, PosixFilePermissions.asFileAttribute(FATTR));
            }
        }

        var code = STR."""
            package ch.stormwind.aoc.y\{year};

            import ch.stormwind.aoc.BaseAocProcessor;

            import java.io.ByteArrayInputStream;
            import java.util.Scanner;

            public class Aoc\{l2(year)}\{p2(day)} extends BaseAocProcessor<Integer> {

                protected Aoc\{l2(year)}\{p2(day)}() {
                    super(new Id(\{year}, \{day}));
                }

                @Override
                public Integer process(Part part, Type type) {
                    var input = RESOURCES.readInput(id, part, type);
                    // var s = new Scanner(new ByteArrayInputStream(input.getBytes()));
                    var lines = input.split("\\n");
                    var result = 0;
                    for (int i = 0; i < lines.length; i++) { // s.hasNext()
                        var l = lines[i].split(": ")[1]; // s.nextLine()
                        result += 0;
                    }
                    return result;
                }
            }
            """;
        var file = new File(String.format("./java/main/ch/stormwind/aoc/y%d/Aoc%s%s.java", year, l2(year), p2(day))).toPath();
        Files.write(file, code.getBytes(StandardCharsets.UTF_8));
    }

    private static String p2(int number) {
        return String.format("%02d", number);
    }

    private static String l2(int number) {
        return String.valueOf(number % 100);
    }
}
