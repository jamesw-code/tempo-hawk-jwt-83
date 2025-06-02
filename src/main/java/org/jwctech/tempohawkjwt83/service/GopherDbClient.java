package org.jwctech.tempohawkjwt83.service;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

@Component
public class GopherDbClient {
    private final String host = "localhost";
    private final int port = 5321;

    public String get(String key) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("GET " + key);
            return in.readLine();

        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to GopherDb", e);
        }
    }

    public void set(String key, String value) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            out.println("SET " + key + " " + value);
            in.readLine(); // ignore "OK" or error for now

        } catch (IOException e) {
            throw new RuntimeException("Failed to connect to GopherDb", e);
        }
    }
}
