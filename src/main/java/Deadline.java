public class Deadline extends Task {
    public String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }

    @Override
    public String toSerial() {
        return "E&" + super.toSerial() + "&" + by;
    }
}
