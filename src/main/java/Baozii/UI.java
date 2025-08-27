package Baozii;

import java.util.Optional;
import java.util.Scanner;

public class UI {
    private static final String WELCOME = "Hi! I am Baozii. What can I do for you?";
    private static final String GOODBYE = "Bye, have a great day!";
    private final Scanner scanner;

    public UI() {
        scanner = new Scanner(System.in);
    }

    public void welcome() {
        System.out.println(WELCOME);
    }

    public void goodbye() {
        System.out.println(GOODBYE);
    }

    public String getUserPrompt() {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    public void showAdd(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully added task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task add unsuccessful");
        });
    }

    public void showDelete(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully deleted task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task delete unsuccessful");
        });
    }

    public void showMark(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully marked task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task mark unsuccessful");
        });
    }

    public void showUnmark(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully unmarked task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task unmark unsuccessful");
        });
    }

    public void showList(TaskList tasks) {
        System.out.println(tasks);
    }
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }
}
