package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shared.FormLogger;
import shared.Logger;
import shared.Request;
import shared.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GeneralClientController {
    private static AnimalClientForm clientForm = null;
    private static AnimalClientFormListener clientFormListener = null;
    private static Logger logger = null;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final TypeReference<HashMap<String, String>> typeRef = new TypeReference<HashMap<String, String>>() {
    };

    public static void startApp() {
        clientForm = new AnimalClientForm("Animal World");
        logger = new FormLogger(clientForm.getStatusMessageTextArea());
        clientFormListener = new AnimalClientFormListener(clientForm);
    }

    public static Map<String, String> sendConnectRequest() {
        String result = NetworkController.connect();
        logger.logMessage(result);

        return sendGetRequest("foodTypes");
    }


    public static void sendDisconnectRequest() {
        final String command = "stp";

        Request request = new Request(command);
        Response response = sendRequest(request);

        NetworkController.disconnect();

        logger.logMessage(response.getMessage());
    }

    public static Map<String, String> sendGetRequest(String argument) {
        final String command = "get";
        ArrayList<String> requestArgs = new ArrayList<>();

        switch (argument) {
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
            case "Животные":
                requestArgs.add("anim");
                break;
            case "foodTypes":
                requestArgs.add("foodTypes");
            default:
                throw new IllegalArgumentException("Некорректное значение выбора");
        }

        Request request = new Request(command, (String[]) requestArgs.toArray());
        Response response = sendRequest(request);

        return parseJsonString(response.getMessage());
    }

    public static void sendCreateRequest(String foodType, String foodName, String foodMass) {
        final String command = "crt";

        Request request = new Request(command, foodType, foodName, foodMass);
        Response response = sendRequest(request);

        logger.logMessage(response.getMessage());
    }

    public static void sendFeedRequest(String feedAnimalId, String feedPreyId) {
        final String command = "feed";

        Request request = new Request(command, feedAnimalId, feedPreyId);
        Response response = sendRequest(request);

        logger.logMessage(response.getMessage());
    }

    public static Map<String, String> parseJsonString(String responseString) {
        Map<String, String> objectCollection = null;
        try {
            objectCollection = objectMapper.readValue(responseString, typeRef);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectCollection;
    }

    private static Response sendRequest(Request request) {
        // todo: multithreading
        String responseString = NetworkController.sendRequest(request);
        Response response = Response.parseResponse(responseString);

        if (response.isClosureStatus() || response.isErrorStatus()) {
            logger.logMessage(response.getMessage());
        }

        return response;
    }
}
