package app;

import shared.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkController {
    private static Socket socket;
    private static int port;

    private static DataOutputStream out;
    private static DataInputStream in;

    public static String connect() throws Exception {
        String result;
        try {
            port = 7070; // TODO: refactor hardcoded
            socket = new Socket("localhost", port);

            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());

            return getResponse();
        } catch (UnknownHostException e) {
            throw new Exception("неизвестный хост");
        } catch (IOException e) {
            throw new Exception("неудалось подключиться");
        }
    }

    public static void disconnect() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println("Клиентский сокет для порта " + port + " закрыт\n");
    }

    public static String sendRequest(Request request) {
        try {
            out.writeUTF(request.toString());
            return getResponse();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getResponse() {
        String response = null;
        try {
            response = in.readUTF();
        } catch (IOException exception) {
            exception.printStackTrace();
            response = "возникла ошибка при чтении данных с сервера";
        }

        return response;
    }
}
