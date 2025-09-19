package Baozii;

import java.io.IOException;
import java.lang.*;

public class Baozii {
    private final TaskList tasks;
    private final Parser parser;
    private final Storage storage;
    private final UI ui;

    private void run() throws IOException {
        tasks.load(storage.load(), parser);
        ui.welcome();
        while (true) {
            String msg = ui.getUserPrompt();
            assert msg != null;
            Action action;
            try {
                action = parser.parse(msg);
            } catch (InvalidCommandException e) {
                ui.showException(e);
                continue;
            }
            switch (action.type()) {
            case ADD -> ui.showAdd(tasks.add(action.task()));
            case DELETE -> ui.showDelete(tasks.delete(action.index()));
            case LIST -> ui.showList(tasks);
            case MARK -> ui.showMark(tasks.mark(action.index()));
            case UNMARK -> ui.showUnmark(tasks.unmark(action.index()));
            case FIND -> ui.showList(tasks.find(action.pattern()));
            case QUIT -> {
                ui.goodbye();
                storage.store(tasks);
                return;
            }
            }
        }
    }

    public Baozii() {
        tasks = new TaskList();
        parser = new Parser();
        storage = new Storage("data.txt");
        ui = new UI();
    }

    public static void main(String[] args) throws IOException {
        Baozii baozii = new Baozii();
        baozii.run();
    }
}
