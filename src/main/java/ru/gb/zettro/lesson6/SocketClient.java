package ru.gb.zettro.lesson6;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketClient {


    public static String sendRequest(String host, int port, String method, String path, String contentType, String aux) throws IOException, InterruptedException {
        StringBuilder request = new StringBuilder();
        request.append(method).append(" ")
                .append(path).append(" ")
                .append("HTTP/1.1\r\n")
                .append("Host: ").append(host);
        if (port > 0) {
            request.append(":").append(port);
        }
        request.append("\r\n")
                .append("Connection: close\r\n")
                .append("Content-Type: ").append(contentType).append("\r\n");
        if (aux.length() > 0) {
            request.append("Content-Length: ").append(aux.length()).append("\r\n\r\n")
                    .append(aux);
        }
        request.append("\r\n");
        System.out.println(request);
        try (Socket socket = new Socket(host, port)) {
            socket.setSoTimeout(3000);
            socket.getOutputStream().write(request.toString().getBytes(StandardCharsets.UTF_8));
            socket.getOutputStream().flush();
            InputStreamReader inputStream = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
            char[] buffer = new char[8192];
            StringBuilder response = new StringBuilder();
            int received;

            while ((received = inputStream.read(buffer)) != -1) {
                response.append(buffer, 0, received);
            }
            return response.toString();
        }
    }

    public static void main(String[] args) throws IOException {
        String response;
        try {
            response = sendRequest("localhost", 8189, "GET", "/simple-server/units", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
            response = sendRequest("localhost", 8189, "GET", "/simple-server/units?filter=ri", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
            response = sendRequest("localhost", 8189, "GET", "/simple-server/units/3", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
            response = sendRequest("localhost", 8189, "DELETE", "/simple-server/units/3", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
            response = sendRequest("localhost", 8189, "GET", "/simple-server/units/3", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
            String newUnitJSON = "{\"name\":\"Module NEW\",\"description\":\"Advanced Techniques\"}";
            System.out.println(newUnitJSON);
            response = sendRequest("localhost", 8189, "POST", "/simple-server/units/add", "application/json", newUnitJSON);
            System.out.println("Server response is:\n" + response);
            response = sendRequest("localhost", 8189, "GET", "/simple-server/units", "text/plain;charset=UTF-8", "");
            System.out.println("Server response is:\n" + response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
