package Server;

import UserHandler.UserHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class.getName());
    private static Socket clientSocket;
    private static ServerSocket server;
    private static int port;
    private static final ArrayList<UserHandler> userList = new ArrayList<>();

    public Server() {
        port = getPortFromSettings("src/main/resources/settings.txt");
        try {
            server = new ServerSocket(port);
            System.out.println("Server started!");
            logger.info("Server started at {}", LocalDateTime.now());
            try {
                while (true) {
                    clientSocket = server.accept();
                    System.out.println("New client accepted!");
                    UserHandler user = new UserHandler(this, clientSocket);
                    userList.add(user);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                server.close();
                logger.info("Server closed at {}", LocalDateTime.now());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void sendMessageToEveryone(String msg) {
        for (UserHandler user : userList) {
            user.sendMsg(msg);
        }
    }
    public static int getPortFromSettings(String filename) {
        File file = new File(filename);
        try {
            Scanner scanner = new Scanner(file);
            String port = scanner.nextLine();
            String[] portSplited = port.split(":");
            return Integer.parseInt(portSplited[1]);
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        }

    }

}
