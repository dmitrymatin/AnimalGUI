package app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import shared.FormLogger;
import shared.Logger;
import shared.Request;
import shared.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.*;

public class GeneralClientController {
    private static Logger logger = null;
    private static Properties properties = new Properties();
    private final static String PROPS_FILENAME = "settings.properties";

    public static void startApp() {
        try {
            properties.load(new FileInputStream(PROPS_FILENAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        AnimalClientForm clientForm = new AnimalClientForm("Animal World");
        AnimalClientFormListener clientFormListener = new AnimalClientFormListener(clientForm, logger);
        logger = new FormLogger(clientForm.getStatusMessageTextArea());
    }

    public static boolean sendConnectRequest(String hostString, String portString) {
        try {
            InetAddress host = InetAddress.getByName(hostString);

            int port = Integer.parseInt(portString.trim());
            if (!(port >= 0 && port <= 65535))
                throw new IllegalArgumentException();

            String result = NetworkController.connect(host.getHostAddress(), port);
            logger.logMessage(result);
            return true;
        } catch (UnknownHostException e) {
            logger.logMessage("Ошибка: неизвестный хост");
        } catch (IllegalArgumentException e) {
            logger.logMessage("Ошибка: неверное значение порта");
        } catch (Exception e) {
            logger.logMessage("Ошибка: " + e.getMessage());
        }
        return false;
    }

    public static void sendDisconnectRequest() {
        final String command = "stp";

        Request request = new Request(command);
        Response response = sendRequest(request);
        logger.logMessage(response.getMessage());

        try {
            NetworkController.disconnect();
        } catch (IOException exception) {
            logger.logMessage("Произошла ошибка во время отключения");
        }
    }

    public static Map<String, String> sendGetFoodTypesRequest() {
        final String foodTypes = "foodTypes";
        final String foodTypesAlias = "Виды еды";

        Response response = sendGetRequest(new String[]{foodTypes});
        if (response.isClosureStatus() || response.isErrorStatus()) {
            logger.logMessage(response.getMessage());
            return null;
        }

        Map<String, String> foodTypesMap = parseJsonString(response.getMessage(), String.class);
        reportSuccessfulJsonParsing(foodTypesMap, foodTypesAlias);
        return foodTypesMap;
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
        if (response.isClosureStatus() || response.isErrorStatus()) {
            logger.logMessage(response.getMessage());
            return null;
        }

        Map<String, FoodDto> foods = parseJsonString(response.getMessage(), FoodDto.class);
        reportSuccessfulJsonParsing(foods, foodType);
        return foods;
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
        String responseString = null;
        try {
            responseString = NetworkController.sendRequest(request);
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return Response.parseResponse(responseString);
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

    private static <V> void reportSuccessfulJsonParsing(Map<String, V> map, String foodType) {
        int mapCount = 0;
        if (map != null) {
            mapCount = map.size();
            logger.logMessage("Загружены данные для " + "\"" + foodType + "\": " + mapCount + " элементов");
        } else {
            logger.logMessage("Для " + "\"" + foodType + "\" запрос вернул " + mapCount + " элементов");
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
