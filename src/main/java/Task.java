public class Task {
    private String name;
    private boolean done;

    public Task() {
        this("");
    }
    public Task(String name) {
        this.name = name;
        done = false;
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + name;
    }
}
