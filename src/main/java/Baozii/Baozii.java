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
            Action action;
            try {
                action = parser.parse(msg);
            } catch (InvalidCommandException e) {
                ui.showException(e);
                continue;
            }
            if (action.type() == ActionType.ADD) {
                ui.showAdd(tasks.add(action.task()));
            } else if (action.type() == ActionType.DELETE) {
                ui.showDelete(tasks.delete(action.index()));
            } else if (action.type() == ActionType.LIST) {
                ui.showList(tasks);
            } else if (action.type() == ActionType.QUIT) {
                ui.goodbye();
                break;
            } else if (action.type() == ActionType.MARK) {
                ui.showMark(tasks.mark(action.index()));
            } else if (action.type() == ActionType.UNMARK) {
                ui.showUnmark(tasks.unmark(action.index()));
            } else {
                ui.showList(tasks.find(action.pattern()));
            }
        }
        storage.store(tasks);
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
