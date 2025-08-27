import java.util.*;
import java.lang.*;

public class Baozii {
    public static void main(String[] args) {
        System.out.println("Hi! I am Baozii. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Parser parser = new Parser();

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
    }
}
