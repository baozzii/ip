import java.util.*;
import java.lang.*;

public class Baozii {
    public static void main(String[] args) {
        System.out.println("Hi! I am Baozii. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();

        while (true) {
            System.out.print(">> ");
            String msg = scanner.nextLine().strip();
            if (msg.equals("bye")) {
                System.out.println("Bye, have a great day!");
                break;
            } else if (msg.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(i + 1);
                    System.out.print(". ");
                    System.out.println(tasks.get(i));
                }
            } else if (msg.startsWith("add")) {
                msg = msg.substring(3).strip();
                tasks.add(new Task(msg));
                System.out.println("added: " + tasks.getLast());
            } else if (msg.startsWith("mark")) {
                int i = Integer.parseInt(msg.substring(4).strip());
                tasks.get(i).mark();
                System.out.println("marked: " + tasks.get(i));
            } else if (msg.startsWith("unmark")) {
                int i = Integer.parseInt(msg.substring(4).strip());
                tasks.get(i).unmark();
                System.out.println("unmarked: " + tasks.get(i));
            } else {
                System.out.println("Unrecognised keyword");
            }
        }
    }
}
