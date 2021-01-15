package app;

import shared.Request;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkController {
    private static Socket socket;

    private static DataOutputStream out;
    private static DataInputStream in;

    public static String connect(String hostName, int port) throws Exception {
        try {
            socket = new Socket(hostName, port);
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
        } catch (UnknownHostException e) {
            throw new Exception("неизвестный хост");
        } catch (IOException e) {
            throw new Exception("не удалось подключиться");
        }
        return getResponse();
    }

    public static void disconnect() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    public static String sendRequest(Request request) throws IOException {
        out.writeUTF(request.toString());
        return getResponse();
    }

    public static String getResponse() throws IOException {
        return in.readUTF();
    }
}
