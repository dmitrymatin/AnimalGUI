package app;

public class GeneralClientController {
    private static AnimalClientForm clientForm = null;
    private static AnimalClientFormListener clientFormListener = null;

    public static void startApp() {
        clientForm = new AnimalClientForm("Animal World");
        clientFormListener = new AnimalClientFormListener(clientForm);
    }

    public static void sendRequest(String queryString) {
        // socket, connect to server
    }
}
