import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

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

    private static List<Stack<String>> getCrateStack(List<String> stacks) {
        List<Stack<String>> crateStack = new ArrayList<>();

        String lastLine = stacks.get(stacks.size() - 1);
        List<String> crates = stacks.subList(0, stacks.size() - 1);
        Collections.reverse(crates);

        for(int index = 0; index < lastLine.length(); index++) {
            if(Character.isDigit(lastLine.charAt(index))) {
                crateStack.add(new Stack<>());
                Stack<String> currStack = crateStack.get(crateStack.size() - 1);

                for(String crate: crates) {
                    if(index < crate.length()) {
                        String crateValue = crate.substring(index, index + 1);
                        if (!crateValue.isBlank())
                            currStack.push(crateValue);
                    }
                }
            }
        }

        return crateStack;
    }

    private static String part1(TestType type) {
        List<String> data = getInput(getFileName(type));

        int delimiterIndex = data.indexOf("");
        List<String> stacks = data.subList(0, delimiterIndex);
        List<String> operations = data.subList(delimiterIndex + 1, data.size());

        List<Stack<String>> crateStacks = getCrateStack(stacks);

        for(String operation: operations) {
            String[] arguments = operation.split(" "); // move <x> from <y> to <z>
            int quantity = Integer.parseInt(arguments[1]);
            int from = Integer.parseInt(arguments[3]) - 1;
            int to = Integer.parseInt(arguments[5]) - 1;

            for(int count = 1; count <= quantity; count++) {
                String value = crateStacks.get(from).pop();
                crateStacks.get(to).push(value);
            }
        }

        StringBuilder topValues = new StringBuilder();
        for (Stack<String> crateStack : crateStacks) {
            topValues.append(crateStack.peek());
        }

        return topValues.toString();
    }

    private static String part2(TestType type) {
        List<String> data = getInput(getFileName(type));

        int delimiterIndex = data.indexOf("");
        List<String> stacks = data.subList(0, delimiterIndex);
        List<String> operations = data.subList(delimiterIndex + 1, data.size());

        List<Stack<String>> crateStacks = getCrateStack(stacks);

        for(String operation: operations) {
            String[] arguments = operation.split(" "); // move <x> from <y> to <z>
            int quantity = Integer.parseInt(arguments[1]);
            int from = Integer.parseInt(arguments[3]) - 1;
            int to = Integer.parseInt(arguments[5]) - 1;

            List<String> extractedValues = new ArrayList<>();
            for(int count = 1; count <= quantity; count++) {
                String value = crateStacks.get(from).pop();
                extractedValues.add(value);
            }
            Collections.reverse(extractedValues);

            for(String value: extractedValues) {
                crateStacks.get(to).push(value);
            }
        }

        StringBuilder topValues = new StringBuilder();
        for (Stack<String> crateStack : crateStacks) {
            topValues.append(crateStack.peek());
        }

        return topValues.toString();
    }

    public static void main(String[] args) {
        System.out.println(part1(TestType.REAL));
        System.out.println(part2(TestType.REAL));
    }
}