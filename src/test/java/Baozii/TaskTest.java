package Baozii;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    @Test
    public void testToString() {
        assertEquals(new Task().toString(), "[ ] ");
        Task task = new Task("test");
        task.mark();
        task.tag("tag");
        assertEquals(task.toString(), "[X] test # tag");
    }

    @Test
    public void testToSerial(){
        assertEquals(new Task().toSerial(), "&false");
        Task task = new Task("test");
        task.mark();
        task.tag("tag");
        assertEquals(task.toString(), "test&false#tag");
    }
}
