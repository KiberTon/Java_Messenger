package ru.geekbrains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class Server {
    private ServerSocket serverSocket;
    private Vector<ClientHandler> clients;

    public Server() {
        try {
            serverSocket = new ServerSocket(8189);
            clients = new Vector<ClientHandler>();
            System.out.println("Сервер запущен");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Клиент подключился");
                clients.add(new ClientHandler(this, socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void broadcastMsg(String msg){
        for (ClientHandler o : clients){
            o.sendMsg(msg);
        }
    }
    public void unsubscribe(ClientHandler clientHandler){
        clients.remove(clientHandler);
    }
}
