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

    private static Set<String> getItemSetFrom(String bag) {
        String[] items = bag.split("");
        Set<String> itemSet = new TreeSet<>(String::compareTo);
        itemSet.addAll(List.of(items));

        return itemSet;
    }

    private static String getCommonItemForBag(String bag) {
        String firstHalf = bag.substring(0, bag.length() / 2);
        String secondHalf = bag.substring(bag.length() / 2);

        Set<String> firstItemSet = getItemSetFrom(firstHalf);
        Set<String> secondItemSet = getItemSetFrom(secondHalf);

        Set<String> intersection = new TreeSet<>(firstItemSet);
        intersection.retainAll(secondItemSet);

        return (String) intersection.toArray()[0];
    }

    private static String getCommonItemForGroup(String bag1, String bag2, String bag3) {
        Set<String> bag1Set = getItemSetFrom(bag1);
        Set<String> bag2Set = getItemSetFrom(bag2);
        Set<String> bag3Set = getItemSetFrom(bag3);

        Set<String> intersection = new TreeSet<>(bag1Set);
        intersection.retainAll(bag2Set);
        intersection.retainAll(bag3Set);

        return (String) intersection.toArray()[0];
    }

    private static long getPriorityForItem(String item) {
        char itemCharacter = item.charAt(0);
        if (Character.isUpperCase(itemCharacter))
            return 26 + itemCharacter - 'A' + 1L;

        return itemCharacter - 'a' + 1L;
    }

    private static long part1(TestType type) {
        List<String> data = getInput(getFileName(type));

        List<String> commonItems = new ArrayList<>();
        for (String line : data) {
            commonItems.add(getCommonItemForBag(line));
        }

        return commonItems.stream()
                .map(Main::getPriorityForItem)
                .mapToLong(Long::longValue)
                .sum();
    }

    private static long part2(TestType type) {
        List<String> data = getInput(getFileName(type));

        List<String> items = new ArrayList<>();
        for (int i = 0; i < data.size(); i += 3) {
            String bag1 = data.get(i);
            String bag2 = data.get(i + 1);
            String bag3 = data.get(i + 2);

            items.add(getCommonItemForGroup(bag1, bag2, bag3));
        }

        return items.stream()
                .map(Main::getPriorityForItem)
                .mapToLong(Long::longValue)
                .sum();
    }

    public static void main(String[] args) {
        System.out.println(part1(TestType.REAL));
        System.out.println(part2(TestType.REAL));
    }
}