package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkController {
    private static Socket socket;
    private static int port;

    private static PrintWriter out;
    private static BufferedReader in;

    public static void connect() {
        try {
            port = 7070; // TODO: refactor hardcoded
            socket = new Socket("localhost", port);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect(){
        try {
            socket.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        System.out.println("Клиентский сокет для порта " + port + " закрыт\n");
    }

    public static String sendRequest(String queryString) {
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.print(queryString);

//            System.out.println("Создан клиентский сокет, port: " + port);
//
//            InputStream is = socket.getInputStream();
//            System.out.println("Получена ссылка на объект входного потока");
//
//            System.out.println(is.read());
//            System.out.println("Прочитан байт из объекта входного потока");

            return getResponse();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getResponse(){

        String response = null;
        try {
            response = in.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return response;
    }
}
