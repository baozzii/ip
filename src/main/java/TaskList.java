import java.util.*;
import java.util.function.Consumer;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void delete(Task task) {
        tasks.remove(task);
    }

    public void delete(int i) {
        tasks.remove(i);
    }

    public void list() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i));
        }
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void enumerate(Consumer<? super Task> consumer) {
        for (Task task : tasks) consumer.accept(task);
    }
}
