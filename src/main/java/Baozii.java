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
            if (msg.startsWith("todo")) {
                String name = msg.substring(4).strip();
                if (name.isEmpty()) {
                    System.out.println("The description for todo cannot be empty.");
                } else {
                    tasks.add(new Todo(msg));
                }
            } else if (msg.startsWith("deadline")) {
                String name = msg.substring(8).strip();
                if (name.isEmpty()) {
                    System.out.println("The description for deadline cannot be empty.");
                } else if (!name.contains("\\by")){
                    System.out.println("The description for deadline has to contain the due time.");
                } else {
                    String by = name.substring(name.indexOf("\\by") + 3).strip();
                    tasks.add(new DeadLine(name.substring(0, name.indexOf("\\by")), by));
                }
            } else if (msg.startsWith("event")) {
                String name = msg.substring(5).strip();
                if (name.isEmpty()) {
                    System.out.println("The description for event cannot be empty.");
                } else if (!name.contains("\\from") || !name.contains("\\to")){
                    System.out.println("The description for event has to contain the start and end time.");
                } else {
                    int ifrom = name.indexOf("\\from");
                    int ito = name.indexOf("\\to");
                    String from, to;
                    if (ifrom < ito) {
                        from = name.substring(ifrom + 5, ito).strip();
                        to = name.substring(ito + 3);
                    } else {
                        to = name.substring(ito + 5, ifrom).strip();
                        from = name.substring(ifrom + 3);
                    }
                    name = name.substring(0, Math.min(ifrom, ito));
                    tasks.add(new Event(name, from, to));
                }
            } else if (msg.startsWith("delete")) {
                String istr = msg.substring(6).strip();
                int i;
                try {
                    i = Integer.parseInt(istr);
                } catch (Exception e) {
                    System.out.println("Invalid argument for index.");
                    continue;
                }
                tasks.remove(i);
            } else if (msg.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + ". " + tasks.get(i));
                }
            } else if (msg.equals("bye")) {
                System.out.println("Bye, have a great day!");
            }
        }
    }
}
