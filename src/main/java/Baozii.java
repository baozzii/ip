import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.lang.*;

public class Baozii {
    public static void main(String[] args) {
        System.out.println("Hi! I am Baozii. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Parser parser = new Parser();

        Path path = Path.of("data.txt");
        if (!Files.exists(path)) {
            try {
                Files.createFile(path);
            } catch (IOException _) {

            }
        }

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            for (String line; (line = br.readLine()) != null; ) {
                parser.parseTaskFromFile(line).ifPresent(tasks::add);
            }
        } catch (IOException _) {

        }

        while (true) {
            System.out.print(">> ");
            String msg = scanner.nextLine().strip();
            Action action;
            try {
                action = parser.parse(msg);
            } catch (InvalidCommandException e) {
                System.out.println(e.getMessage());
                continue;
            }
            if (action.type() == ActionType.ADD) {
                tasks.add(action.task());
                System.out.println("Task added successfully: " + action.task());
            } else if (action.type() == ActionType.DELETE) {
                try {
                    System.out.println("Task deleted successfully: " + tasks.get(action.index()));
                    tasks.delete(action.index());
                } catch (RuntimeException e) {
                    System.out.println("There aren't that many tasks here!");
                }
            } else if (action.type() == ActionType.LIST) {
                tasks.list();
            } else if (action.type() == ActionType.QUIT) {
                System.out.println("Bye, have a great day!");
                break;
            }
        }

        try {
            Files.writeString(path, "", StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            tasks.enumerate(task -> {
                try {
                    Files.writeString(path, parser.serialise(task) + "\n", StandardCharsets.UTF_8,
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException _) {

                }
            });
        } catch (IOException _) {

        }
    }
}
