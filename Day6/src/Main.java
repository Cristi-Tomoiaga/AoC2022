import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

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

    private static boolean checkDifferent(List<Character> window) {
        Set<Character> uniqueCharacters = new HashSet<>(window);

        return uniqueCharacters.size() == window.size();
    }

    private static int part1(TestType type) {
        String dataStreamBuffer = getInput(getFileName(type)).get(0);

        for(int index = 3; index < dataStreamBuffer.length(); index++) {
            String windowString = dataStreamBuffer.substring(index - 3, index + 1);
            List<Character> window = windowString.chars().mapToObj(c -> (char) c).toList();

            if(checkDifferent(window))
                return index + 1;
        }

        return 0;
    }

    private static int part2(TestType type) {
        String dataStreamBuffer = getInput(getFileName(type)).get(0);

        for(int index = 13; index < dataStreamBuffer.length(); index++) {
            String windowString = dataStreamBuffer.substring(index - 13, index + 1);
            List<Character> window = windowString.chars().mapToObj(c -> (char) c).toList();

            if(checkDifferent(window))
                return index + 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        System.out.println(part1(TestType.REAL));
        System.out.println(part2(TestType.REAL));
    }
}