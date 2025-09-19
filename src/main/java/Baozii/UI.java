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

    /**
     * Given a nullable task, prints whether it is successfully added.
     * @param task a nullable task object. If it is null, it means the add action was unsuccessful

     */
    public void showAdd(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully added task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task add unsuccessful");
        });
    }

    /**
     * Given a nullable task, prints whether it is successfully deleted.
     * @param task a nullable task object. If it is null, it means the delete action was unsuccessful

     */
    public void showDelete(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully deleted task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task delete unsuccessful");
        });
    }

    /**
     * Given a nullable task, prints whether it is successfully marked.
     * @param task a nullable task object. If it is null, it means the mark action was unsuccessful

     */
    public void showMark(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully marked task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task mark unsuccessful");
        });
    }

    /**
     * Given a nullable task, prints whether it is successfully unmarked.
     * @param task a nullable task object. If it is null, it means the unmark action was unsuccessful

     */
    public void showUnmark(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully unmarked task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task unmark unsuccessful");
        });
    }

    public void showTag(Optional<Task> task) {
        task.ifPresentOrElse(t -> {
            System.out.println("Successfully tagged task:");
            System.out.println(t);
        }, () -> {
            System.out.println("Task tag unsuccessful");
        });
    }

    /**
     * Prints out the tasklist
     * @param tasks the given tasklist

     */
    public void showList(TaskList tasks) {
        System.out.print(tasks);
    }
    public void showException(Exception e) {
        System.out.println(e.getMessage());
    }
}
