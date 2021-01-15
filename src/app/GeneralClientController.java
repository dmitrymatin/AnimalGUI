package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import shared.FormLogger;
import shared.Logger;
import shared.Request;
import shared.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GeneralClientController {
    private static AnimalClientForm clientForm = null;
    private static AnimalClientFormListener clientFormListener = null;
    private static Logger logger = null;

    public static void startApp() {
        clientForm = new AnimalClientForm("Animal World");
        logger = new FormLogger(clientForm.getStatusMessageTextArea());
        clientFormListener = new AnimalClientFormListener(clientForm, logger);
    }

    public static boolean sendConnectRequest() {
        try {
            String result = NetworkController.connect();
            logger.logMessage(result);
            return true;
        } catch (Exception e) {
            logger.logMessage("Ошибка: " + e.getMessage());
            return false;
        }
    }

    public static void sendDisconnectRequest() {
        final String command = "stp";

        Request request = new Request(command);
        Response response = sendRequest(request);

        NetworkController.disconnect();
    }

    public static Map<String, String> sendGetFoodTypesRequest() {
        final String foodTypes = "foodTypes";
        final String foodTypesAlias = "Виды еды";

        Response response = sendGetRequest(new String[]{foodTypes});
        logger.logMessage("Загружены данные для " + "\"" + foodTypesAlias + "\"");

        return parseJsonString(response.getMessage(), String.class);
    }

    public static Map<String, FoodDto> sendGetFoodsRequest(String foodType) {
        ArrayList<String> requestArgs = new ArrayList<>();

        switch (foodType) {
            case "Все":
                requestArgs.add("all");
                break;
            case "Хищник":
                requestArgs.add("pdt");
                break;
            case "Травоядное":
                requestArgs.add("hbv");
                break;
            case "Трава":
                requestArgs.add("grs");
                break;
            case "Животные":
                requestArgs.add("anim");
                break;
            default:
                throw new IllegalArgumentException("Некорректное значение выбора");
        }

        Response response = sendGetRequest(Arrays.copyOf(requestArgs.toArray(), requestArgs.size(), String[].class));
        logger.logMessage("Загружены данные для " + "\"" + foodType + "\"");

        return parseJsonString(response.getMessage(), FoodDto.class);
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

    private static Response sendGetRequest(String[] arguments) {
        final String command = "get";

        Request request = new Request(command, arguments);
        return sendRequest(request);
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


    private static <V> HashMap<String, V> parseJsonString(String responseString, Class<V> valueClass) {
        HashMap<String, V> objectCollection = null;
        final ObjectMapper objectMapper = new ObjectMapper();
        final TypeFactory typeFactory = objectMapper.getTypeFactory();

        try {
            objectCollection = objectMapper.readValue(responseString, typeFactory.constructMapType(HashMap.class, String.class, valueClass));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return objectCollection;
    }
}
