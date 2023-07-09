package com.ttchoa22ite.population.models;

import java.io.IOException;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatModel {
    private DatagramSocket socket;
    private ExecutorService executorService;
    private InetAddress serverAddress;
    private int serverPort;
    private OnMessageReceivedListener messageReceivedListener;

    public ChatModel() {
        try {
            socket = new DatagramSocket();
            serverAddress = InetAddress.getByName("localhost"); // Địa chỉ IP của máy chủ
            serverPort = 12345; // Cổng UDP của máy chủ
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
        executorService = Executors.newSingleThreadExecutor();

        // Set up listener for receiving messages
        executorService.execute(() -> {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                while (true) {
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength());
                    // Process received message
                    if (messageReceivedListener != null) {
                        messageReceivedListener.onMessageReceived(message);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void sendMessage(String message) {
        byte[] buffer = message.getBytes();
        try {
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnMessageReceivedListener(OnMessageReceivedListener listener) {
        messageReceivedListener = listener;
    }

    public void shutdown() {
        executorService.shutdown();
        socket.close();
    }

    public interface OnMessageReceivedListener {
        void onMessageReceived(String message);
    }
}
