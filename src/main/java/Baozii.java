import java.util.*;
import java.lang.*;

public class Baozii {
    public static void main(String[] args) {
        System.out.println("Hi! I am Baozii. What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<String> tasks = new ArrayList<>();

        while (true) {
            System.out.print(">> ");
            String msg = scanner.next();
            if (msg.equals("bye")) {
                System.out.println("Bye, have a great day!");
                break;
            } else if (msg.equals("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print(i + 1);
                    System.out.print(". ");
                    System.out.println(tasks.get(i));
                }
            } else {
                tasks.add(msg);
                System.out.println("added: " + msg);
            }
        }
    }
}
