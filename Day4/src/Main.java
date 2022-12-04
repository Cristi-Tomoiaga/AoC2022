import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

enum TestType {
    DUMMY, REAL
}

public class Main {
    private static String getFileName(TestType type) {
        return switch (type) {
            case DUMMY -> "dummy.txt";
            case REAL -> "input.txt";
        };
    }

    private static List<String> getInput(String fileName) {
        Path path = Paths.get("data/" + fileName);

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private static long part1(TestType type) {
        List<String> data = getInput(getFileName(type));

        long count = 0L;
        for (String line : data) {
            String[] stringSections = line.split(",");
            Section firstSection = Section.fromString(stringSections[0]);
            Section secondSection = Section.fromString(stringSections[1]);

            if(firstSection.contains(secondSection) || secondSection.contains(firstSection))
                count++;
        }

        return count;
    }

    private static long part2(TestType type) {
        List<String> data = getInput(getFileName(type));

        long count = 0L;
        for (String line : data) {
            String[] stringSections = line.split(",");
            Section firstSection = Section.fromString(stringSections[0]);
            Section secondSection = Section.fromString(stringSections[1]);

            if(Section.overlap(firstSection, secondSection))
                count++;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(part1(TestType.REAL));
        System.out.println(part2(TestType.REAL));
    }
}