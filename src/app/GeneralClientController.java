package app;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class GeneralClientController {
    private static AnimalClientForm clientForm = null;
    private static AnimalClientFormListener clientFormListener = null;

    public static void startApp() {
        clientForm = new AnimalClientForm("Animal World");
        clientFormListener = new AnimalClientFormListener(clientForm);
    }

    public static void sendRequest(String queryString) {
        String response = NetworkController.sendRequest(queryString);
        clientForm.getNameTextField().setText(response);
    }
}
