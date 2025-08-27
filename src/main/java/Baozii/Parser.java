package Baozii;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Parser {
    public Parser() {}

    private Action parseTodo(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() == 1) {
            throw new InvalidCommandException("The description of todo cannot be empty!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < tokens.size(); i++) {
            sb.append(tokens.get(i));
            if (i + 1 != tokens.size()) sb.append(" ");
        }
        return new Action(ActionType.ADD, new Todo(sb.toString().strip()), null);
    }

    private Action parseEvent(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() == 1) {
            throw new InvalidCommandException("The description of event cannot be empty!");
        }
        enum State {
            NAME, FROM, TO
        }
        StringBuilder sbname = new StringBuilder();
        StringBuilder sbfrom = new StringBuilder();
        StringBuilder sbto = new StringBuilder();
        boolean fromTag = false, toTag = false;
        State state = State.NAME;
        for (int i = 1; i < tokens.size(); i++) {
            String s = tokens.get(i);
            if (s.equalsIgnoreCase("\\from")) {
                if (fromTag) {
                    throw new InvalidCommandException("There shouldn't be multiple \\from tags.");
                }
                fromTag = true;
                state = State.FROM;
            } else if (s.equalsIgnoreCase("\\to")) {
                if (toTag) {
                    throw new InvalidCommandException("There shouldn't be multiple \\to tags.");
                }
                toTag = true;
                state = State.TO;
            } else {
                if (state == State.NAME) {
                    sbname.append(s);
                    sbname.append(" ");
                } else if (state == State.FROM) {
                    sbfrom.append(s);
                    sbfrom.append(" ");
                } else {
                    sbto.append(s);
                    sbto.append(" ");
                }
            }
        }
        if (sbname.isEmpty()) {
            throw new InvalidCommandException("Oops, your event doesn't have a name!");
        }
        if (sbfrom.isEmpty()) {
            throw new InvalidCommandException("Oops, your event doesn't have a start time!");
        }
        if (sbto.isEmpty()) {
            throw new InvalidCommandException("Oops, your event doesn't have a end time!");
        }
        return new Action(ActionType.ADD,
                new Event(sbname.toString().strip(), sbfrom.toString().strip(), sbto.toString().strip()), null);
    }

    private Action parseDeadline(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() == 1) {
            throw new InvalidCommandException("The description of deadline cannot be empty!");
        }
        enum State {
            NAME, BY
        }
        StringBuilder sbname = new StringBuilder();
        StringBuilder sbby = new StringBuilder();
        State state = State.NAME;
        boolean byTag = false;
        for (int i = 1; i < tokens.size(); i++) {
            String s = tokens.get(i);
            if (s.equalsIgnoreCase("\\by")) {
                if (byTag) {
                    throw new InvalidCommandException("There shouldn't be multiple \\by tags.");
                }
                byTag = true;
                state = State.BY;
            } else {
                if (state == State.NAME) {
                    sbname.append(s);
                    sbname.append(" ");
                } else {
                    sbby.append(s);
                    sbby.append(" ");
                }
            }
        }
        if (sbname.isEmpty()) {
            throw new InvalidCommandException("Oops, your deadline doesn't have a name!");
        }
        if (sbby.isEmpty()) {
            throw new InvalidCommandException("Oops, your deadline doesn't have a due time!");
        }
        return new Action(ActionType.ADD, new Deadline(sbname.toString().strip(), sbby.toString().strip()), null);
    }

    private Action parseDelete(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() > 2) {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
        if (tokens.size() == 1) {
            throw new InvalidCommandException("Which task do you want to delete?");
        }
        try {
            Integer index = Integer.parseInt(tokens.get(1));
            return new Action(ActionType.DELETE, null, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("It seems like your index is not an integer!");
        }
    }

    private Action parseList(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() > 1) {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
        return new Action(ActionType.LIST, null, null);
    }

    private Action parseQuit(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() > 1) {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
        return new Action(ActionType.QUIT, null, null);
    }

    private Action parseMark(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() > 2) {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
        if (tokens.size() == 1) {
            throw new InvalidCommandException("Which task do you want to mark?");
        }
        try {
            Integer index = Integer.parseInt(tokens.get(1));
            return new Action(ActionType.MARK, null, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("It seems like your index is not an integer!");
        }
    }

    private Action parseUnmark(List<String> tokens) throws InvalidCommandException {
        if (tokens.size() > 2) {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
        if (tokens.size() == 1) {
            throw new InvalidCommandException("Which task do you want to unmark?");
        }
        try {
            Integer index = Integer.parseInt(tokens.get(1));
            return new Action(ActionType.UNMARK, null, index);
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("It seems like your index is not an integer!");
        }
    }

    public Action parse(String s) throws InvalidCommandException {
        List<String> tokens = Arrays.stream(s.split(" ")).filter(msg -> !msg.isEmpty()).toList();
        if (tokens.isEmpty()) {
            throw new InvalidCommandException("Oops! That was an empty command!");
        }
        String command = tokens.get(0);
        if (command.equalsIgnoreCase("todo")) {
            return parseTodo(tokens);
        } else if (command.equalsIgnoreCase("deadline")) {
            return parseDeadline(tokens);
        } else if (command.equalsIgnoreCase("event")) {
            return parseEvent(tokens);
        } else if (command.equalsIgnoreCase("delete")) {
            return parseDelete(tokens);
        } else if (command.equalsIgnoreCase("list")) {
            return parseList(tokens);
        } else if (command.equalsIgnoreCase("bye")) {
            return parseQuit(tokens);
        } else if (command.equalsIgnoreCase("mark")) {
            return parseMark(tokens);
        } else if (command.equalsIgnoreCase("unmark")) {
            return parseUnmark(tokens);
        } else {
            throw new InvalidCommandException("Hmm... I don't recognise this command.");
        }
    }

    public Optional<Task> parseTaskFromFile(String s) {
        List<String> tokens = Arrays.stream(s.split("&")).toList();
        if (tokens.isEmpty()) return Optional.empty();
        String type = tokens.get(0);
        return switch (type) {
            case "T" -> Optional.of(new Todo(tokens.get(1)));
            case "D" -> Optional.of(new Deadline(tokens.get(1), tokens.get(2)));
            case "E" -> Optional.of(new Event(tokens.get(1), tokens.get(2), tokens.get(3)));
            default -> Optional.empty();
        };
    }

    public String serialise(Task task) {
        return task.toSerial();
    }
}
