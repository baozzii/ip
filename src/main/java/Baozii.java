import java.util.*;

public class Baozii {
    public static void main(String[] args) {
        System.out.println("Hi! I am Baozii. What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print(">> ");
            String msg = scanner.next();
            if (msg.equals("bye")) {
                System.out.println("Bye, have a great day!");
                break;
            } else {
                System.out.println(msg);
            }
        }
    }
}
