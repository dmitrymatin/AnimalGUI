package shared;

import java.util.ArrayList;
import java.util.List;

public class Request {
    private final String command;
    private final ArrayList<String> args = new ArrayList<>();

    public Request(String command, String... args) {
        this.command = command;
        if (args != null)
            this.args.addAll(List.of(args));
    }

    public static Request parseRequest(String queryString) {
        Request request = null;

        // query format ::= <command>?[<arg1>][&<arg2>]...[&<argN>]
        String command;
        String argsString;

        String[] queryParts = queryString.split("\\?");
        String[] args = null;
        if (queryParts.length > 0) {
            command = queryParts[0];
            if (queryParts.length > 1) {
                argsString = queryParts[1];
                args = argsString.split("&");
            }
            request = new Request(command, args);
        }
        return request;
    }

    @Override
    public String toString() {
        char commandDelimiter = '?';
        return command + commandDelimiter + String.join("&", args);
    }

    public String getCommand() {
        return command;
    }

    public ArrayList<String> getArgs() {
        return args;
    }
}
