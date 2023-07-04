import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class.getName());
    private static Socket clientSocket;
    private static ServerSocket server;
    private static final ArrayList<UserHandler> userList = new ArrayList<>();
    private static final int PORT = 8080;

    public Server() {
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server started!");
            logger.info("Server started at {}", LocalDateTime.now());
            try {
                while (true) {
                    clientSocket = server.accept();
                    System.out.println("New client accepted!");
                    logger.info("New user connected: {}", clientSocket);
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

}
