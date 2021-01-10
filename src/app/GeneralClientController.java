package app;

import shared.FormLogger;
import shared.Logger;
import shared.Request;

import java.util.ArrayList;

public class GeneralClientController {
    private static AnimalClientForm clientForm = null;
    private static AnimalClientFormListener clientFormListener = null;
    private static Logger logger = null;

    public static void startApp() {
        clientForm = new AnimalClientForm("Animal World");
        logger = new FormLogger(clientForm.getStatusMessageTextArea());
        clientFormListener = new AnimalClientFormListener(clientForm);
    }

    public static void connect() {
        String result = NetworkController.connect();
        logger.logMessage(result);
    }

    public static void prepareListingRequest(String label) {
        final String command = "get";
        ArrayList<String> requestArgs = new ArrayList<>();

        switch (label) {
            case "Все":
                requestArgs.add("all");
                break;
            case "Хищники":
                requestArgs.add("pdt");
                break;
            case "Травоядные":
                requestArgs.add("hbv");
                break;
            case "Трава":
                requestArgs.add("grs");
                break;
            default:
                throw new IllegalArgumentException("Некорректное значение выбора");
        }

        Request request = new Request(command, (String[]) requestArgs.toArray());
        String response = NetworkController.sendRequest(request);
        // todo: parse response
    }

    public static void sendRequest(Request request) {
//        String response = NetworkController.sendRequest(request.toString());
//        logger.logMessage(response);
    }
}
